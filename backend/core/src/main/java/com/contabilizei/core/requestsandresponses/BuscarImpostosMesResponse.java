package com.contabilizei.core.requestsandresponses;

import com.contabilizei.core.to.ImpostoMesTO;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
@JsonInclude(NON_EMPTY)
public class BuscarImpostosMesResponse extends AppResponse {

    private List<ImpostoMesTO> impostos;

    public List<ImpostoMesTO> getImpostos() {
        return impostos;
    }

    public void setImpostos(List<ImpostoMesTO> impostos) {
        this.impostos = impostos;
    }
}
