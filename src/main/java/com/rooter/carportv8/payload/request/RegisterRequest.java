package com.rooter.carportv8.payload.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.*;

@Data
@ToString(exclude = "password")
public class RegisterRequest {
    @NotNull
    @Size(min = 0, max = 280)
    private String username;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 0, max = 280)
    private String about;

    @NotNull
    @Min(18)
    private Integer age;

    @NotNull
    @Size(min = 8, max = 256)
    private String password;

    @NotNull
    @Size(min = 8, max = 256)
    private String confirmPassword;
}
