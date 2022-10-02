package com.rooter.carportv8.searchPredicates;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.rooter.carportv8.model.enums.ETripStatus;
import com.rooter.carportv8.model.QTrip;

public final class TripPredicates {
    private static final QTrip qTrip = QTrip.trip;

    public static BooleanExpression hasId(Long id){
        return qTrip.id.eq(id);
    }

    public static BooleanExpression hasStatus(ETripStatus eTripStatus){
        return qTrip.status.eq(eTripStatus);
    }
    public static BooleanExpression hasCarId(Long id){
        return qTrip.carId.eq(id);
    }
}
