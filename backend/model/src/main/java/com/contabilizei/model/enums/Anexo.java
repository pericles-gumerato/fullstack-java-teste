package com.contabilizei.model.enums;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
public enum Anexo {

    COMERCIO(600), INDUSTRIA(850), PRESTACAO_DE_SERVICOS(1100);

    private int aliquotaPormil;

    Anexo(int aliquotaPormil) {
        this.aliquotaPormil = aliquotaPormil;
    }

    public int getAliquotaPormil() {
        return aliquotaPormil;
    }
}
