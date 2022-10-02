package com.rooter.carportv8.repo.interfaces;

import com.querydsl.core.types.Predicate;
import com.rooter.carportv8.model.UserCredentials;

public interface UserCredentialsRepository extends CustomCrudRepository<UserCredentials> {
    boolean exists(Predicate predicate);
}
