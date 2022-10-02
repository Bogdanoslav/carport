package com.rooter.carportv8.rest;

import com.rooter.carportv8.dto.booking.GetBookingDriver;
import com.rooter.carportv8.dto.booking.GetBookingPassenger;
import com.rooter.carportv8.hateoas.DriverBookingAssembler;
import com.rooter.carportv8.hateoas.PassengerBookingAssembler;
import com.rooter.carportv8.model.Booking;
import com.rooter.carportv8.security.UserDetailsImpl;
import com.rooter.carportv8.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class BookingController {

    private final BookingService bookingService;
    private final DriverBookingAssembler driverBookingAssembler;
    private final PassengerBookingAssembler passengerBookingAssembler;

    @Autowired
    public BookingController(BookingService bookingService,
                             DriverBookingAssembler driverBookingAssembler,
                             PassengerBookingAssembler passengerBookingAssembler) {
        this.bookingService = bookingService;
        this.driverBookingAssembler = driverBookingAssembler;
        this.passengerBookingAssembler = passengerBookingAssembler;
    }

    @GetMapping("trips/{tripId}/bookings")
    @PreAuthorize("hasRole('ROLE_DRIVER')")
    public ResponseEntity<?> getAllByTripId(@PathVariable("tripId") Long tripId){
        List<Booking> bookings = bookingService.getAllByTripId(tripId);
        List<EntityModel<GetBookingDriver>> bookingsEntities = bookings.stream().map(driverBookingAssembler::toModel).toList();
        CollectionModel<EntityModel<GetBookingDriver>> collectionModel =
                CollectionModel.of(bookingsEntities,
                        linkTo(methodOn(BookingController.class).getAllByTripId(tripId))
                                .withSelfRel());
        return ResponseEntity.ok(collectionModel);
    }

    @GetMapping("profile/bookings")
    @PreAuthorize("hasRole('ROLE_PASSENGER')")
    public ResponseEntity<?> getProfileBookings(@AuthenticationPrincipal UserDetailsImpl userDetails){
        List<Booking> bookings = bookingService.getAllByUserId(userDetails.getId());
        List<EntityModel<GetBookingPassenger>> bookingsEntities = bookings.stream().map(passengerBookingAssembler::toModel).toList();
        CollectionModel<EntityModel<GetBookingPassenger>> collectionModel =
                CollectionModel.of(bookingsEntities,
                        linkTo(methodOn(BookingController.class).getProfileBookings(null))
                                .withSelfRel());
        return ResponseEntity.ok(collectionModel);
    }

    @PostMapping("trips/{id}/book")
    @Transactional
    @PreAuthorize("hasRole('ROLE_PASSENGER')")
    public ResponseEntity<?> book(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        Booking booking = bookingService.book(id, userDetails.getId());
        return  ResponseEntity.ok(passengerBookingAssembler.toModel(booking));
    }

    @PatchMapping("bookings/{id}/cancel")
    @Transactional
    @PreAuthorize("hasRole('ROLE_PASSENGER')")
    public ResponseEntity<?> cancel(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        bookingService.cancel(id, userDetails.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("bookings/{id}/reject")
    @Transactional
    @PreAuthorize("hasRole('ROLE_DRIVER')")
    public ResponseEntity<?> reject(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        bookingService.reject(id, userDetails.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
