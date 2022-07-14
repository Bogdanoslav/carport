package com.rooter.carportv8.service;

import com.rooter.carportv8.model.CarBodyType;
import com.rooter.carportv8.model.CarBrand;
import com.rooter.carportv8.model.CarColor;
import com.rooter.carportv8.model.CarModel;

import java.util.List;

public interface CarInputsDataService {
    List<CarBrand> getBrands();
    List<CarModel> getCarModels(Long brandId);
    List<CarColor> getColors();
    List<CarBodyType> getCarBodyTypes();
}
