package com.contabilizei.core.requestsandresponses;

import com.contabilizei.core.to.ClienteTO;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
@JsonInclude(NON_EMPTY)
public class BuscarClientesResponse extends AppResponse {

    private List<ClienteTO> clientes;

    public List<ClienteTO> getClientes() {
        return clientes;
    }

    public void setClientes(List<ClienteTO> clientes) {
        this.clientes = clientes;
    }
}
