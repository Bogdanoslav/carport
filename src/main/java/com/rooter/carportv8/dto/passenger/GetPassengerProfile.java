package com.rooter.carportv8.dto.passenger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetPassengerProfile {
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private String about;
}
