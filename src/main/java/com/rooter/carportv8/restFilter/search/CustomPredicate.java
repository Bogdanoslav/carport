package com.rooter.carportv8.restFilter.search;

import com.querydsl.core.types.Operator;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.dsl.*;
import com.rooter.carportv8.common.CommonConstants;
import com.rooter.carportv8.model.Trip;
import com.rooter.carportv8.util.DateValidator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isNumeric;

public class CustomPredicate {
    private SearchCriteria criteria;

    public CustomPredicate(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    private final Map<String, Operator> operators = new HashMap<>() {{
        put("=", Ops.EQ);
        put("!=", Ops.NE);
        put(">", Ops.GT);
        put("<", Ops.LT);
        put(">=", Ops.GOE);
        put("<=", Ops.LOE);
    }};


    public BooleanExpression getPredicate() {
        PathBuilder<Trip> entityPath = new PathBuilder<>(Trip.class, "trip");
        DateValidator dateValidator = new DateValidator(CommonConstants.DATE_FORMAT);
        if (isNumeric(criteria.getValue().toString())) {
            NumberPath<Integer> path = entityPath.getNumber(criteria.getKey(), Integer.class);
            Integer value = Integer.parseInt(criteria.getValue().toString());
            return Expressions.predicate(operators.get(criteria.getOperation()),
                    path, Expressions.constant(value));
        }
        else if (dateValidator.isValid(criteria.getValue().toString())) {
            DateTimePath<LocalDateTime> path = entityPath.getDateTime(criteria.getKey(), LocalDateTime.class);
            LocalDate value = LocalDate.parse(criteria.getValue().toString());
            return Expressions.predicate(operators.get(criteria.getOperation()),
                    Expressions.constant(value), Expressions.stringTemplate("FORMATDATETIME({0}, {1})", path, "yyyy-MM-dd"));
        }
        else {
            StringPath path = entityPath.getString(criteria.getKey());
            return Expressions.predicate(operators.get(criteria.getOperation()),
                    path, Expressions.constant(criteria.getValue()));
        }
    }
}
