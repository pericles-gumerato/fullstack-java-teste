package com.contabilizei.app.controller;

import com.contabilizei.app.util.ResponseWrapper;
import com.contabilizei.app.util.ServicesManagerFactory;
import com.contabilizei.core.requestsandresponses.CriarClientRequest;
import com.contabilizei.core.requestsandresponses.CriarClientResponse;
import com.contabilizei.core.requestsandresponses.CriarNotaFiscalRequest;
import com.contabilizei.core.requestsandresponses.CriarNotaFiscalResponse;
import com.contabilizei.core.service.CadastroService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
@Path("cadastro")
public class CadastroController {

    private static final Logger logger = LogManager.getLogger(CadastroController.class);

    private final CadastroService cadastroService;

    public CadastroController() {
        this.cadastroService = ServicesManagerFactory.getServicesManager().getCadastroService();
    }

    @PUT
    @Path("cliente")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public ResponseWrapper<CriarClientResponse> cadastrarCliente(@Valid CriarClientRequest criarClienteRequest) {

        logger.debug("Creating client with request {}", criarClienteRequest);

        CriarClientResponse response = cadastroService.criarCliente(criarClienteRequest);

        return new ResponseWrapper(response);
    }

    @PUT
    @Path("nota_fiscal")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public ResponseWrapper<CriarNotaFiscalResponse> cadastrarNotaFiscal(@Valid CriarNotaFiscalRequest criarNotaFiscalRequest) {

        logger.debug("Creating nota fiscal with request {}", criarNotaFiscalRequest);

        CriarNotaFiscalResponse response = cadastroService.criarNotaFiscal(criarNotaFiscalRequest);

        return new ResponseWrapper(response);
    }

}

