package com.rooter.carportv8.mapper;

import com.rooter.carportv8.dto.trip.GetTrip;
import com.rooter.carportv8.dto.trip.SaveTrip;
import com.rooter.carportv8.model.Trip;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TripMapper {
    TripMapper INSTANCE = Mappers.getMapper( TripMapper.class );


    void save(@MappingTarget Trip trip, SaveTrip saveTrip);

    @Mapping(source = "car.driver.firstName", target = "driver.firstName")
    @Mapping(source = "car.driver.lastName", target = "driver.lastName")
    @Mapping(source = "car.driver.email", target = "driver.email")
    @Mapping(source = "car.driver.age", target = "driver.age")
    @Mapping(source = "car.driver.about", target = "driver.about")
    GetTrip getTrip(Trip trip);
}
