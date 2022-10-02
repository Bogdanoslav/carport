package com.rooter.carportv8.repo.impl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilderFactory;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rooter.carportv8.model.*;
import com.rooter.carportv8.model.QBooking;
import com.rooter.carportv8.model.QCar;
import com.rooter.carportv8.model.QCarBrand;
import com.rooter.carportv8.model.QCarModel;
import com.rooter.carportv8.model.QDriver;
import com.rooter.carportv8.model.QTrip;
import com.rooter.carportv8.repo.interfaces.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class TripRepositoryImpl extends GeneralRepositoryImpl<Trip> implements TripRepository {
    private final QTrip qTrip = QTrip.trip;
    private final JPQLQuery<Trip> query = new JPAQuery<>(entityManager)
                .select(qTrip).distinct().from(qTrip)
                .leftJoin(qTrip.bookings, QBooking.booking).fetchJoin()
                .innerJoin(qTrip.car(), QCar.car).fetchJoin()
                .innerJoin(QCar.car.model(), QCarModel.carModel).fetchJoin()
                .innerJoin(QCarModel.carModel.brand(), QCarBrand.carBrand).fetchJoin()
                .innerJoin(QCar.car.driver(), QDriver.driver).fetchJoin();


    @Autowired
    public TripRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Page<Trip> findAll(Predicate predicate, Pageable pageable) {
        Querydsl querydsl = new Querydsl(entityManager, (new PathBuilderFactory()).create(Trip.class));
        JPQLQuery<Trip> query = new JPAQuery<>(entityManager);
        long totalElements = query
                .where(predicate)
                .fetchCount();
        List<Trip> result = querydsl.applyPagination(pageable, query).fetch();
        return new PageImpl<>(result, pageable, totalElements);
    }


    @Override
    public List<Trip> findAll(Predicate predicate) {
        return query
                .where(predicate)
                .fetch();
    }

    @Override
    public List<Trip> findAll() {
        JPQLQuery<Trip> query = new JPAQuery<>(entityManager);
        return query
                .fetch();
    }

    @Override
    public Optional<Trip> findOne(Predicate predicate) {
        return Optional.ofNullable(query
                .where(predicate)
                .fetchOne());
    }

    @Override
    public Optional<Trip> findFirst(Predicate predicate) {
        return Optional.ofNullable(query
                .where(predicate)
                .fetchOne());
    }

    @Override
    public void delete(Predicate predicate) {
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        query.delete(qTrip)
                .where(predicate)
                .execute();
    }
}
