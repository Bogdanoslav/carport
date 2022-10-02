package com.rooter.carportv8.serviceImpl;

import com.rooter.carportv8.dto.passenger.UpdatePassengerProfile;
import com.rooter.carportv8.mapper.PassengerMapper;
import com.rooter.carportv8.model.Passenger;
import com.rooter.carportv8.repo.interfaces.PassengerRepository;
import com.rooter.carportv8.searchPredicates.PassengerPredicates;
import com.rooter.carportv8.service.PassengerProfileService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;

public class PassengerProfileServiceImpl implements PassengerProfileService {
    private final PassengerRepository passengerRepository;
    private final PassengerMapper passengerMapper;

    @Autowired
    public PassengerProfileServiceImpl(PassengerRepository passengerRepository, PassengerMapper passengerMapper) {
        this.passengerRepository = passengerRepository;
        this.passengerMapper = passengerMapper;
    }

    @Override
    public Passenger getProfile(Long id) {
        return passengerRepository.findOne(PassengerPredicates.hasId(id))
                .orElseThrow(()->new NoSuchElementException(String.format("No record of %s could be found with id %d.", Passenger.class.getSimpleName(), id)));
    }

    @Override
    public Passenger update(Long id,UpdatePassengerProfile updateDriverProfile) {
        Passenger passenger = passengerRepository.findOne(PassengerPredicates.hasId(id))
                .orElseThrow(()->new NoSuchElementException(String.format("No record of %s could be found with id %d.", Passenger.class.getSimpleName(), id)));
        passengerMapper.update(passenger, updateDriverProfile);
        return passengerRepository.save(passenger);
    }
}
