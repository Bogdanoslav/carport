package com.rooter.carportv8.repo;

import com.rooter.carportv8.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarModelRepository extends JpaRepository<CarModel, Long> {

    @Query("SELECT cm FROM CarModel cm WHERE cm.brand.id = :brandId")
    List<CarModel> findByBrandId(@Param("brandId") Long brandId);
}
