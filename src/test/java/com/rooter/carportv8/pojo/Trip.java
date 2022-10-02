package com.rooter.carportv8.pojo;

import com.rooter.carportv8.model.Address;
import com.rooter.carportv8.model.Booking;
import com.rooter.carportv8.model.Car;
import com.rooter.carportv8.model.enums.ETripStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Trip {
    private Long id;

    private Address departureAddress;

    private Address arrivalAddress;

    private Car car;

    private Long carId;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private String about;

    private Integer totalSeats;

    private Integer bookedSeats;

    private BigDecimal price;

    private ETripStatus status;

    private Set<Booking> bookings;

    private Boolean instantBooking = false;

    private LocalDateTime createdAt;
}
