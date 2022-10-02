package com.rooter.carportv8.dto.driver;

import com.rooter.carportv8.dto.car.GetCar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetDriverProfile {
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private String about;
    private Long drivingExperience;
}
