package com.rooter.carportv8.mapper;

import com.rooter.carportv8.dto.car.PartialUpdateCar;
import com.rooter.carportv8.dto.passenger.GetPassengerProfile;
import com.rooter.carportv8.dto.passenger.UpdatePassengerProfile;
import com.rooter.carportv8.model.Car;
import com.rooter.carportv8.model.Passenger;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface PassengerMapper {
    GetPassengerProfile getPassengerProfile(Passenger passenger);
    void update(@MappingTarget Passenger passenger, UpdatePassengerProfile updatePassengerProfile);
}
