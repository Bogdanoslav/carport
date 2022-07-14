package com.rooter.carportv8.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "car")
@Entity
@Getter
@Setter
public class Car extends BaseEntity {

    @JoinColumn(name = "color_id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = CarColor.class, fetch = FetchType.EAGER)
    private CarColor color;

    @Column(name = "color_id")
    private Long colorId;

    @JoinColumn(name = "model_id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = CarModel.class, fetch = FetchType.EAGER)
    private CarModel model;

    @Column(name = "model_id")
    private Long modelId;

    @JoinColumn(name = "body_type_id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = CarBodyType.class, fetch = FetchType.EAGER)
    private CarBodyType bodyType;

    @Column(name = "body_type_id")
    private Long bodyTypeId;

    public Car() {
    }

    public Car(CarColor color, CarModel model, CarBodyType bodyType) {
        this.color = color;
        this.model = model;
        this.bodyType = bodyType;
    }

    public CarBrand getBrand() {
        return model.getBrand();
    }
}
