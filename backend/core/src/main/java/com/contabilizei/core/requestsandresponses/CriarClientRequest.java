package com.contabilizei.core.requestsandresponses;

import com.contabilizei.model.enums.Anexo;
import com.contabilizei.model.enums.RegimeTributario;

import java.util.Set;

import javax.validation.constraints.NotNull;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
public class CriarClientRequest {

    @NotNull
    private String razaoSocial;

    @NotNull
    private Long cnpj;

    @NotNull
    private RegimeTributario regimeTributario;

    private Set<Anexo> anexos;

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public Long getCnpj() {
        return cnpj;
    }

    public void setCnpj(Long cnpj) {
        this.cnpj = cnpj;
    }

    public RegimeTributario getRegimeTributario() {
        return regimeTributario;
    }

    public void setRegimeTributario(RegimeTributario regimeTributario) {
        this.regimeTributario = regimeTributario;
    }

    public Set<Anexo> getAnexos() {
        return anexos;
    }

    public void setAnexos(Set<Anexo> anexos) {
        this.anexos = anexos;
    }
}
