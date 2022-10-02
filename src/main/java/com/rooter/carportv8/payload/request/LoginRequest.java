package com.rooter.carportv8.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@ToString(exclude = "password")
public class LoginRequest {
    @NotNull
    @Size(min = 8, max = 32)
    private String username;

    @NotNull
    @Size(min = 8, max = 256)
    private String password;
}
