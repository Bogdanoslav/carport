package com.rooter.carportv8.payload.request;

import com.rooter.carportv8.model.Role;
import lombok.Data;
import lombok.ToString;

import java.util.Set;

@Data
@ToString(exclude = "password")
public class SignupRequest {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
}
