package com.rooter.carportv8.repo.impl;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rooter.carportv8.model.*;
import com.rooter.carportv8.model.QCarModel;
import com.rooter.carportv8.model.QDriver;
import com.rooter.carportv8.model.QPassenger;
import com.rooter.carportv8.repo.interfaces.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class PassengerRepositoryImpl extends GeneralRepositoryImpl<Passenger> implements PassengerRepository {
    private final QPassenger qPassenger = QPassenger.passenger;

    private final JPQLQuery<Passenger> query = new JPAQuery<>(entityManager)
            .select(qPassenger)
            .from(qPassenger);


    @Autowired
    public PassengerRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Passenger> findAll(Predicate predicate) {
        return query
                .where(predicate)
                .fetch();
    }

    @Override
    public List<Passenger> findAll() {
        return query
                .fetch();
    }

    @Override
    public Optional<Passenger> findOne(Predicate predicate) {
        return Optional.ofNullable(
                query
                        .where(predicate)
                        .fetchOne()
        );
    }

    @Override
    public Optional<Passenger> findFirst(Predicate predicate) {
        return Optional.ofNullable(
                query
                        .where(predicate)
                        .fetchFirst()
        );
    }

    @Override
    public void delete(Predicate predicate) {
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        query.delete(qPassenger)
                .where(predicate)
                .execute();
    }
}
