package com.rooter.carportv8.hateoas;

import com.rooter.carportv8.dto.driver.GetDriverProfile;
import com.rooter.carportv8.mapper.DriverMapper;
import com.rooter.carportv8.model.Driver;
import com.rooter.carportv8.rest.DriverController;
import com.rooter.carportv8.rest.DriverProfileController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DriverProfileAssembler  implements RepresentationModelAssembler<Driver, EntityModel<GetDriverProfile>> {
    private final DriverMapper driverMapper;

    @Autowired
    public DriverProfileAssembler(DriverMapper driverMapper) {
        this.driverMapper = driverMapper;
    }

    @Override
    public EntityModel<GetDriverProfile> toModel(Driver driver) {
        GetDriverProfile getDriverProfile = driverMapper.getDriverProfile(driver);
        return EntityModel.of(getDriverProfile, //
                linkTo(methodOn(DriverProfileController.class).get(null)).withSelfRel(),
                linkTo(methodOn(DriverProfileController.class).update(null,null)).withSelfRel());
    }
}