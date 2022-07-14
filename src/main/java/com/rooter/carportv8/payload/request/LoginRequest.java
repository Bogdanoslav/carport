package com.rooter.carportv8.payload.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString(exclude = "password")
public class LoginRequest {
    private String username;
    private String password;

}
