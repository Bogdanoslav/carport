package com.rooter.carportv8.repo;

import com.rooter.carportv8.dto.PartialUpdateCar;
import com.rooter.carportv8.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface CarRepository extends JpaRepository <Car, Long> {

    @Query("select c from Car c " +
            "join fetch c.color " +
            "join fetch c.model m " +
            "join fetch m.brand " +
            "join fetch c.bodyType")
    List<Car> findAll();

    @Query("select c from Car c join fetch c.color join fetch c.model m join fetch m.brand join fetch c.bodyType where c.id = ?1")
    Optional<Car> findById(Long id);


}
