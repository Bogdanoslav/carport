package com.rooter.carportv8.resources;

import com.rooter.carportv8.rest.AuthController;
import com.rooter.carportv8.rest.CarsController;
import org.springframework.hateoas.RepresentationModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class IndexResources extends RepresentationModel {
    public IndexResources() {
//        add(linkTo(methodOn(CarsController.class).getAllByOwnerId(null)).withRel("cars"));
//        add(linkTo(methodOn(CarsController.class).getColors()).withRel("car-colors"));
//        add(linkTo(methodOn(CarsController.class).getBrands()).withRel("car-brands"));
//        add(linkTo(methodOn(CarsController.class).getBodyTypes()).withRel("car-bodyTypes"));
        add(linkTo(methodOn(AuthController.class).login(null)).withRel("signin"));
    }
}
