package com.contabilizei.app.controller;

import com.contabilizei.app.util.ResponseWrapper;
import com.contabilizei.app.util.ServicesManagerFactory;
import com.contabilizei.core.requestsandresponses.CalcularImpostosMesRequest;
import com.contabilizei.core.requestsandresponses.CalcularImpostosMesResponse;
import com.contabilizei.core.service.CalculoService;

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
@Path("calculo")
public class CalculoController {

    private static final Logger logger = LogManager.getLogger(CalculoController.class);

    private final CalculoService calculoService;

    public CalculoController() {
        this.calculoService = ServicesManagerFactory.getServicesManager().getCalculoService();
    }

    @POST
    @Path("impostos_mes")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public ResponseWrapper<CalcularImpostosMesResponse> calcularImpostosMes(@Valid CalcularImpostosMesRequest calcularImpostosMesRequest) {

        logger.debug("Computing taxes with request {}", calcularImpostosMesRequest);

        CalcularImpostosMesResponse response = calculoService.calcularImpostosMes(calcularImpostosMesRequest);

        return new ResponseWrapper(response);
    }
}

