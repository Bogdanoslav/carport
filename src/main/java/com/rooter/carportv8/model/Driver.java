package com.rooter.carportv8.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "driver")
@Getter
@Setter
public class Driver extends Person{
    private Long drivingExperience;

    public Driver() {
    }

    @Builder
    public Driver(Long id, String firstName, String lastName, String email, Integer age, String about, UserCredentials userCredentials, Long drivingExperience) {
        super(id, firstName, lastName, email, age, about, userCredentials);
        this.drivingExperience = drivingExperience;
    }

    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY)
    @JsonManagedReference("driver-car")
    private Set<Car> cars;
}
