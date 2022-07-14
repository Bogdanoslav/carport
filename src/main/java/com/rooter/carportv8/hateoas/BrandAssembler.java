package com.rooter.carportv8.hateoas;

import com.rooter.carportv8.controller.CarsController;
import com.rooter.carportv8.model.CarBrand;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BrandAssembler implements RepresentationModelAssembler<CarBrand, EntityModel<CarBrand>> {
    @Override
    public EntityModel<CarBrand> toModel(CarBrand brand) {
        return EntityModel.of(brand,
                linkTo(methodOn(CarsController.class).getModels(brand.getId())).withRel("models"));
    }
}
