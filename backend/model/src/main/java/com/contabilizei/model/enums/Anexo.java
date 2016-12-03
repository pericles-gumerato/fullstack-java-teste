package com.contabilizei.model.enums;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
public enum Anexo {

    COMERCIO(60L), INDUSTRIA(85L), PRESTACAO_DE_SERVICOS(110L);

    private long aliquotaPormil;

    Anexo(long aliquotaPormil) {
        this.aliquotaPormil = aliquotaPormil;
    }

    public long getAliquotaPormil() {
        return aliquotaPormil;
    }
}
