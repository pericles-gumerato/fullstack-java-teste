package com.contabilizei.core.service;

import com.contabilizei.core.requestsandresponses.BuscarClientesRequest;
import com.contabilizei.core.requestsandresponses.BuscarClientesResponse;
import com.contabilizei.core.requestsandresponses.BuscarImpostosMesRequest;
import com.contabilizei.core.requestsandresponses.BuscarImpostosMesResponse;
import com.contabilizei.core.requestsandresponses.BuscarNotasFiscaisRequest;
import com.contabilizei.core.requestsandresponses.BuscarNotasFiscaisResponse;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
public interface ConsultaService {

    /**
     * Consulta clientes de forma paginada.
     *
     * @param request objeto de request.
     * @return objeto de resposta.
     */
    BuscarClientesResponse consultaTodosClientes(BuscarClientesRequest request);

    /**
     * Consulta notas fiscais de um determinado mês de forma paginada.
     *
     * @param request objeto de request.
     * @return objeto de resposta.
     */
    BuscarNotasFiscaisResponse consultaNotasFiscaisMes(BuscarNotasFiscaisRequest request);

    /**
     * Consulta impostos para um determinado mês de forma paginada.
     *
     * @param request objeto de request.
     * @return objeto de resposta.
     */
    BuscarImpostosMesResponse consultaNotasFiscaisMes(BuscarImpostosMesRequest request);
}
