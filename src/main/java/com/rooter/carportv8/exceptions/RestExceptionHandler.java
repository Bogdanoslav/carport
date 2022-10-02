package com.rooter.carportv8.exceptions;

import com.rooter.carportv8.payload.response.RestMessage;
import org.hibernate.QueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

@ControllerAdvice
public class RestExceptionHandler{
    private static final String UNEXPECTED_ERROR = "Exception.unexpected";
    private final MessageSource messageSource;

    @Autowired
    public RestExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<RestMessage> handleNoSuchElementException(RuntimeException ex, Locale locale){
        RestExceptionMessage restException = new RestExceptionMessage(ex.getMessage(), ZonedDateTime.now(ZoneId.of("Z")), HttpStatus.NOT_FOUND);
        return new ResponseEntity(restException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadCredentialsException.class,PropertyReferenceException.class})
    public ResponseEntity<RestMessage> handleBadCredentialsException(RuntimeException ex, Locale locale){
        RestExceptionMessage restException = new RestExceptionMessage(ex.getMessage(), ZonedDateTime.now(ZoneId.of("Z")), HttpStatus.BAD_REQUEST);
        return new ResponseEntity(restException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(QueryException.class)
    public ResponseEntity<RestMessage> handleQueryException(RuntimeException ex, Locale locale){
        RestExceptionMessage restException = new RestExceptionMessage("Error while executing query", ZonedDateTime.now(ZoneId.of("Z")), HttpStatus.BAD_REQUEST);
        return new ResponseEntity(restException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<RestMessage> handleBusinessLogicException(LocalizedException ex, Locale locale){
        RestExceptionMessage restException = new RestExceptionMessage(ex.getMessage(), ZonedDateTime.now(ZoneId.of("Z")), HttpStatus.CONFLICT);
        return new ResponseEntity(restException, HttpStatus.CONFLICT);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestMessage> handleArgumentNotValidException(MethodArgumentNotValidException ex, Locale locale) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName;
            if(error instanceof FieldError){
                fieldName = ((FieldError) error).getField();
            } else{
                fieldName = error.getObjectName();
            }
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ValidationExceptionMessage validationException = new ValidationExceptionMessage(errors, ZonedDateTime.now(ZoneId.of("Z")), HttpStatus.BAD_REQUEST);
        return new ResponseEntity(validationException, HttpStatus.BAD_REQUEST);
    }
}
