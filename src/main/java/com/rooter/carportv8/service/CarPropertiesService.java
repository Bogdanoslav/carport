package com.rooter.carportv8.service;

public interface CarPropertiesService {
    boolean existsCarModel(Long id);
    boolean existsCarColor(Long id);
    boolean existsCarBodyType(Long id);
}
