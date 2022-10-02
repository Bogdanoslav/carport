package com.rooter.carportv8.searchPredicates;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.rooter.carportv8.model.QPassenger;

public final class PassengerPredicates {
    private static final QPassenger qPassenger = QPassenger.passenger;

    public static BooleanExpression hasId(Long id){
        return qPassenger.id.eq(id);
    }
}
