package com.rooter.carportv8.hateoas;

import com.rooter.carportv8.dto.booking.GetBookingPassenger;
import com.rooter.carportv8.mapper.BookingMapper;
import com.rooter.carportv8.model.Booking;
import com.rooter.carportv8.model.enums.EBookingStatus;
import com.rooter.carportv8.rest.BookingController;
import com.rooter.carportv8.rest.TripsController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PassengerBookingAssembler implements RepresentationModelAssembler<Booking, EntityModel<GetBookingPassenger>> {

    private final BookingMapper bookingMapper;

    public PassengerBookingAssembler(BookingMapper bookingMapper) {
        this.bookingMapper = bookingMapper;
    }

    @Override
    public EntityModel<GetBookingPassenger> toModel(Booking booking) {
        GetBookingPassenger getBookingDto = bookingMapper.getUsersBooking(booking);
        EntityModel<GetBookingPassenger> bookingModel = EntityModel.of(getBookingDto,
                linkTo(methodOn(BookingController.class).getProfileBookings(null)).withSelfRel());
        EBookingStatus bookingStatus = booking.getStatus();
        if(EBookingStatus.WAITING.equals(bookingStatus)||EBookingStatus.APPROVED.equals(bookingStatus)){
            linkTo(methodOn(BookingController.class).cancel(booking.getId(), null)).withRel("cancel");
            linkTo(methodOn(TripsController.class).getById(booking.getTripId(), null)).withRel("trip");
        }
        return bookingModel;
    }
}
