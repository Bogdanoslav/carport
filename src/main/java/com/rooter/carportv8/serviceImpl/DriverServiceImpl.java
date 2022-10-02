package com.rooter.carportv8.serviceImpl;

import com.rooter.carportv8.model.Driver;
import com.rooter.carportv8.repo.interfaces.DriverRepository;
import com.rooter.carportv8.searchPredicates.DriverPredicates;
import com.rooter.carportv8.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public Driver getById(Long id) {
        return driverRepository.findOne(DriverPredicates.hasId(id))
                .orElseThrow(()->new NoSuchElementException(String.format("No record of %s could be found with id %d.",Driver.class.getSimpleName(), id)));
    }
}
