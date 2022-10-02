package com.rooter.carportv8.serviceImpl;

import com.rooter.carportv8.model.Driver;
import com.rooter.carportv8.model.Passenger;
import com.rooter.carportv8.repo.interfaces.PassengerRepository;
import com.rooter.carportv8.searchPredicates.DriverPredicates;
import com.rooter.carportv8.searchPredicates.PassengerPredicates;
import com.rooter.carportv8.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;

    @Autowired
    public PassengerServiceImpl(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @Override
    public Passenger getById(Long id) {
        return passengerRepository.findOne(PassengerPredicates.hasId(id))
                .orElseThrow(()->new NoSuchElementException(String.format("No record of %s could be found with id %d.", Passenger.class.getSimpleName(), id)));    }
}
