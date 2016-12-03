package com.contabilizei.core.service;

import com.contabilizei.core.requestsandresponses.MarcarImpostoComoPagoRequest;
import com.contabilizei.core.requestsandresponses.MarcarImpostoPagoResponse;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
public interface UpdateService {
    /**
     * Marca um determinado imposto como pago.
     *
     * @param request objeto de request
     * @return objeto de resposta.
     */
    MarcarImpostoPagoResponse marcarImpostoComoPago(MarcarImpostoComoPagoRequest request);
}
