package com.rooter.carportv8.restFilter.search;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.rooter.carportv8.model.Trip;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CustomSortBuilder {
    private SortCriteria sortCriteria;
    private final Map<String, Order> stringOrderMap = new HashMap<>(){{
        put("ASC", Order.ASC);
        put("DESC", Order.DESC);
    }};

    public OrderSpecifier<?> build(){
        if(Objects.isNull(sortCriteria)){
            return null;
        }
        PathBuilder<?> entityPath = new PathBuilder<>(Trip.class, Trip.class.getSimpleName().toLowerCase());
        Order order = stringOrderMap.get(sortCriteria.getOrder());
        PathBuilder<?> fieldPath = entityPath.get(sortCriteria.getKey());
        return new OrderSpecifier(order,fieldPath);
    }

    public void parseSort(String sort){
        if (sort != null) {
            Pattern searchPattern = Pattern.compile("(\\w+)\\((ASC|DESC)\\)");
            Matcher searchMatcher = searchPattern.matcher(sort + ",");
            searchMatcher.find();
            sortCriteria = new SortCriteria(searchMatcher.group(1),searchMatcher.group(2));
        }
    }
}
