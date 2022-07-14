package com.rooter.carportv8.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RestException  extends RuntimeException{
    private String message;
    private String[] args;
}
