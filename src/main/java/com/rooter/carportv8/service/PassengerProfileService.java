package com.rooter.carportv8.service;

import com.rooter.carportv8.dto.driver.UpdateDriverProfile;
import com.rooter.carportv8.dto.passenger.UpdatePassengerProfile;
import com.rooter.carportv8.model.Driver;
import com.rooter.carportv8.model.Passenger;

public interface PassengerProfileService {
    Passenger getProfile(Long id);
    Passenger update(Long id, UpdatePassengerProfile updateDriverProfile);
}
