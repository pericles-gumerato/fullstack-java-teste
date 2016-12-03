package com.contabilizei.core.service.impl;

import com.contabilizei.core.dao.ImpostoMesDao;
import com.contabilizei.core.requestsandresponses.MarcarImpostoComoPagoRequest;
import com.contabilizei.core.requestsandresponses.MarcarImpostoPagoResponse;
import com.contabilizei.core.service.UpdateService;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
public class UpdateServiceImpl implements UpdateService {

    private final ImpostoMesDao impostoMesDao;

    public UpdateServiceImpl(ImpostoMesDao impostoMesDao) {
        this.impostoMesDao = impostoMesDao;
    }


    @Override
    public MarcarImpostoPagoResponse marcarImpostoComoPago(MarcarImpostoComoPagoRequest request) {
        MarcarImpostoPagoResponse response = new MarcarImpostoPagoResponse();
        response.setSucesso(impostoMesDao.marcarImpostoMesComoPago(request.getImpostoId(), request.getClienteId()));

        return response;
    }

}
