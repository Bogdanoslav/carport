package com.rooter.carportv8.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ResourceUtil {

    @Autowired
    MessageSource messageSource;

    public String getLocalMessage(String key, String... params){
        return messageSource.getMessage(key,
                params,
                Locale.ENGLISH);
    }
}
