package com.rooter.carportv8.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "car_model")
@Getter @Setter @NoArgsConstructor
public class CarModel extends CatalogueEntity{

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private CarBrand brand;

    @OneToMany(mappedBy = "model")
    @JsonIgnore
    Set<Car> cars;

    public CarModel(String name, CarBrand brand) {
        super(name);
        this.brand = brand;
    }
}
