package com.contabilizei.core.dao;

import com.contabilizei.model.entity.NotaFiscal;

import java.util.Date;
import java.util.List;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
public interface NotaFiscalDao {
    /**
     * Cria uma nova nota fiscal associada ao cliente com id informado.
     *
     * @param notaFiscal Nota fiscal a ser criada.
     */
    NotaFiscal criarNotaFiscal(NotaFiscal notaFiscal);

    /**
     * Consulta as notas fiscais do cliente com o id informado.
     *
     * @param clienteId        ID do cliente.
     * @param anoMesReferencia data contendo o ano e mês para a busca.
     * @param pageSize         tamanho da página.
     * @param pageNumber       número da página.
     * @return lista contendo as notas fiscais encontradas.
     */
    List<NotaFiscal> consultaNotasFiscais(Long clienteId, Date anoMesReferencia, int pageSize, int pageNumber);
}
