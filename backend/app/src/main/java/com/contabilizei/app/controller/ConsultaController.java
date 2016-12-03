package com.contabilizei.app.controller;

import com.contabilizei.app.util.ResponseWrapper;
import com.contabilizei.app.util.ServicesManagerFactory;
import com.contabilizei.core.requestsandresponses.BuscarClientesRequest;
import com.contabilizei.core.requestsandresponses.BuscarClientesResponse;
import com.contabilizei.core.requestsandresponses.BuscarImpostosMesRequest;
import com.contabilizei.core.requestsandresponses.BuscarImpostosMesResponse;
import com.contabilizei.core.requestsandresponses.BuscarNotasFiscaisRequest;
import com.contabilizei.core.requestsandresponses.BuscarNotasFiscaisResponse;
import com.contabilizei.core.service.ConsultaService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
@Path("consulta")
public class ConsultaController {

    private static final Logger logger = LogManager.getLogger(ConsultaController.class);

    private final ConsultaService consultaService;

    public ConsultaController() {
        this.consultaService = ServicesManagerFactory.getServicesManager().getConsultaService();
    }

    @POST
    @Path("clientes")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public ResponseWrapper<BuscarClientesResponse> buscarClientes(@Valid BuscarClientesRequest buscarClientesRequest) {

        logger.debug("Searching clientes with request {}", buscarClientesRequest);

        BuscarClientesResponse response = consultaService.consultaTodosClientes(buscarClientesRequest);

        return new ResponseWrapper(response);
    }

    @POST
    @Path("notas_fiscais")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public ResponseWrapper<BuscarNotasFiscaisResponse> buscarNotasFiscais(@Valid BuscarNotasFiscaisRequest buscarNotasFiscaisRequest) {

        logger.debug("Searching fiscal notes with request {}", buscarNotasFiscaisRequest);

        BuscarNotasFiscaisResponse response = consultaService.consultaNotasFiscaisMes(buscarNotasFiscaisRequest);

        return new ResponseWrapper(response);
    }

    @POST
    @Path("impostos_mes")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public ResponseWrapper<BuscarImpostosMesResponse> buscarImpostosMes(@Valid BuscarImpostosMesRequest buscarImpostosMesRequest) {

        logger.debug("Searching tax receipts with request {}", buscarImpostosMesRequest);

        BuscarImpostosMesResponse response = consultaService.consultaNotasFiscaisMes(buscarImpostosMesRequest);

        return new ResponseWrapper(response);
    }
}

