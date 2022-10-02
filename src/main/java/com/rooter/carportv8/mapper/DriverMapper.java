package com.rooter.carportv8.mapper;

import com.rooter.carportv8.dto.driver.GetDriverProfile;
import com.rooter.carportv8.dto.driver.UpdateDriverProfile;
import com.rooter.carportv8.model.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface DriverMapper {
    GetDriverProfile getDriverProfile(Driver driver);

    void update(@MappingTarget Driver driver, UpdateDriverProfile updateDriverProfile);
}
