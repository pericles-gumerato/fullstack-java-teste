package com.contabilizei.core.dao.impl;

import com.contabilizei.core.EntityManagerFactoryBuilder;
import com.contabilizei.core.dao.ImpostoMesDao;
import com.contabilizei.core.util.DateUtils;
import com.contabilizei.model.entity.Cliente;
import com.contabilizei.model.entity.ImpostoMes;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
public class ImpostoMesDaoImpl implements ImpostoMesDao {

    EntityManagerFactory entityManagerFactory;

    public ImpostoMesDaoImpl() {
        this.entityManagerFactory = EntityManagerFactoryBuilder.getInstance().getEntityManagerFactory();
    }

    @Override
    public ImpostoMes criarImpostoMes(ImpostoMes impostoMes) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Calendar now = Calendar.getInstance();
        impostoMes.setCreatedAt(now);
        impostoMes.setUpdatedAt(now);

        entityManager.persist(impostoMes);
        entityManager.flush();

        entityManager.getTransaction().commit();
        entityManager.close();

        return impostoMes;
    }

    @Override
    public boolean marcarImpostoMesComoPago(Long impostoMesId, Long clienteId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();


        Query query = entityManager.createQuery("UPDATE ImpostoMes i SET i.pago = true WHERE i.id = ?1 AND i.clienteId = ?2");
        query.setParameter(1, impostoMesId);
        query.setParameter(2, clienteId);

        int result = query.executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();

        return result == 1;
    }

    @Override
    public List<ImpostoMes> consultaImpostosMes(Long clienteId, Date mesAnoReferencia, int pageSize, int pageNumber) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("SELECT i FROM ImpostoMes i WHERE i.clienteId = ?1 AND i.mesAnoReferencia >= ?2 AND i.mesAnoReferencia <= ?3");
        query.setParameter(1, clienteId);
        query.setParameter(2, DateUtils.comecoDoMes(mesAnoReferencia));
        query.setParameter(3, DateUtils.finalDoMes(mesAnoReferencia));

        List<ImpostoMes> impostosMes = query.setMaxResults(pageSize).setFirstResult(pageNumber * pageSize - pageSize).getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return impostosMes;
    }

}
