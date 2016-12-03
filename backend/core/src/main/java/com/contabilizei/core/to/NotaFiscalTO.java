package com.contabilizei.core.to;

import com.contabilizei.model.entity.NotaFiscal;
import com.contabilizei.model.enums.Anexo;

import java.util.Date;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
public class NotaFiscalTO {

    private Long id;

    private Long clienteId;

    private Long numero;

    private Date dataEmissao;

    private String descricao;

    private Long valorCentavos;

    private Anexo anexo;

    public static NotaFiscalTO fromNotaFiscal(NotaFiscal notaFiscal) {
        NotaFiscalTO ret = new NotaFiscalTO();
        ret.setId(notaFiscal.getId());
        ret.setValorCentavos(notaFiscal.getValorCentavos());
        ret.setNumero(notaFiscal.getNumero());
        ret.setDescricao(notaFiscal.getDescricao());
        ret.setDataEmissao(notaFiscal.getDataEmissao());
        ret.setNumero(notaFiscal.getNumero());
        ret.setClienteId(notaFiscal.getClienteId());

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

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getValorCentavos() {
        return valorCentavos;
    }

    public void setValorCentavos(Long valorCentavos) {
        this.valorCentavos = valorCentavos;
    }

    public Anexo getAnexo() {
        return anexo;
    }

    public void setAnexo(Anexo anexo) {
        this.anexo = anexo;
    }
}
