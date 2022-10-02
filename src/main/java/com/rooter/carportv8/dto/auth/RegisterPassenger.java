package com.rooter.carportv8.dto.auth;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class RegisterPassenger {
    @NotNull
    @Size(min = 0, max = 280)
    private String username;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

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
