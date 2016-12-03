package com.contabilizei.core.service.impl;

import com.contabilizei.core.dao.ClienteDao;
import com.contabilizei.core.dao.NotaFiscalDao;
import com.contabilizei.core.requestsandresponses.CriarClientRequest;
import com.contabilizei.core.requestsandresponses.CriarClientResponse;
import com.contabilizei.core.requestsandresponses.CriarNotaFiscalRequest;
import com.contabilizei.core.requestsandresponses.CriarNotaFiscalResponse;
import com.contabilizei.core.service.CadastroService;
import com.contabilizei.model.entity.Cliente;
import com.contabilizei.model.entity.NotaFiscal;
import com.contabilizei.model.enums.RegimeTributario;

import java.util.Optional;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
public class CadastroServiceImpl implements CadastroService {

    private final ClienteDao clienteDao;
    private final NotaFiscalDao notaFiscalDao;

    public CadastroServiceImpl(ClienteDao clienteDao, NotaFiscalDao notaFiscalDao) {
        this.clienteDao = clienteDao;
        this.notaFiscalDao = notaFiscalDao;
    }

    @Override
    public CriarClientResponse criarCliente(CriarClientRequest request) {
        Cliente cliente = new Cliente();
        cliente.setAnexos(request.getAnexos());
        cliente.setCnpj(request.getCnpj());
        cliente.setRazaoSocial(request.getRazaoSocial());
        cliente.setRegimeTributario(request.getRegimeTributario());

        cliente = clienteDao.criarCliente(cliente);

        CriarClientResponse response = new CriarClientResponse();
        response.setId(cliente.getId());

        return response;
    }

    @Override
    public CriarNotaFiscalResponse criarNotaFiscal(CriarNotaFiscalRequest request) {

        CriarNotaFiscalResponse response = new CriarNotaFiscalResponse();
        // Check the cliente
        Optional<Cliente> clienteOptional = clienteDao.buscarCliente(request.getClienteId());
        if (!clienteOptional.isPresent()) {
            response.setMensagem("Cliente não encontrado");
            response.setSucesso(false);

            return response;
        }

        if (clienteOptional.get().getRegimeTributario().equals(RegimeTributario.SIMPLES_NACIONAL) && !clienteOptional.get().getAnexos().contains(request.getAnexo())) {
            response.setMensagem("Cliente Simples Nacional não possui anexo informado em seu cadastro");
            response.setSucesso(false);
            return response;
        }

        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setClienteId(request.getClienteId());
        notaFiscal.setAnexo(request.getAnexo());
        notaFiscal.setDataEmissao(request.getDataEmissao());
        notaFiscal.setDescricao(request.getDescricao());
        notaFiscal.setNumero(request.getNumero());
        notaFiscal.setValorCentavos(request.getValorCentavos());

        notaFiscal = notaFiscalDao.criarNotaFiscal(notaFiscal);

        response.setId(notaFiscal.getId());
        response.setSucesso(true);
        return response;
    }
}
