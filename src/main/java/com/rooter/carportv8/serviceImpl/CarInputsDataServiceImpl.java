package com.rooter.carportv8.serviceImpl;

import com.rooter.carportv8.model.CarBodyType;
import com.rooter.carportv8.model.CarBrand;
import com.rooter.carportv8.model.CarColor;
import com.rooter.carportv8.model.CarModel;
import com.rooter.carportv8.repo.CarBodyTypeRepository;
import com.rooter.carportv8.repo.CarBrandRepository;
import com.rooter.carportv8.repo.CarColorRepository;
import com.rooter.carportv8.repo.CarModelRepository;
import com.rooter.carportv8.service.CarInputsDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarInputsDataServiceImpl implements CarInputsDataService {
    private final CarColorRepository carColorRepository;
    private final CarBrandRepository carBrandRepository;
    private final CarModelRepository carModelRepository;
    private final CarBodyTypeRepository carBodyTypeRepository;

    @Autowired
    public CarInputsDataServiceImpl(CarColorRepository carColorRepository, CarBrandRepository carBrandRepository, CarModelRepository carModelRepository, CarBodyTypeRepository carBodyTypeRepository) {
        this.carColorRepository = carColorRepository;
        this.carBrandRepository = carBrandRepository;
        this.carModelRepository = carModelRepository;
        this.carBodyTypeRepository = carBodyTypeRepository;
    }

    @Override
    public List<CarBrand> getBrands() {
        return carBrandRepository.findAll();
    }

    @Override
    public List<CarModel> getCarModels(Long brandId) {
        return carModelRepository.findByBrandId(brandId);
    }

    @Override
    public List<CarColor> getColors() {
        return carColorRepository.findAll();
    }

    @Override
    public List<CarBodyType> getCarBodyTypes() {
        return carBodyTypeRepository.findAll();
    }
}
