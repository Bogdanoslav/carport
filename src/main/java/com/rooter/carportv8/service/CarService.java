package com.rooter.carportv8.service;

import com.rooter.carportv8.dto.PartialUpdateCar;
import com.rooter.carportv8.dto.SaveCar;
import com.rooter.carportv8.model.Car;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CarService {
    Car getCar(Long id);
    List<Car> getCars();
    Car saveCar(SaveCar saveCar);
    void deleteCar(Long id);
    Car updateCar(Long id, PartialUpdateCar partialUpdateCar);
}
