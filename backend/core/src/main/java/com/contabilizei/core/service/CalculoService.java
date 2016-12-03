package com.contabilizei.core.service;

import com.contabilizei.core.requestsandresponses.CalcularImpostosMesRequest;
import com.contabilizei.core.requestsandresponses.CalcularImpostosMesResponse;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
public interface CalculoService {

    /**
     * Faz o cálculo dos impostos do mês.
     *
     * @param request objeto de request.
     * @return objeto de resposta.
     */
    CalcularImpostosMesResponse calcularImpostosMes(CalcularImpostosMesRequest request);
}
