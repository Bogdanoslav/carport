package com.rooter.carportv8.payload.response;

import lombok.Getter;

import java.util.List;

@Getter
public class RestMessage {
    private String message;
    private List<String> messages;

    public RestMessage(String message) {
        this.message = message;
    }

    public RestMessage(List<String> messages) {
        this.messages = messages;
    }
}
