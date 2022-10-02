package com.rooter.carportv8.repo.interfaces;

import com.rooter.carportv8.model.CarBrand;
import com.rooter.carportv8.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarModelRepository extends JpaRepository<CarModel, Long>{
    @Query("SELECT cm FROM CarModel cm " +
            "left join CarBrand cb " +
            "ON cm.id = cb.id " +
            "WHERE cb.id = ?1")
    List<CarModel> findAllByBrandId(Long id);
}