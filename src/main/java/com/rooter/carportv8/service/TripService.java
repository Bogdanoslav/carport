package com.rooter.carportv8.service;

import com.rooter.carportv8.dto.trip.SaveTrip;
import com.rooter.carportv8.model.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TripService {
    Trip getById(Long tripId);

    Page<Trip> search(String search, Pageable pageable);

    Trip save(SaveTrip saveTrip, Long userId);

    Trip cancel(Long tripId, Long userId);

}
