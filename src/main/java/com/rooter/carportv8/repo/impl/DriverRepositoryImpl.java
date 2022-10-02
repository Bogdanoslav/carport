package com.rooter.carportv8.repo.impl;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rooter.carportv8.model.Car;
import com.rooter.carportv8.model.Driver;
import com.rooter.carportv8.model.Passenger;
import com.rooter.carportv8.model.QDriver;
import com.rooter.carportv8.repo.interfaces.DriverRepository;
import com.rooter.carportv8.repo.interfaces.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class DriverRepositoryImpl extends GeneralRepositoryImpl<Driver> implements DriverRepository {

    private final QDriver qDriver = QDriver.driver;

    private final JPQLQuery<Driver> query = new JPAQuery<>(entityManager)
            .select(qDriver)
            .from(qDriver);

    @Autowired
    public DriverRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Driver> findAll(Predicate predicate) {
        return query
                .where(predicate)
                .fetch();
    }

    @Override
    public List<Driver> findAll() {
        return query
                .fetch();
    }

    @Override
    public Optional<Driver> findOne(Predicate predicate) {
        return Optional.ofNullable(
                query
                        .where(predicate)
                        .fetchOne()
        );
    }

    @Override
    public Optional<Driver> findFirst(Predicate predicate) {
        return Optional.ofNullable(
                query
                        .where(predicate)
                        .fetchFirst()
        );
    }

    @Override
    public void delete(Predicate predicate) {
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        query.delete(qDriver)
                .where(predicate)
                .execute();
    }
}
