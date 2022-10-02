package com.rooter.carportv8.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.Map;

@AllArgsConstructor
@Getter
public class ValidationExceptionMessage {
    private final Map<String, String> message;
    private final ZonedDateTime zonedDateTime;
    private final HttpStatus httpStatus;
}
