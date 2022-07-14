package com.rooter.carportv8.hateoas;

import com.rooter.carportv8.model.Car;
import com.rooter.carportv8.controller.CarsController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CarAssembler implements RepresentationModelAssembler<Car, EntityModel<Car>> {
    @Override
    public EntityModel<Car> toModel(Car car) {

        return EntityModel.of(car, //
                linkTo(methodOn(CarsController.class).getById(car.getId())).withSelfRel(),
                linkTo(methodOn(CarsController.class).delete(car.getId())).withRel("delete"),
                linkTo(methodOn(CarsController.class).patch(car.getId(), null)).withRel("patch"),
                linkTo(methodOn(CarsController.class).getAll()).withRel("cars"));
    }
}
