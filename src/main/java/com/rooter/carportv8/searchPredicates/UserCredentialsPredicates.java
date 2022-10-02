package com.rooter.carportv8.searchPredicates;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.rooter.carportv8.model.QUserCredentials;

public class UserCredentialsPredicates {
    private static QUserCredentials qUserCredentials = QUserCredentials.userCredentials;
    public static BooleanExpression hasUsername(String username){
        return qUserCredentials.username.eq(username);
    }
}
