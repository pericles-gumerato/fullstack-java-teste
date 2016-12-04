package com.contabilizei.core.service.impl;

import com.contabilizei.core.dao.ClienteDao;
import com.contabilizei.core.dao.ImpostoMesDao;
import com.contabilizei.core.dao.NotaFiscalDao;
import com.contabilizei.core.requestsandresponses.BuscarClientesRequest;
import com.contabilizei.core.requestsandresponses.BuscarClientesResponse;
import com.contabilizei.core.requestsandresponses.BuscarImpostosMesRequest;
import com.contabilizei.core.requestsandresponses.BuscarImpostosMesResponse;
import com.contabilizei.core.requestsandresponses.BuscarNotasFiscaisRequest;
import com.contabilizei.core.requestsandresponses.BuscarNotasFiscaisResponse;
import com.contabilizei.core.service.ConsultaService;
import com.contabilizei.core.to.ClienteTO;
import com.contabilizei.core.to.ImpostoMesTO;
import com.contabilizei.core.to.NotaFiscalTO;
import com.contabilizei.model.entity.Cliente;
import com.contabilizei.model.entity.ImpostoMes;
import com.contabilizei.model.entity.NotaFiscal;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
public class ConsultaServiceImpl implements ConsultaService {

    private static final int DEFAULT_MAX_RESULTS_PER_PAGE = 20; // If the client does not send, we'll lock the page size to 10.
    private static final int GLOBAL_MAX_RESULTS_PER_PAGE = 1000; // Let's define a global max to avoid requests with 1M results per page.

    private final ClienteDao clienteDao;
    private final ImpostoMesDao impostoMesDao;
    private final NotaFiscalDao notaFiscalDao;

    public ConsultaServiceImpl(ClienteDao clienteDao, ImpostoMesDao impostoMesDao, NotaFiscalDao notaFiscalDao) {
        this.clienteDao = clienteDao;
        this.impostoMesDao = impostoMesDao;
        this.notaFiscalDao = notaFiscalDao;
    }

    @Override
    public BuscarClientesResponse consultaTodosClientes(BuscarClientesRequest request) {

        int pagina = Objects.isNull(request.getPagina()) ? 1 : request.getPagina();
        Integer maxPorPagina = request.getMaxPorPagina();
        if (Objects.isNull(maxPorPagina)) {
            maxPorPagina = DEFAULT_MAX_RESULTS_PER_PAGE;
        } else if (maxPorPagina > GLOBAL_MAX_RESULTS_PER_PAGE) {
            maxPorPagina = GLOBAL_MAX_RESULTS_PER_PAGE;
        }

        List<Cliente> clientes = clienteDao.buscarTodosClientesSemAnexos(maxPorPagina, pagina);

        BuscarClientesResponse response = new BuscarClientesResponse();
        response.setClientes(clientes.stream().map(c -> ClienteTO.fromCliente(c)).collect(Collectors.toList()));

        return response;
    }

    @Override
    public BuscarNotasFiscaisResponse consultaNotasFiscaisMes(BuscarNotasFiscaisRequest request) {

        BuscarNotasFiscaisResponse response = new BuscarNotasFiscaisResponse();
        // Check the cliente
        Optional<Cliente> cliente = clienteDao.buscarCliente(request.getClienteId());
        if (!cliente.isPresent()) {
            response.setMensagem("Cliente não encontrado");

            return response;
        }

        Calendar mesAnoReferencia = Calendar.getInstance();
        int mes = request.getMes();
        mesAnoReferencia.set(Calendar.MONTH, mes - 1); // Adjusting human month number -> Java month number
        mesAnoReferencia.set(Calendar.YEAR, request.getAno());

        int pagina = Objects.isNull(request.getPagina()) ? 1 : request.getPagina();
        Integer maxPorPagina = request.getMaxPorPagina();
        if (Objects.isNull(maxPorPagina)) {
            maxPorPagina = DEFAULT_MAX_RESULTS_PER_PAGE;
        } else if (maxPorPagina > GLOBAL_MAX_RESULTS_PER_PAGE) {
            maxPorPagina = GLOBAL_MAX_RESULTS_PER_PAGE;
        }

        List<NotaFiscal> notasDB = notaFiscalDao.consultaNotasFiscais(request.getClienteId(), mesAnoReferencia.getTime(), maxPorPagina, pagina);

        response.setNotas(notasDB.stream().map(nf -> NotaFiscalTO.fromNotaFiscal(nf)).collect(Collectors.toList()));

        return response;
    }

    @Override
    public BuscarImpostosMesResponse consultaNotasFiscaisMes(BuscarImpostosMesRequest request) {

        BuscarImpostosMesResponse response = new BuscarImpostosMesResponse();
        // Check the cliente
        Optional<Cliente> cliente = clienteDao.buscarCliente(request.getClienteId());
        if (!cliente.isPresent()) {
            response.setMensagem("Cliente não encontrado");

            return response;
        }

        Calendar mesAnoReferencia = Calendar.getInstance();
        int mes = request.getMes();
        mesAnoReferencia.set(Calendar.MONTH, mes - 1); // Adjusting human month number -> Java month number
        mesAnoReferencia.set(Calendar.YEAR, request.getAno());

        int pagina = Objects.isNull(request.getPagina()) ? 1 : request.getPagina();
        Integer maxPorPagina = request.getMaxPorPagina();
        if (Objects.isNull(maxPorPagina)) {
            maxPorPagina = DEFAULT_MAX_RESULTS_PER_PAGE;
        } else if (maxPorPagina > GLOBAL_MAX_RESULTS_PER_PAGE) {
            maxPorPagina = GLOBAL_MAX_RESULTS_PER_PAGE;
        }

        List<ImpostoMes> impostosMes = impostoMesDao.consultaImpostosMes(request.getClienteId(), mesAnoReferencia.getTime(), maxPorPagina, pagina);

        response.setImpostos(impostosMes.stream().map(i -> ImpostoMesTO.fromImpostoMes(i)).collect(Collectors.toList()));

        return response;
    }

}
