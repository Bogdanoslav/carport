package com.rooter.carportv8.model;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class BookingId implements Serializable {
    private Long passengerId;
    private Long tripId;

    public BookingId() {
    }

    public BookingId(Long passengerId, Long tripId) {
        this.passengerId = passengerId;
        this.tripId = tripId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingId bookingId = (BookingId) o;
        return Objects.equals(passengerId, bookingId.passengerId) && Objects.equals(tripId, bookingId.tripId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passengerId, tripId);
    }

    public Long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }
}
