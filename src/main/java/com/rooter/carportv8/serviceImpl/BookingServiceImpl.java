package com.rooter.carportv8.serviceImpl;

import com.rooter.carportv8.exceptions.BusinessLogicException;
import com.rooter.carportv8.model.Booking;
import com.rooter.carportv8.model.enums.EBookingStatus;
import com.rooter.carportv8.model.enums.ETripStatus;
import com.rooter.carportv8.model.Trip;
import com.rooter.carportv8.repo.interfaces.BookingRepository;
import com.rooter.carportv8.repo.interfaces.TripRepository;
import com.rooter.carportv8.searchPredicates.BookingPredicates;
import com.rooter.carportv8.searchPredicates.TripPredicates;
import com.rooter.carportv8.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {
    private final TripRepository tripRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(TripRepository tripRepository, BookingRepository bookingRepository) {
        this.tripRepository = tripRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Booking> getAllByTripId(Long tripId) {
        List<Booking> bookings = bookingRepository.findAll(BookingPredicates.hasTripId(tripId));
        return bookings;
    }

    @Override
    public List<Booking> getAllByUserId(Long id) {
        List<Booking> bookings = bookingRepository.findAll(BookingPredicates.hasUserId(id));
        return bookings;
    }

    @Override
    public Booking getById(Long bookingId, Long userId) {
        Booking booking = bookingRepository.findOne(BookingPredicates.hasId(bookingId))
                .orElseThrow(() -> new NoSuchElementException(String.format("No record of %s could be found with id %d.", Booking.class.getSimpleName(), bookingId)));
        if (!userId.equals(booking.getPassengerId())) {
            throw new AccessDeniedException("UNAUTHORIZED");
        }
        return booking;
    }

    @Override
    public Booking book(Long tripId, Long userId) {
        Trip trip = tripRepository.findOne(TripPredicates.hasId(tripId))
                .orElseThrow(() -> new NoSuchElementException(String.format("No record of %s could be found with id %d.", Trip.class.getSimpleName(), tripId)));
        if (!ETripStatus.SCHEDULED.equals(trip.getStatus())) {
            throw new BusinessLogicException("err.booking.tripIsNotScheduled");
        }
        if(trip.getSeatsLeft()==0){
            throw new BusinessLogicException("err.booking.noFreeSeats");
        }

        Optional<Booking> bookingOptional = bookingRepository.findOne(
                BookingPredicates.hasUserId(userId)
                        .and(BookingPredicates.hasTripId(tripId)));

        bookingOptional.ifPresent(b->{
            if(EBookingStatus.REJECTED.equals(b.getStatus())){
                throw new BusinessLogicException("err.booking.userHasBeenRejected");
            } else if(EBookingStatus.APPROVED.equals(b.getStatus())||EBookingStatus.WAITING.equals(b.getStatus())){
                throw new BusinessLogicException("err.booking.bookingAlreadyExists");
            }
        });

        Booking booking = Booking.builder()
                .tripId(tripId)
                .passengerId(userId)
                .bookedAt(LocalDateTime.now())
                .build();

        if (trip.getInstantBooking()) {
            booking.setStatus(EBookingStatus.APPROVED);
            trip.setSeatsLeft(trip.getSeatsLeft()-1);
        } else {
            booking.setStatus(EBookingStatus.WAITING);
        }
        return bookingRepository.save(booking);
    }

    @Override
    public void cancel(Long bookingId, Long userId) {
        Booking booking = getById(bookingId, userId);
        if(EBookingStatus.REJECTED.equals(booking.getStatus())){
            throw new BusinessLogicException("err.booking.cancelRejectedBooking");
        }
        else if(ETripStatus.COMPLETED.equals(booking.getTrip().getStatus())){
            throw new BusinessLogicException("err.booking.cancelBookingOnCompletedTrip");
        }
        Trip trip = booking.getTrip();
        trip.setSeatsLeft(trip.getSeatsLeft()+1);
        tripRepository.save(trip);
        bookingRepository.delete(BookingPredicates.hasId(booking.getId()));
    }

    @Override
    public Booking reject(Long bookingId, Long userId) {
        Booking booking = bookingRepository.findOne(BookingPredicates.hasId(bookingId))
                .orElseThrow(() -> new NoSuchElementException(String.format("No record of %s could be found with id %d.", Booking.class.getSimpleName(), bookingId)));
        if(!userId.equals(booking.getTrip().getCar().getDriverId())){
            throw new BusinessLogicException("err.booking.reject.BookingOnAnotherDriverTrip");
        }

        Trip trip = booking.getTrip();
        if (!ETripStatus.SCHEDULED.equals(trip.getStatus())){
            throw new BusinessLogicException("err.booking.reject.TripMustBeScheduled");
        }

        if (EBookingStatus.WAITING.equals(booking.getStatus())) {
            booking.setStatus(EBookingStatus.REJECTED);
            trip.setSeatsLeft(trip.getSeatsLeft()+1);
            return bookingRepository.save(booking);
        } else {
            throw new BusinessLogicException("err.booking.reject.BookingMustBeWaiting");
        }
    }



}
