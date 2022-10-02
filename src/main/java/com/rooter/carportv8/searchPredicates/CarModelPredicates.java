package com.rooter.carportv8.searchPredicates;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.rooter.carportv8.model.QCarModel;

public final class CarModelPredicates {
    private static final QCarModel carModel = QCarModel.carModel;

    public static BooleanExpression hasBrandId(Long id){
        return carModel.brand().id.eq(id);
    }
}
