package com.rooter.carportv8.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rooter.carportv8.model.enums.ETripStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "trip")
@Getter @Setter
public class Trip extends BaseEntity{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "departure_address_id")
    private Address departureAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "arrival_address_id")
    private Address arrivalAddress;

    @JoinColumn(name = "car_id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = Car.class, fetch = FetchType.EAGER)
    private Car car;

    @Column(name = "car_id")
    private Long carId;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @Column(name = "about")
    private String about;

    @Column(name = "total_seats")
    private Integer totalSeats;

    @Column(name = "seats_left")
    private Integer seatsLeft;

    @Column(name = "price")
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private ETripStatus status;

    @OneToMany(mappedBy = "trip")
    private List<Booking> bookings;

    @Column(name = "instant_booking")
    private Boolean instantBooking = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Trip() {
    }

    @Builder

    public Trip(Long id, Address departureAddress, Address arrivalAddress, Car car, Long carId, LocalDateTime departureTime, LocalDateTime arrivalTime, String about, Integer totalSeats, Integer seatsLeft, BigDecimal price, ETripStatus status, List<Booking> bookings, Boolean instantBooking, LocalDateTime createdAt) {
        super(id);
        this.departureAddress = departureAddress;
        this.arrivalAddress = arrivalAddress;
        this.car = car;
        this.carId = carId;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.about = about;
        this.totalSeats = totalSeats;
        this.seatsLeft = seatsLeft;
        this.price = price;
        this.status = status;
        this.bookings = bookings;
        this.instantBooking = instantBooking;
        this.createdAt = createdAt;
    }
}
