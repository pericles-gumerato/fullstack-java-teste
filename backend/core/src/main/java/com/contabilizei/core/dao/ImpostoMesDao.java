package com.contabilizei.core.dao;

import com.contabilizei.model.entity.ImpostoMes;

import java.util.Date;
import java.util.List;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
public interface ImpostoMesDao {

    /**
     * Marca um determinado imposto como pago.
     *
     * @param impostoMesId id do imposto.
     * @param clienteId    id do cliente para check.
     * @return true se operação foi bem sucedida, false caso contrário.
     */
    boolean marcarImpostoMesComoPago(Long impostoMesId, Long clienteId);

    /**
     * Consulta os impostos de um determinado mês.
     *
     * @param clienteId        id do cliente
     * @param anoMesReferencia mes e ano de referência.
     * @param pageSize         tamanho máximo da página.
     * @param pageNumber       número da página.
     * @return lista de resultados.
     */
    List<ImpostoMes> consultaImpostosMes(Long clienteId, Date anoMesReferencia, int pageSize, int pageNumber);
}
