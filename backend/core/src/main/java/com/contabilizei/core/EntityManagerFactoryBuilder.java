package com.contabilizei.core;

import com.netflix.config.DynamicIntProperty;
import com.netflix.config.DynamicProperty;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
public class EntityManagerFactoryBuilder {

    private static EntityManagerFactory entityManagerFactoryBuilder = null;

    private EntityManagerFactoryBuilder() {
    }

    public static EntityManager getInstance() {
        if (entityManagerFactoryBuilder == null) {
            entityManagerFactoryBuilder = Persistence.createEntityManagerFactory("contabilizei");
        }
        return entityManagerFactoryBuilder.createEntityManager();
    }
}
