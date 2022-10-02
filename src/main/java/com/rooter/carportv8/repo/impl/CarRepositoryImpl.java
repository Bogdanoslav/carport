package com.rooter.carportv8.repo.impl;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rooter.carportv8.model.*;
import com.rooter.carportv8.model.QCar;
import com.rooter.carportv8.model.QCarBrand;
import com.rooter.carportv8.model.QCarModel;
import com.rooter.carportv8.model.QDriver;
import com.rooter.carportv8.repo.interfaces.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class CarRepositoryImpl extends GeneralRepositoryImpl<Car> implements CarRepository {

    private final QCar qCar = QCar.car;
    private final JPQLQuery<Car> query = new JPAQuery<>(entityManager)
            .select(qCar)
            .from(qCar)
            .innerJoin(qCar.model(), QCarModel.carModel).fetchJoin()
            .innerJoin(QCarModel.carModel.brand(), QCarBrand.carBrand).fetchJoin()
            .innerJoin(qCar.driver(), QDriver.driver).fetchJoin();

    @Autowired
    public CarRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }


    @Override
    public List<Car> findAll(Predicate predicate) {
        return query
                .where(predicate)
                .fetch();
    }

    @Override
    public List<Car> findAll() {
        return query
                .fetch();
    }

    public Optional<Car> findOne(Predicate predicate) {
        return Optional.ofNullable(
                query
                .where(predicate)
                .fetchOne());
    }

    @Override
    public Optional<Car> findFirst(Predicate predicate) {
        return Optional.ofNullable(
                query
                .where(predicate)
                .fetchFirst());
    }

    @Override
    @Transactional
    public void delete(Predicate predicate) {
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        query.delete(qCar)
                .where(predicate)
                .execute();
    }
}
