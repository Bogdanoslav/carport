package com.rooter.carportv8.hateoas;

import com.rooter.carportv8.dto.car.GetCar;
import com.rooter.carportv8.mapper.CarMapper;
import com.rooter.carportv8.model.Car;
import com.rooter.carportv8.rest.CarsController;
import com.rooter.carportv8.rest.DriverController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CarAssembler implements RepresentationModelAssembler<Car, EntityModel<GetCar>> {

    private final CarMapper carMapper;

    @Autowired
    public CarAssembler(CarMapper carMapper) {
        this.carMapper = carMapper;
    }

    public EntityModel<GetCar> toModel(Car car) {
        GetCar getCar = carMapper.getCar(car);
        return EntityModel.of(getCar, //
                linkTo(methodOn(CarsController.class).getById(car.getId(), null)).withSelfRel(),
                linkTo(methodOn(CarsController.class).getAllByOwnerId(null)).withRel("cars"),
                linkTo(methodOn(DriverController.class).getById(car.getDriverId(), null)).withRel("owner"));
    }
}
