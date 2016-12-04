package com.contabilizei.core.dao.impl;

import com.contabilizei.core.EntityManagerFactoryBuilder;
import com.contabilizei.core.dao.ClienteDao;
import com.contabilizei.model.entity.Cliente;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
public class ClienteDaoImpl implements ClienteDao {

    EntityManagerFactory entityManagerFactory;

    public ClienteDaoImpl() {
        this.entityManagerFactory = EntityManagerFactoryBuilder.getInstance().getEntityManagerFactory();
    }

    @Override
    public Cliente criarCliente(Cliente cliente) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Calendar now = Calendar.getInstance();
        cliente.setCreatedAt(now);
        cliente.setUpdatedAt(now);

        entityManager.persist(cliente);
        entityManager.flush();

        entityManager.getTransaction().commit();
        entityManager.close();

        return cliente;
    }

    @Override
    public Optional<Cliente> buscarCliente(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Cliente cliente = entityManager.find(Cliente.class, id);

        entityManager.getTransaction().commit();
        entityManager.close();

        if (Objects.isNull(cliente)) {
            return Optional.empty();
        }
        return Optional.of(cliente);
    }

    @Override
    public List<Cliente> buscarTodosClientesSemAnexos(int pageSize, int pageNumber) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("SELECT c FROM Cliente c");
        List<Cliente> clientes = query.setMaxResults(pageSize).setFirstResult(pageNumber * pageSize - pageSize).getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return clientes;
    }


}
