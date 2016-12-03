package com.contabilizei.core.dao.impl;

import com.contabilizei.core.EntityManagerFactoryBuilder;
import com.contabilizei.core.dao.NotaFiscalDao;
import com.contabilizei.core.util.DateUtils;
import com.contabilizei.model.entity.NotaFiscal;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
public class NotaFiscalDaoImpl implements NotaFiscalDao {

    EntityManagerFactory entityManagerFactory;

    public NotaFiscalDaoImpl() {
        this.entityManagerFactory = EntityManagerFactoryBuilder.getInstance().getEntityManagerFactory();
    }

    @Override
    public NotaFiscal criarNotaFiscal(NotaFiscal notaFiscal) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Calendar now = Calendar.getInstance();
        notaFiscal.setCreatedAt(now);
        notaFiscal.setUpdatedAt(now);

        entityManager.persist(notaFiscal);

        entityManager.getTransaction().commit();
        entityManager.close();

        return notaFiscal;
    }

    @Override
    public List<NotaFiscal> consultaNotasFiscais(Long clienteId, Date mesAnoReferencia, int pageSize, int pageNumber) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("SELECT nf FROM NotaFiscal nf WHERE nf.clienteId = ?1 AND nf.dataEmissao >= ?2 AND nf.dataEmissao <= ?3");
        query.setParameter(1, clienteId);
        query.setParameter(2, DateUtils.comecoDoMes(mesAnoReferencia));
        query.setParameter(3, DateUtils.finalDoMes(mesAnoReferencia));

        List<NotaFiscal> notasFiscais = query.setMaxResults(pageSize).setFirstResult(pageNumber * pageSize - pageSize).getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return notasFiscais;
    }

}
