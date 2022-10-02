package com.rooter.carportv8.hateoas;

import com.rooter.carportv8.dto.trip.GetTrip;
import com.rooter.carportv8.mapper.TripMapper;
import com.rooter.carportv8.rest.TripsController;
import com.rooter.carportv8.model.enums.ETripStatus;
import com.rooter.carportv8.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TripAssembler implements RepresentationModelAssembler<Trip, EntityModel<GetTrip>> {
    private final TripMapper tripMapper;

    @Autowired
    public TripAssembler(TripMapper tripMapper) {
        this.tripMapper = tripMapper;
    }

    public EntityModel<GetTrip> toModel(Trip trip) {
        GetTrip getTripDtp = tripMapper.getTrip(trip);
        EntityModel<GetTrip> tripModel = EntityModel.of(getTripDtp, //
                linkTo(methodOn(TripsController.class).getById(trip.getId(), null)).withSelfRel(),
                linkTo(methodOn(TripsController.class).search(null, null)).withRel("trips"));
        if(ETripStatus.SCHEDULED.equals(trip.getStatus())){
            tripModel.add(linkTo(methodOn(TripsController.class).cancel(trip.getId(), null)).withRel("cancel"));
        }
        return tripModel;
    }
}
