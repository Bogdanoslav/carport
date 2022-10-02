package com.rooter.carportv8.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "passenger")
@Getter
@Setter
public class Passenger extends Person{

    @OneToMany(mappedBy = "passenger", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Booking> bookings;

    public Passenger() {
        super();
    }

    @Builder
    public Passenger(Long id, String firstName, String lastName, String email, Integer age, String about, UserCredentials userCredentials, Set<Booking> bookings) {
        super(id, firstName, lastName, email, age, about, userCredentials);
        this.bookings = bookings;
    }
}
