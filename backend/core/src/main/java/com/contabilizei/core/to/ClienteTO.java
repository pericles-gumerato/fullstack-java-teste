package com.contabilizei.core.to;

import com.contabilizei.model.entity.Cliente;
import com.contabilizei.model.enums.RegimeTributario;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
public class ClienteTO {

    private Long id;

    private String razaoSocial;

    private Long cnpj;

    private RegimeTributario regimeTributario;

    public static ClienteTO fromCliente(Cliente cliente) {
        ClienteTO ret = new ClienteTO();
        ret.setId(cliente.getId());
        ret.setCnpj(cliente.getCnpj());
        ret.setRazaoSocial(cliente.getRazaoSocial());
        ret.setRegimeTributario(cliente.getRegimeTributario());

        return ret;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

}
