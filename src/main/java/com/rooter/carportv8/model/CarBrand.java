package com.rooter.carportv8.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "car_brand")
@Getter @Setter @NoArgsConstructor
public class CarBrand extends CatalogueEntity {
    @JsonIgnore
    @OneToMany(mappedBy = "brand")
    Set<CarModel> models;

    public CarBrand(String name) {
        super(name);
    }
}
