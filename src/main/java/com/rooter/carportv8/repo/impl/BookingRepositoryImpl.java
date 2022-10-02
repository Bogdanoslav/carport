package com.rooter.carportv8.repo.impl;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rooter.carportv8.model.Booking;
import com.rooter.carportv8.model.QBooking;
import com.rooter.carportv8.model.QPassenger;
import com.rooter.carportv8.repo.interfaces.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class BookingRepositoryImpl extends GeneralRepositoryImpl<Booking> implements BookingRepository {
    private final QBooking qBooking = QBooking.booking;
    private final JPQLQuery<Booking> query = new JPAQuery<>(entityManager)
            .select(qBooking)
            .from(qBooking)
            .innerJoin(qBooking.passenger(), QPassenger.passenger).fetchJoin();

    @Autowired
    public BookingRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Booking> findAll(Predicate predicate) {
        return query
                .where(predicate)
                .fetch();
    }

    @Override
    public List<Booking> findAll() {
        return query
                .fetch();
    }

    @Override
    public Optional<Booking> findOne(Predicate predicate) {
        return Optional.ofNullable(query
                .where(predicate)
                .fetchOne());
    }

    @Override
    public Optional<Booking> findFirst(Predicate predicate) {
        return Optional.ofNullable(query
                .where(predicate)
                .fetchFirst());
    }

    @Override
    public void delete(Predicate predicate) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        queryFactory.delete(qBooking).where(predicate).execute();
    }

    @Override
    public boolean exists(Predicate predicate) {
        return !query
                .where(predicate)
                .fetch().isEmpty();
    }
}
