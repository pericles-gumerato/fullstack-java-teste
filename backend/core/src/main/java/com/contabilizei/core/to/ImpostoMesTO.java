package com.contabilizei.core.to;

import com.contabilizei.model.entity.ImpostoMes;
import com.contabilizei.model.enums.TipoImposto;

import java.util.Date;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
public class ImpostoMesTO {

    private Long id;

    private Long clienteId;

    private TipoImposto tipo;

    private Date vencimento;

    private Long valorCentavos;

    private Date mesAnoReferencia;

    private Boolean pago;

    public static ImpostoMesTO fromImpostoMes(ImpostoMes impostoMes) {
        ImpostoMesTO ret = new ImpostoMesTO();
        ret.setId(impostoMes.getId());
        ret.setValorCentavos(impostoMes.getValorCentavos());
        ret.setClienteId(impostoMes.getClienteId());
        ret.setMesAnoReferencia(impostoMes.getMesAnoReferencia());
        ret.setPago(impostoMes.getPago());
        ret.setTipo(impostoMes.getTipo());
        ret.setVencimento(impostoMes.getVencimento());

        return ret;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public TipoImposto getTipo() {
        return tipo;
    }

    public void setTipo(TipoImposto tipo) {
        this.tipo = tipo;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public Long getValorCentavos() {
        return valorCentavos;
    }

    public void setValorCentavos(Long valorCentavos) {
        this.valorCentavos = valorCentavos;
    }

    public Date getMesAnoReferencia() {
        return mesAnoReferencia;
    }

    public void setMesAnoReferencia(Date mesAnoReferencia) {
        this.mesAnoReferencia = mesAnoReferencia;
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }
}
