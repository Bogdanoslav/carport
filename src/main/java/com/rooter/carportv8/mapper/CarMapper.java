package com.rooter.carportv8.mapper;

import com.rooter.carportv8.dto.PartialUpdateCar;
import com.rooter.carportv8.dto.SaveCar;
import com.rooter.carportv8.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface CarMapper {
    void update(@MappingTarget Car car, PartialUpdateCar partialUpdateCar);
    void mapSave(@MappingTarget Car car, SaveCar saveCar);
}
