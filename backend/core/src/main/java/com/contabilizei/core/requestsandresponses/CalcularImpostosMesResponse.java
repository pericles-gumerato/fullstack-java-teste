package com.contabilizei.core.requestsandresponses;

import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
@JsonInclude(NON_EMPTY)
public class CalcularImpostosMesResponse extends AppResponse {

    private boolean sucesso;

    public boolean isSucesso() {
        return sucesso;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }

}
