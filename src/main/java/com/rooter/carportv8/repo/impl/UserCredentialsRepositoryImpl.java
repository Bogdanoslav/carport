package com.rooter.carportv8.repo.impl;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rooter.carportv8.model.QUserCredentials;
import com.rooter.carportv8.model.UserCredentials;
import com.rooter.carportv8.repo.interfaces.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class UserCredentialsRepositoryImpl extends GeneralRepositoryImpl<UserCredentials> implements UserCredentialsRepository {

    private final QUserCredentials qUserCredentials = QUserCredentials.userCredentials;
    private final JPQLQuery<UserCredentials> query = new JPAQuery<>(entityManager)
            .select(qUserCredentials)
            .from(qUserCredentials);

    @Autowired
    public UserCredentialsRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<UserCredentials> findAll(Predicate predicate) {
        return query
                .where(predicate)
                .fetch();
    }

    @Override
    public List<UserCredentials> findAll() {
        return query.
                fetch();
    }

    @Override
    public Optional<UserCredentials> findOne(Predicate predicate) {
        return Optional.ofNullable(
                query
                        .where(predicate)
                        .fetchOne()
        );
    }

    @Override
    public Optional<UserCredentials> findFirst(Predicate predicate) {
        return Optional.ofNullable(
                query
                        .where(predicate)
                        .fetchFirst()
        );
    }

    @Override
    public void delete(Predicate predicate) {
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        query.delete(qUserCredentials)
                .where(predicate)
                .execute();
    }

    @Override
    public boolean exists(Predicate predicate) {
        return !query
                .where(predicate)
                .fetch().isEmpty();
    }
}
