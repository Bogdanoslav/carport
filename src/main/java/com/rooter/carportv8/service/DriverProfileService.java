package com.rooter.carportv8.service;

import com.rooter.carportv8.dto.driver.UpdateDriverProfile;
import com.rooter.carportv8.model.Driver;

public interface DriverProfileService {
    Driver getProfile(Long id);
    Driver update(Long id,UpdateDriverProfile updateDriverProfile);
}
