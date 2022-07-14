package com.rooter.carportv8.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "body_type")
@Getter
@Setter
@NoArgsConstructor
public class CarBodyType extends CatalogueEntity {
    @JsonIgnore
    @OneToMany(mappedBy = "bodyType")
    List<Car> cars;
}
