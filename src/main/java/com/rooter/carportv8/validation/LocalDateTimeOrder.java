package com.rooter.carportv8.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = LocalDateTimeOrderValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface LocalDateTimeOrder {
    String message() default "Incorrect date order";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String start();
    String end();

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        LocalDateTimeOrder[] value();
    }

}
