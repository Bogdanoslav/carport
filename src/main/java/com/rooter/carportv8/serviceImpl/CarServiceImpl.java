package com.rooter.carportv8.serviceImpl;

import com.rooter.carportv8.dto.PartialUpdateCar;
import com.rooter.carportv8.dto.SaveCar;
import com.rooter.carportv8.exceptions.NoSuchElementException;
import com.rooter.carportv8.mapper.CarMapper;
import com.rooter.carportv8.model.Car;
import com.rooter.carportv8.repo.CarRepository;
import com.rooter.carportv8.service.CarService;
import com.rooter.carportv8.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final ResourceUtil resourceUtil;
    @Autowired
    private EntityManager entityManager;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, CarMapper carMapper, ResourceUtil resourceUtil) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
        this.resourceUtil = resourceUtil;
    }

    @Override
    public Car getCar(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException(Car.class, id));
        return car;
    }

    @Override
    public List<Car> getCars() {
        List<Car> cars = carRepository.findAll();
        return cars;
    }

    @Override
    public Car saveCar(SaveCar saveCar) {
        Car car = new Car();
        carMapper.mapSave(car, saveCar);
        entityManager.clear();
        return carRepository.saveAndFlush(car);
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public Car updateCar(Long id, PartialUpdateCar partialUpdateCar) {
        Car car = carRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException(Car.class, id
                ));
        carMapper.update(car, partialUpdateCar);
        return carRepository.save(car);
    }
}
