package com.contabilizei.core.service.impl;

import com.contabilizei.core.dao.ClienteDao;
import com.contabilizei.core.dao.ImpostoMesDao;
import com.contabilizei.core.dao.NotaFiscalDao;
import com.contabilizei.core.requestsandresponses.CalcularImpostosMesRequest;
import com.contabilizei.core.requestsandresponses.CalcularImpostosMesResponse;
import com.contabilizei.core.service.CalculoService;
import com.contabilizei.core.util.DateUtils;
import com.contabilizei.model.entity.Cliente;
import com.contabilizei.model.entity.ImpostoMes;
import com.contabilizei.model.entity.NotaFiscal;
import com.contabilizei.model.enums.RegimeTributario;
import com.contabilizei.model.enums.TipoImposto;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
public class CalculoServiceImpl implements CalculoService {

    private static int TAMANHO_BATCH_NOTAS_CALCULO = 100; // Size of list retrieved per interaction

    private final ClienteDao clienteDao;
    private final ImpostoMesDao impostoMesDao;
    private final NotaFiscalDao notaFiscalDao;

    public CalculoServiceImpl(ClienteDao clienteDao, ImpostoMesDao impostoMesDao, NotaFiscalDao notaFiscalDao) {
        this.clienteDao = clienteDao;
        this.impostoMesDao = impostoMesDao;
        this.notaFiscalDao = notaFiscalDao;
    }

    @Override
    public CalcularImpostosMesResponse calcularImpostosMes(CalcularImpostosMesRequest request) {
        Long clienteId = request.getClienteId();
        Calendar mesAnoReferencia = Calendar.getInstance();
        int mes = request.getMes();
        mesAnoReferencia.set(Calendar.MONTH, mes - 1); // Adjusting human month number -> Java month number
        mesAnoReferencia.set(Calendar.YEAR, request.getAno());

        Optional<Cliente> clienteOptional = clienteDao.buscarCliente(clienteId);
        if (!clienteOptional.isPresent()) {
            CalcularImpostosMesResponse response = new CalcularImpostosMesResponse();
            response.setSucesso(false);
            response.setMensagem("Cliente não encontrado");

            return response;
        }

        Cliente cliente = clienteOptional.get();

        if (cliente.getRegimeTributario().equals(RegimeTributario.SIMPLES_NACIONAL)) {
            return calculaImpostoSimplesNacional(cliente, mesAnoReferencia.getTime());
        } else {
            return calculaImpostoLucroPresumido(cliente, mesAnoReferencia.getTime());
        }
    }

    private CalcularImpostosMesResponse calculaImpostoSimplesNacional(Cliente cliente, Date mesAnoReferencia) {
        CalcularImpostosMesResponse response = new CalcularImpostosMesResponse();

        if (checaImpostoJaCalculado(cliente, mesAnoReferencia)) {
            response.setSucesso(false);
            response.setMensagem("Imposto já calculado para cliente no ano e mês de referência informado.");
            return response;
        }

        int page = 1;
        List<NotaFiscal> notasFiscaisMes = notaFiscalDao.consultaNotasFiscais(cliente.getId(), mesAnoReferencia, TAMANHO_BATCH_NOTAS_CALCULO, page);
        if (notasFiscaisMes.size() == 0) {
            response.setSucesso(false);
            response.setMensagem("Não existem notas fiscais para o ano e mês de referência");
            return response;
        }

        Long totalImpostoEmCentavos = 0L;
        while (notasFiscaisMes.size() > 0) {
            for (NotaFiscal nf : notasFiscaisMes) {
                Long valorNotaEmCentavos = nf.getValorCentavos();
                Long aliquotaPorMil = nf.getAnexo().getAliquotaPormil();

                totalImpostoEmCentavos += (valorNotaEmCentavos * aliquotaPorMil) / 1000;
            }

            // Get next fiscal notes
            page++;
            notasFiscaisMes = notaFiscalDao.consultaNotasFiscais(cliente.getId(), mesAnoReferencia, TAMANHO_BATCH_NOTAS_CALCULO, page);
        }

        // Compute date
        Calendar vencimento = Calendar.getInstance();
        vencimento.setTime(mesAnoReferencia);
        vencimento.add(Calendar.MONTH, 1); // Next month
        vencimento.set(Calendar.DAY_OF_MONTH, 20); // Set vencimento to day 20

        // Create the tax registry
        ImpostoMes impostoMes = new ImpostoMes();
        impostoMes.setClienteId(cliente.getId());
        impostoMes.setMesAnoReferencia(mesAnoReferencia);
        impostoMes.setPago(false);
        impostoMes.setTipo(TipoImposto.SIMPLES_NACIONAL);
        impostoMes.setValorCentavos(totalImpostoEmCentavos);
        impostoMes.setVencimento(vencimento.getTime());

        impostoMesDao.criarImpostoMes(impostoMes);

        response.setSucesso(true);
        return response;
    }

