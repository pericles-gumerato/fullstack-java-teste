package com.contabilizei.core.requestsandresponses;

import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
@JsonInclude(NON_EMPTY)
public class MarcarImpostoPagoResponse extends AppResponse {

    private Boolean sucesso;

    public Boolean getSucesso() {
        return sucesso;
    }

    public void setSucesso(Boolean sucesso) {
        this.sucesso = sucesso;
    }
}
