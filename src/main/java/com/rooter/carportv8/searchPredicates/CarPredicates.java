package com.rooter.carportv8.searchPredicates;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.rooter.carportv8.model.enums.ECarStatus;
import com.rooter.carportv8.model.QCar;

public final class CarPredicates {
    private static final QCar car = QCar.car;

    public static BooleanExpression hasId(Long id){
        return car.id.eq(id);
    }

    public static BooleanExpression hasDriverId(Long id){
        return car.driverId.eq(id);
    }
    public static BooleanExpression hasStatus(ECarStatus eCarStatus){
        return car.status.eq(eCarStatus);
    }
}
