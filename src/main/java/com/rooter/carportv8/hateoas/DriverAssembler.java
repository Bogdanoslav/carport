package com.rooter.carportv8.hateoas;

import com.rooter.carportv8.dto.driver.GetDriverProfile;
import com.rooter.carportv8.mapper.DriverMapper;
import com.rooter.carportv8.model.Driver;
import com.rooter.carportv8.rest.DriverController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DriverAssembler implements RepresentationModelAssembler<Driver, EntityModel<GetDriverProfile>> {
    private final DriverMapper driverMapper;

    @Autowired
    public DriverAssembler(DriverMapper driverMapper) {
        this.driverMapper = driverMapper;
    }

    @Override
    public EntityModel<GetDriverProfile> toModel(Driver driver) {
        GetDriverProfile getDriverProfile = driverMapper.getDriverProfile(driver);
        return EntityModel.of(getDriverProfile, //
                linkTo(methodOn(DriverController.class).getById(driver.getId(), null)).withSelfRel());
    }
}
