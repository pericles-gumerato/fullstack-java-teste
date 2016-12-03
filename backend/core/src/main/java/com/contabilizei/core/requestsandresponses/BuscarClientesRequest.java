package com.contabilizei.core.requestsandresponses;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
public class BuscarClientesRequest {

    private Integer pagina;

    private Integer maxPorPagina;

    public Integer getPagina() {
        return pagina;
    }

    public void setPagina(Integer pagina) {
        this.pagina = pagina;
    }

    public Integer getMaxPorPagina() {
        return maxPorPagina;
    }

    public void setMaxPorPagina(Integer maxPorPagina) {
        this.maxPorPagina = maxPorPagina;
    }
}
