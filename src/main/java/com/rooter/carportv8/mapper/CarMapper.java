package com.rooter.carportv8.mapper;

import com.rooter.carportv8.dto.car.GetCar;
import com.rooter.carportv8.dto.car.PartialUpdateCar;
import com.rooter.carportv8.dto.car.SaveCar;
import com.rooter.carportv8.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper( CarMapper.class );

    void update(@MappingTarget Car car, PartialUpdateCar partialUpdateCar);
    void mapSave(@MappingTarget Car car, SaveCar saveCar);
    @Mapping(source = "model.brand", target = "brand")
    GetCar getCar(Car car);
}
