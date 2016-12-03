package com.contabilizei.model.enums;

import java.util.Optional;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
public enum TipoImposto {

    IRPJ(Optional.of(48L)), ISS(Optional.of(20L)), COFINS(Optional.of(30L)), SIMPLES_NACIONAL(Optional.empty());

    private Optional<Long> aliquotaPormil;

    TipoImposto(Optional<Long> aliquotaPormil) {
        this.aliquotaPormil = aliquotaPormil;
    }

    public Optional<Long> getAliquotaPormil() {
        return aliquotaPormil;
    }

}
