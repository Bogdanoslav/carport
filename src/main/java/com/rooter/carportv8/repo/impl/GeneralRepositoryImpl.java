package com.rooter.carportv8.repo.impl;

import com.querydsl.core.types.Predicate;
import com.rooter.carportv8.repo.interfaces.CustomCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class GeneralRepositoryImpl<T> implements CustomCrudRepository<T> {

    protected EntityManager entityManager;

    public GeneralRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public T save(T entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

    @Override
    @Transactional
    public T update(T entity, Predicate predicate) {
        entityManager.refresh(entity);
        entityManager.flush();
        return entity;
    }
}
