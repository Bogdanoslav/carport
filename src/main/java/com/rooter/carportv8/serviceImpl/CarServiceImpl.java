package com.rooter.carportv8.serviceImpl;

import com.rooter.carportv8.dto.car.PartialUpdateCar;
import com.rooter.carportv8.dto.car.SaveCar;
import com.rooter.carportv8.exceptions.BusinessLogicException;
import com.rooter.carportv8.mapper.CarMapper;
import com.rooter.carportv8.model.*;
import com.rooter.carportv8.model.enums.ECarStatus;
import com.rooter.carportv8.model.enums.ETripStatus;
import com.rooter.carportv8.repo.interfaces.CarModelRepository;
import com.rooter.carportv8.repo.interfaces.CarRepository;
import com.rooter.carportv8.repo.interfaces.TripRepository;
import com.rooter.carportv8.searchPredicates.CarPredicates;
import com.rooter.carportv8.searchPredicates.TripPredicates;
import com.rooter.carportv8.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CarServiceImpl implements CarService {
    private final CarMapper carMapper;
    private final CarModelRepository carModelRepository;
    private final TripRepository tripRepository;
    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository,
                          CarMapper carMapper,
                          CarModelRepository carModelRepository, TripRepository tripRepository) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
        this.carModelRepository = carModelRepository;
        this.tripRepository = tripRepository;
    }

    @Override
    public Car getCar(Long carId, Long ownerId) {
        Car car = carRepository.findOne(CarPredicates.hasId(carId)
                        .and(CarPredicates.hasStatus(ECarStatus.ACTIVE)))
                .orElseThrow(()->new NoSuchElementException(String.format("No record of %s could be found with id %d.",Car.class.getSimpleName(), carId)));
        if(!ownerId.equals(car.getDriverId())){
            throw new AccessDeniedException("UNAUTHORIZED");
        }
        return car;
    }

    @Override
    public List<Car> getCarsByOwnerId(Long id) {
        return carRepository.findAll(CarPredicates.hasDriverId(id).and(CarPredicates.hasStatus(ECarStatus.ACTIVE)));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Car saveCar(SaveCar saveCar, Long ownerId) {
        if(!carModelRepository.existsById(saveCar.getModelId())){
            throw new NoSuchElementException(String.format("No model could be found with id %d.", saveCar.getModelId()));
        }
        Car car = new Car();
        carMapper.mapSave(car, saveCar);
        car.setDriverId(ownerId);
        car.setStatus(ECarStatus.ACTIVE);
        return carRepository.save(car);
    }

    @Override
    public void deleteCar(Long carId, Long ownerId) {
        Car car = carRepository.findOne(CarPredicates.hasId(carId)
                        .and(CarPredicates.hasStatus(ECarStatus.ACTIVE)))
                .orElseThrow(()->new NoSuchElementException(String.format("No record of %s could be found with id %d.",Car.class.getSimpleName(), carId)));
        if(!ownerId.equals(car.getDriverId())){
            throw new AccessDeniedException("UNAUTHORIZED");
        }
        List<Trip> activeTripsWithCurrentCar = tripRepository.findAll(
                TripPredicates.hasCarId(carId)
                .and((TripPredicates.hasStatus(ETripStatus.SCHEDULED))
                .or(TripPredicates.hasStatus(ETripStatus.IN_PROGRESS))));
        if(!activeTripsWithCurrentCar.isEmpty()){
            throw new BusinessLogicException("err.car.delete.carIsOnActiveTrip");
        }
        car.setStatus(ECarStatus.ARCHIVED);
        carRepository.save(car);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Car updateCar(Long carId, PartialUpdateCar partialUpdateCar, Long ownerId) {
        Car car = carRepository.findOne(CarPredicates.hasId(carId).and(CarPredicates.hasStatus(ECarStatus.ACTIVE)))
                .orElseThrow(()->new NoSuchElementException(String.format("No active car found with id %d.", carId)));
        if(!ownerId.equals(car.getDriverId())){
            throw new AccessDeniedException("UNAUTHORIZED");
        }
        carMapper.update(car, partialUpdateCar);
        return carRepository.save(car);
    }
}
