package com.rooter.carportv8.dto.trip;

import com.rooter.carportv8.validation.LocalDateTimeOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter
@LocalDateTimeOrder(start = "departureTime", end = "arrivalTime")
public class SaveTrip {
    @NotNull
    private SaveAddress arrivalAddress;
    @NotNull
    private SaveAddress departureAddress;
    @NotNull
    @Min(0)
    private Long carId;
    @NotNull
    @Future
    private LocalDateTime departureTime;
    @NotNull
    @Future
    private LocalDateTime arrivalTime;
    @Size(min = 0, max = 280)
    private String about;
    @NotNull
    @Min(0)
    private Integer totalSeats;
    @NotNull
    @Min(0)
    private BigDecimal price;
    @NotNull
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
