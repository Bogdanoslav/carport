package com.rooter.carportv8.dto.trip;

import com.rooter.carportv8.dto.car.GetCar;
import com.rooter.carportv8.dto.driver.GetDriverProfile;
import com.rooter.carportv8.model.Address;
import com.rooter.carportv8.model.enums.ETripStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class GetTrip {
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Address departureAddress;
    private Address arrivalAddress;
    private GetDriverProfile driver;
    private GetCar car;
    private String about;
    private Integer totalSeats;
    private Integer seatsLeft;
    private BigDecimal price;
    private ETripStatus status;
    private LocalDateTime createdAt;
}
