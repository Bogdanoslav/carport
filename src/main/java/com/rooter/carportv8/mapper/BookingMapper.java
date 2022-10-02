package com.rooter.carportv8.mapper;

import com.rooter.carportv8.dto.booking.GetBookingDriver;
import com.rooter.carportv8.dto.booking.GetBookingPassenger;
import com.rooter.carportv8.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookingMapper {
    BookingMapper INSTANCE = Mappers.getMapper( BookingMapper.class );

    @Mapping(source = "passenger.firstName", target = "passenger.firstName")
    @Mapping(source = "passenger.lastName", target = "passenger.lastName")
    @Mapping(source = "passenger.email", target = "passenger.email")
    @Mapping(source = "passenger.age", target = "passenger.age")
    @Mapping(source = "passenger.about", target = "passenger.about")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "bookedAt", target = "bookedAt")
    GetBookingDriver getBookingForTrip(Booking booking);

    @Mapping(source = "trip.car.driver.firstName", target = "driver.firstName")
    @Mapping(source = "trip.car.driver.lastName", target = "driver.lastName")
    @Mapping(source = "trip.car.driver.email", target = "driver.email")
    @Mapping(source = "trip.car.driver.age", target = "driver.age")
    @Mapping(source = "trip.car.driver.about", target = "driver.about")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "bookedAt", target = "bookedAt")
    GetBookingPassenger getUsersBooking(Booking booking);
}
