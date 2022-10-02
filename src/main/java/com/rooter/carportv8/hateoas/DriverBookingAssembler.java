package com.rooter.carportv8.hateoas;

import com.rooter.carportv8.dto.booking.GetBookingDriver;
import com.rooter.carportv8.mapper.BookingMapper;
import com.rooter.carportv8.rest.BookingController;
import com.rooter.carportv8.rest.TripsController;
import com.rooter.carportv8.model.Booking;
import com.rooter.carportv8.model.enums.EBookingStatus;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DriverBookingAssembler implements RepresentationModelAssembler<Booking, EntityModel<GetBookingDriver>> {
    private final BookingMapper bookingMapper;

    public DriverBookingAssembler(BookingMapper bookingMapper) {
        this.bookingMapper = bookingMapper;
    }

    @Override
    public EntityModel<GetBookingDriver> toModel(Booking booking) {
        GetBookingDriver getBookingDto = bookingMapper.getBookingForTrip(booking);
        EntityModel<GetBookingDriver> bookingModel = EntityModel.of(getBookingDto,
                linkTo(methodOn(BookingController.class).getAllByTripId(booking.getTrip().getId())).withSelfRel());
        EBookingStatus bookingStatus = booking.getStatus();
        if(!EBookingStatus.REJECTED.equals(bookingStatus)){
            linkTo(methodOn(BookingController.class).reject(booking.getId(), null)).withRel("reject");
        }
        return bookingModel;
    }
}
