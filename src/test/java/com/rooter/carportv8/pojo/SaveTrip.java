package com.rooter.carportv8.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rooter.carportv8.dto.trip.SaveAddress;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaveTrip {
    private SaveAddress arrivalAddress;
    private SaveAddress departureAddress;
    private Long carId;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String about;
    private Integer totalSeats;
    private BigDecimal price;
    private Boolean instantBooking;

    @Builder
    public SaveTrip(SaveAddress arrivalAddress, SaveAddress departureAddress, Long carId, LocalDateTime departureTime, LocalDateTime arrivalTime, String about, Integer totalSeats, BigDecimal price, Boolean instantBooking) {
        this.arrivalAddress = arrivalAddress;
        this.departureAddress = departureAddress;
        this.carId = carId;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.about = about;
        this.totalSeats = totalSeats;
        this.price = price;
        this.instantBooking = instantBooking;
    }
}
