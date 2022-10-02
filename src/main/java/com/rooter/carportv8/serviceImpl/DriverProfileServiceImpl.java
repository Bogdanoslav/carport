package com.rooter.carportv8.serviceImpl;

import com.rooter.carportv8.dto.driver.UpdateDriverProfile;
import com.rooter.carportv8.mapper.DriverMapper;
import com.rooter.carportv8.model.Driver;
import com.rooter.carportv8.repo.interfaces.DriverRepository;
import com.rooter.carportv8.searchPredicates.DriverPredicates;
import com.rooter.carportv8.service.DriverProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class DriverProfileServiceImpl implements DriverProfileService {
    private final DriverMapper driverMapper;
    private final DriverRepository driverRepository;

    @Autowired
    public DriverProfileServiceImpl(DriverMapper driverMapper, DriverRepository driverRepository) {
        this.driverMapper = driverMapper;
        this.driverRepository = driverRepository;
    }

    @Override
    public Driver getProfile(Long id) {
        return driverRepository.findOne(DriverPredicates.hasId(id))
                .orElseThrow(()->new NoSuchElementException(String.format("No record of %s could be found with id %d.",Driver.class.getSimpleName(), id)));
    }

    @Override
    public Driver update(Long id, UpdateDriverProfile updateDriverProfile) {
        Driver driver = driverRepository.findOne(DriverPredicates.hasId(id))
                .orElseThrow(()->new NoSuchElementException(String.format("No record of %s could be found with id %d.",Driver.class.getSimpleName(), id)));
        driverMapper.update(driver, updateDriverProfile);
        return driverRepository.save(driver);
    }

}