    private CalcularImpostosMesResponse calculaImpostoLucroPresumido(Cliente cliente, Date mesAnoReferencia) {
        CalcularImpostosMesResponse response = new CalcularImpostosMesResponse();

        if (checaImpostoJaCalculado(cliente, mesAnoReferencia)) {
            response.setSucesso(false);
            response.setMensagem("Imposto já calculado para cliente no ano e mês de referência informado.");
            return response;
        }

        int page = 1;
        List<NotaFiscal> notasFiscaisMes = notaFiscalDao.consultaNotasFiscais(cliente.getId(), mesAnoReferencia, TAMANHO_BATCH_NOTAS_CALCULO, page);
        if (notasFiscaisMes.size() == 0) {
            response.setSucesso(false);
            response.setMensagem("Não existem notas fiscais para o ano e mês de referência");
            return response;
        }

        Long somaNotasMes = 0L;
        while (notasFiscaisMes.size() > 0) {
            for (NotaFiscal nf : notasFiscaisMes) {
                somaNotasMes += nf.getValorCentavos();
            }

            // Get next fiscal notes
            page++;
            notasFiscaisMes = notaFiscalDao.consultaNotasFiscais(cliente.getId(), mesAnoReferencia, TAMANHO_BATCH_NOTAS_CALCULO, page);
        }

        // Compute date
        Calendar vencimento = Calendar.getInstance();
        vencimento.setTime(DateUtils.finalDoMes(mesAnoReferencia));

        // Create the tax registries
        // IRPJ
        ImpostoMes irpjMes = new ImpostoMes();
        irpjMes.setClienteId(cliente.getId());
        irpjMes.setMesAnoReferencia(mesAnoReferencia);
        irpjMes.setPago(false);
        irpjMes.setTipo(TipoImposto.IRPJ);
        irpjMes.setValorCentavos(somaNotasMes * TipoImposto.IRPJ.getAliquotaPormil().get() / 1000);
        irpjMes.setVencimento(vencimento.getTime());

        impostoMesDao.criarImpostoMes(irpjMes);

        // ISS
        ImpostoMes issMes = new ImpostoMes();
        issMes.setClienteId(cliente.getId());
        issMes.setMesAnoReferencia(mesAnoReferencia);
        issMes.setPago(false);
        issMes.setTipo(TipoImposto.ISS);
        issMes.setValorCentavos(somaNotasMes * TipoImposto.ISS.getAliquotaPormil().get() / 1000);
        issMes.setVencimento(vencimento.getTime());

        impostoMesDao.criarImpostoMes(issMes);

        // ISS
        ImpostoMes cofinsMes = new ImpostoMes();
        cofinsMes.setClienteId(cliente.getId());
        cofinsMes.setMesAnoReferencia(mesAnoReferencia);
        cofinsMes.setPago(false);
        cofinsMes.setTipo(TipoImposto.COFINS);
        cofinsMes.setValorCentavos(somaNotasMes * TipoImposto.COFINS.getAliquotaPormil().get() / 1000);
        cofinsMes.setVencimento(vencimento.getTime());

        impostoMesDao.criarImpostoMes(cofinsMes);

        response.setSucesso(true);
        return response;
    }

    private boolean checaImpostoJaCalculado(Cliente cliente, Date mesAnoReferencia) {

        List<ImpostoMes> impostos = impostoMesDao.consultaImpostosMes(cliente.getId(), mesAnoReferencia, 1, 1);
        if (impostos.size() > 0) {
            return true;
        }

        /*
            At this point the computation could have been made, and generated in 0 taxes.
            In this case, the user can calculate it again.
         */
        return false;
    }

}
