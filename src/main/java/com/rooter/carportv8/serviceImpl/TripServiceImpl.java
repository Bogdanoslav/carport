package com.rooter.carportv8.serviceImpl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.rooter.carportv8.dto.trip.SaveTrip;
import com.rooter.carportv8.exceptions.BusinessLogicException;
import com.rooter.carportv8.mapper.TripMapper;
import com.rooter.carportv8.model.Booking;
import com.rooter.carportv8.model.Car;
import com.rooter.carportv8.model.enums.EBookingStatus;
import com.rooter.carportv8.model.enums.ETripStatus;
import com.rooter.carportv8.model.Trip;
import com.rooter.carportv8.repo.interfaces.CarRepository;
import com.rooter.carportv8.repo.interfaces.TripRepository;
import com.rooter.carportv8.restFilter.search.CustomPredicateBuilder;
import com.rooter.carportv8.searchPredicates.TripPredicates;
import com.rooter.carportv8.service.CarService;
import com.rooter.carportv8.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;
    private final TripMapper tripMapper;
    private final CustomPredicateBuilder predicateBuilder;
    private final CarService carService;

    @Autowired
    public TripServiceImpl(TripRepository tripRepository, TripMapper tripMapper, CustomPredicateBuilder predicateBuilder, CarRepository carRepository, CarService carService) {
        this.tripRepository = tripRepository;
        this.tripMapper = tripMapper;
        this.predicateBuilder = predicateBuilder;
        this.carService = carService;
    }

    @Override
    public Trip getById(Long tripId) {
        return tripRepository.findOne(TripPredicates.hasId(tripId))
                .orElseThrow(() -> new NoSuchElementException(String.format("No record of %s could be found with id %d.", Trip.class.getSimpleName(), tripId)));
    }

    @Override
    public Page<Trip> search(String search, Pageable pageable) {
        predicateBuilder.parseSearch(search);
        BooleanExpression searchPredicates = predicateBuilder.build();
        TripPredicates.hasStatus(ETripStatus.SCHEDULED).and(searchPredicates);
        return tripRepository.findAll(searchPredicates, pageable);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Trip save(SaveTrip saveTrip, Long userId) {
        Car car = carService.getCar(saveTrip.getCarId(), userId);
        if (!Objects.equals(car.getDriverId(), userId)) {
            throw new AccessDeniedException("UNAUTHORIZED");
        }
        Trip trip = new Trip();
        tripMapper.save(trip, saveTrip);
        trip.setStatus(ETripStatus.SCHEDULED);
        trip.setSeatsLeft(0);
        trip.setCreatedAt(LocalDateTime.now());
        trip = tripRepository.save(trip);
        return trip;
    }

    @Override
    public Trip cancel(Long tripId, Long userId) {
        Trip trip = tripRepository.findOne(TripPredicates.hasId(tripId))
                .orElseThrow(() -> new NoSuchElementException(String.format("No record of %s could be found with id %d.", Trip.class.getSimpleName(), tripId)));
        if (!userId.equals(trip.getCar().getDriverId())) {
            throw new AccessDeniedException("UNAUTHORIZED");
        }
        if (!ETripStatus.SCHEDULED.equals(trip.getStatus())) {
            throw new BusinessLogicException("err.trip.cancel.TripNotScheduled");
        }
        trip.setStatus(ETripStatus.CANCELED);
        for (Booking b :
                trip.getBookings()) {
            b.setStatus(EBookingStatus.CANCELED);
        }
        return trip;
    }


}
