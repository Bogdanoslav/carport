package com.rooter.carportv8.restFilter.search;

import lombok.Getter;

@Getter
public class SortCriteria {
    private final String key;
    private final String order;

    public SortCriteria(String key, String order) {
        this.key = key;
        this.order = order;
    }
}
