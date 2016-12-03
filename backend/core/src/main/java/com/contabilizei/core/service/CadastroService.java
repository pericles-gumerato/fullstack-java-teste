package com.contabilizei.core.service;

import com.contabilizei.core.requestsandresponses.CriarClientRequest;
import com.contabilizei.core.requestsandresponses.CriarClientResponse;
import com.contabilizei.core.requestsandresponses.CriarNotaFiscalRequest;
import com.contabilizei.core.requestsandresponses.CriarNotaFiscalResponse;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
public interface CadastroService {

    /**
     * Cria um novo cliente.
     *
     * @param request objeto de request.
     * @return objeto de resposta.
     */
    CriarClientResponse criarCliente(CriarClientRequest request);

    /**
     * Cria uma nona nota fiscal.
     *
     * @param request objeto de request.
     * @return objeto de resposta.
     */
    CriarNotaFiscalResponse criarNotaFiscal(CriarNotaFiscalRequest request);
}
