package com.contabilizei.core.requestsandresponses;

import com.contabilizei.core.to.NotaFiscalTO;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
@JsonInclude(NON_EMPTY)
public class BuscarNotasFiscaisResponse extends AppResponse {

    private List<NotaFiscalTO> notas;

    public List<NotaFiscalTO> getNotas() {
        return notas;
    }

    public void setNotas(List<NotaFiscalTO> notas) {
        this.notas = notas;
    }
}
