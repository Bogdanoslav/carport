package com.rooter.carportv8.exceptions;

import java.util.Locale;

public class BusinessLogicException extends LocalizedException{

    public BusinessLogicException(String messageKey) {
        super(messageKey);
    }

    public BusinessLogicException(String messageKey, Locale locale) {
        super(messageKey, locale);
    }
}
