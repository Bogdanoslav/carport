package com.rooter.carportv8.hateoas;

import com.rooter.carportv8.dto.passenger.GetPassengerProfile;
import com.rooter.carportv8.mapper.PassengerMapper;
import com.rooter.carportv8.model.Passenger;
import com.rooter.carportv8.rest.DriverController;
import com.rooter.carportv8.rest.PassengerController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PassengerAssembler implements RepresentationModelAssembler<Passenger, EntityModel<GetPassengerProfile>> {
    private final PassengerMapper passengerMapper;

    public PassengerAssembler(PassengerMapper passengerMapper) {
        this.passengerMapper = passengerMapper;
    }

    @Override
    public EntityModel<GetPassengerProfile> toModel(Passenger passenger) {
        GetPassengerProfile getPassengerProfile = passengerMapper.getPassengerProfile(passenger);
        return EntityModel.of(
                getPassengerProfile,
                linkTo(methodOn(PassengerController.class).getById(passenger.getId(), null)).withSelfRel());
    }
}
