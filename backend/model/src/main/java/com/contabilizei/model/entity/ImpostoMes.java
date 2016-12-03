package com.contabilizei.model.entity;

import com.contabilizei.model.enums.TipoImposto;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
@Entity
@Table(name = "imposto_mes", schema = "contabilizei")
public class ImpostoMes {

    @Id
    @SequenceGenerator(name = "_imposto_mes_id_seq",
            sequenceName = "contabilizei.imposto_mes_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "_imposto_mes_id_seq")
    private Long id;

    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tipo_imposto_id", nullable = false)
    private TipoImposto tipo;

    @Column(name = "vencimento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date vencimento;

    @Column(name = "valor_centavos", nullable = false)
    private Long valorCentavos;

    @Column(name = "mes_ano_referencia", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date mesAnoReferencia;

    @Column(name = "pago", nullable = false)
    private Boolean pago;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdAt;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar updatedAt;

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

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public Calendar getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Calendar updatedAt) {
        this.updatedAt = updatedAt;
    }
}
