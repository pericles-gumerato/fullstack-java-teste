package com.contabilizei.core.requestsandresponses;

import javax.validation.constraints.NotNull;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
public class BuscarImpostosMesRequest {

    @NotNull
    private Long clienteId;

    @NotNull
    private Integer mes;

    @NotNull
    private Integer ano;

    private Integer pagina;

    private Integer maxPorPagina;

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

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
