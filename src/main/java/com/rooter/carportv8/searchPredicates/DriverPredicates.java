package com.rooter.carportv8.searchPredicates;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.rooter.carportv8.model.QDriver;

public final class DriverPredicates {
    private static final QDriver driver = QDriver.driver;

    public static BooleanExpression hasId(Long id){
        return driver.id.eq(id);
    }
}
