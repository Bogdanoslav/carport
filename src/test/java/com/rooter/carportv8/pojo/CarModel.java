package com.rooter.carportv8.pojo;

import com.rooter.carportv8.model.CatalogueEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarModel {
    private Long id;;
    private String name;
    private CarBrand brand;
}
