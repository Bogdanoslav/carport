package com.rooter.carportv8.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class RestExceptionMessage {
    private final String message;
    private final ZonedDateTime zonedDateTime;
    private final HttpStatus httpStatus;
}
