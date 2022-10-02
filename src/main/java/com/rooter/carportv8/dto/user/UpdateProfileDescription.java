package com.rooter.carportv8.dto.user;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class UpdateProfileDescription {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Email
    private String email;

    @Min(18)
    private Integer age;

    @Size(min = 0, max = 280)
    private String about;
}
