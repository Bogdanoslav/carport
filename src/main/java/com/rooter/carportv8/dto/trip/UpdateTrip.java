package com.rooter.carportv8.dto.trip;

import com.rooter.carportv8.model.Address;
import com.rooter.carportv8.model.Car;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class UpdateTrip {
    private Address arrivalAddress;
    private Address departureAddress;
    private Car car;
    private Timestamp departureTime;
    private Timestamp arrivalTime;
    private String about;
    private Integer totalSeats;
    private BigDecimal price;
}
