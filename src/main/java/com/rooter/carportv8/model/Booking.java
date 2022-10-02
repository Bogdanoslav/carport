package com.rooter.carportv8.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rooter.carportv8.model.enums.EBookingStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking",
        uniqueConstraints = { @UniqueConstraint(columnNames =
                { "trip_id", "passenger_id" }) })
@Getter @Setter
public class Booking extends BaseEntity{

    @Column(name = "passenger_id")
    private Long passengerId;

    @Column(name = "trip_id")
    private Long tripId;

    @ManyToOne(targetEntity = Passenger.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "passenger_id", insertable = false, updatable = false)
    private Passenger passenger;

    @ManyToOne(targetEntity = Trip.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "trip_id", insertable = false, updatable = false)
    private Trip trip;

    @Enumerated(EnumType.STRING)
    private EBookingStatus status;

    @Column(name = "booked_at")
    private LocalDateTime bookedAt;

    public Booking() {
    }

    @Builder

    public Booking(Passenger passenger,
                   Long passengerId,
                   Trip trip,
                   Long tripId,
                   EBookingStatus status,
                   LocalDateTime bookedAt) {
        this.passenger = passenger;
        this.passengerId = passengerId;
        this.trip = trip;
        this.tripId = tripId;
        this.status = status;
        this.bookedAt = bookedAt;
    }
}
