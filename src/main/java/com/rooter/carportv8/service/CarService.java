package com.rooter.carportv8.service;

import com.rooter.carportv8.dto.car.PartialUpdateCar;
import com.rooter.carportv8.dto.car.SaveCar;
import com.rooter.carportv8.model.Car;

import java.util.List;

public interface CarService {
    Car getCar(Long carId, Long ownerId);
    List<Car> getCarsByOwnerId(Long ownerId);
    Car saveCar(SaveCar saveCar, Long ownerId);
    void deleteCar(Long carId, Long ownerId);
    Car updateCar(Long id, PartialUpdateCar partialUpdateCar, Long userDetailsId);
}
