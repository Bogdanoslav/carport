package com.rooter.carportv8.restFilter.search;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequestScope
public class CustomPredicateBuilder {
    private final List<SearchCriteria> params;

    public CustomPredicateBuilder() {
        params = new ArrayList<>();
    }

    public CustomPredicateBuilder with(
            String key, String operation, Object value) {

        params.add(new SearchCriteria(key, operation, value));
        return this;
    }


    public BooleanExpression build() {
        if (params.size() == 0) {
            return null;
        }

        List<BooleanExpression> predicates = params.stream().map(param -> {
            CustomPredicate predicate = new CustomPredicate(param);
            return predicate.getPredicate();
        }).filter(Objects::nonNull).toList();

        BooleanExpression result = Expressions.asBoolean(true).isTrue();
        for (BooleanExpression predicate : predicates) {
            result = result.and(predicate);
        }
        return result;
    }

    public void parseSearch(String search){
        if (search != null) {
            Pattern searchPattern = Pattern.compile("(\\w+\\.*\\w*)([=><]|!=|>=|<=)([\\w-]+)");
            Matcher searchMatcher = searchPattern.matcher(search + ",");

            while (searchMatcher.find()) {
                with(searchMatcher.group(1), searchMatcher.group(2), searchMatcher.group(3));
            }
        }
    }
}
