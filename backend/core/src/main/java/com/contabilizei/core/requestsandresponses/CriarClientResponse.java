package com.contabilizei.core.requestsandresponses;

import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
@JsonInclude(NON_EMPTY)
public class CriarClientResponse extends AppResponse {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
