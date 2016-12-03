package com.contabilizei.app.controller;

import com.contabilizei.app.util.ResponseWrapper;
import com.contabilizei.app.util.ServicesManagerFactory;
import com.contabilizei.core.requestsandresponses.MarcarImpostoComoPagoRequest;
import com.contabilizei.core.requestsandresponses.MarcarImpostoPagoResponse;
import com.contabilizei.core.service.UpdateService;

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
@Path("update")
public class UpdateController {

    private static final Logger logger = LogManager.getLogger(UpdateController.class);

    private final UpdateService updateService;

    public UpdateController() {
        this.updateService = ServicesManagerFactory.getServicesManager().getUpdateService();
    }

    @POST
    @Path("marcar_imposto_como_pago")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public ResponseWrapper<MarcarImpostoPagoResponse> marcarImpostoComoPago(@Valid MarcarImpostoComoPagoRequest marcarImpostoComoPagoRequest) {

        logger.debug("Marking tax as paid with request {}", marcarImpostoComoPagoRequest);

        MarcarImpostoPagoResponse response = updateService.marcarImpostoComoPago(marcarImpostoComoPagoRequest);

        return new ResponseWrapper(response);
    }

}

