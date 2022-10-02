package com.rooter.carportv8.repo.interfaces;

import com.querydsl.core.types.Predicate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CustomCrudRepository<T>{
    List<T> findAll(Predicate predicate);
    List<T> findAll();
    Optional<T> findOne(Predicate predicate);
    Optional<T> findFirst(Predicate predicate);
    T save(T entity);
    void delete(Predicate predicate);
    T update(T entity,Predicate predicate);
}
