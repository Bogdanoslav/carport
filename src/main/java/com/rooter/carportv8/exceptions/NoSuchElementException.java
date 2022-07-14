package com.rooter.carportv8.exceptions;

import com.rooter.carportv8.common.ExceptionMessage;
import lombok.Getter;

@Getter
public class NoSuchElementException extends RuntimeException{

    private final String className;
    private final Long entityId;

    public NoSuchElementException(Class<?> clazz, Long entityId) {
        super(ExceptionMessage.ITEM_NOT_FOUND.getKey());
        this.entityId = entityId;
        this.className = clazz.getSimpleName();
    }
}
