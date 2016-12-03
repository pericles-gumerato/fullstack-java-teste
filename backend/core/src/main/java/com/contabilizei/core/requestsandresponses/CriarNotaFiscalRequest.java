package com.contabilizei.core.requestsandresponses;

import com.contabilizei.model.enums.Anexo;

import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
public class CriarNotaFiscalRequest {

    @NotNull
    private Long clienteId;

    @NotNull
    private Long numero;

    @NotNull
    private Date dataEmissao;

    @NotNull
    private String descricao;

    @NotNull
    private Long valorCentavos;

    @NotNull
    private Anexo anexo;

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
