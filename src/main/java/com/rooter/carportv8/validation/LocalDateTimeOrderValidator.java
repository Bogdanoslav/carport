package com.rooter.carportv8.validation;

import com.rooter.carportv8.dto.trip.SaveTrip;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.util.Objects;

public class LocalDateTimeOrderValidator implements ConstraintValidator<LocalDateTimeOrder, SaveTrip> {

    private String start;
    private String end;

    public void initialize(LocalDateTimeOrder localDateTimeOrder) {
        this.start = localDateTimeOrder.start();
        this.end = localDateTimeOrder.end();
    }

    @Override
    public boolean isValid(SaveTrip saveTrip, ConstraintValidatorContext constraintValidatorContext) {
        Object startValue = new BeanWrapperImpl(saveTrip)
                .getPropertyValue(start);
        Object endValue = new BeanWrapperImpl(saveTrip)
                .getPropertyValue(end);
        if(Objects.isNull(startValue)||Objects.isNull(endValue)){
            return false;
        }
        if(!(startValue instanceof LocalDateTime
                && endValue instanceof LocalDateTime)){
            throw new IllegalArgumentException("@DateOrder only applies to LocalDateTime objects");
        }
        LocalDateTime startDate = (LocalDateTime)new BeanWrapperImpl(saveTrip)
                .getPropertyValue(start);
        LocalDateTime endDate = (LocalDateTime)new BeanWrapperImpl(saveTrip)
                .getPropertyValue(end);
        return startDate.isBefore(endDate);
    }


}
