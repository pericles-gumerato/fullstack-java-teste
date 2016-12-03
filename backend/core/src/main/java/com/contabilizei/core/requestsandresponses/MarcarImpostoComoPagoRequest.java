package com.contabilizei.core.requestsandresponses;

import javax.validation.constraints.NotNull;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
public class MarcarImpostoComoPagoRequest {

    @NotNull
    private Long clienteId;

    @NotNull
    private Long impostoId;

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getImpostoId() {
        return impostoId;
    }

    public void setImpostoId(Long impostoId) {
        this.impostoId = impostoId;
    }
}
