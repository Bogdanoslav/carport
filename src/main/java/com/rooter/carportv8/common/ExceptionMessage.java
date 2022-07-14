package com.rooter.carportv8.common;

public enum ExceptionMessage {
    ITEM_NOT_FOUND("Item.notFound");

    String key;
    ExceptionMessage(String key) {
        this.key = key;
    }

    public String getKey(){
        return key;
    }
}
