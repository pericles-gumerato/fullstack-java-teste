package com.contabilizei.model.enums;

import java.util.Optional;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
public enum TipoImposto {

    IRPJ(Optional.of(480)), ISS(Optional.of(200)), COFINS(Optional.of(300)), SIMPLES_NACIONAL(Optional.empty());

    private Optional<Integer> aliquotaPormil;

    TipoImposto(Optional<Integer> aliquotaPormil) {
        this.aliquotaPormil = aliquotaPormil;
    }

    public Optional<Integer> getAliquotaPormil() {
        return aliquotaPormil;
    }

}
