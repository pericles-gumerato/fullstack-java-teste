package com.contabilizei.core.dao;

import com.contabilizei.model.entity.Cliente;

import java.util.List;
import java.util.Optional;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
public interface ClienteDao {

    /**
     * Cria um novo cliente.
     *
     * @param cliente dados do novo cliente
     */
    Cliente criarCliente(Cliente cliente);

    /**
     * Busca um cliente por id
     *
     * @param id id do cliente
     */
    Optional<Cliente> buscarCliente(Long id);

    /**
     * Busca todos os clientes de forma paginada sem buscar o conjunto de anexos.
     *
     * @param pageSize   tamanho da página.
     * @param pageNumber número da página.
     * @return lista com os clientes.
     */
    List<Cliente> buscarTodosClientesSemAnexos(int pageSize, int pageNumber);
}
