package com.rooter.carportv8.service;

import com.rooter.carportv8.model.Booking;

import java.util.List;

public interface BookingService {
    Booking getById(Long bookingId, Long userId);
    Booking book(Long tripId, Long userId);
    void cancel(Long bookingId, Long userId);
    Booking reject(Long bookingId, Long userId);

    List<Booking> getAllByTripId(Long tripId);

    List<Booking>  getAllByUserId(Long id);
}
