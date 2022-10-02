package com.rooter.carportv8.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rooter.carportv8.model.enums.EBodyType;
import com.rooter.carportv8.model.enums.ECarStatus;
import com.rooter.carportv8.model.enums.EColor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Table(name = "car")
@Entity
@Getter
@Setter
public class Car extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private EColor color;

    @Enumerated(EnumType.STRING)
    private EBodyType bodyType;

    @JoinColumn(name = "model_id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = CarModel.class, fetch = FetchType.EAGER)
    private CarModel model;

    @Column(name = "model_id")
    private Long modelId;

    @JoinColumn(name = "driver_id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = Driver.class, fetch = FetchType.EAGER)
    @JsonBackReference("driver-car")
    private Driver driver;

    @Column(name = "driver_id")
    private Long driverId;

    @OneToMany(mappedBy = "car")
    @JsonIgnore
    private Set<Trip> trips;

    @Enumerated(EnumType.STRING)
    private ECarStatus status;

    public Car() {
    }

    @Builder
    public Car(Long id, EColor color, EBodyType bodyType, CarModel model, Long modelId, Driver driver, Long driverId, Set<Trip> trips, ECarStatus status) {
        super(id);
        this.color = color;
        this.bodyType = bodyType;
        this.model = model;
        this.modelId = modelId;
        this.driver = driver;
        this.driverId = driverId;
        this.trips = trips;
        this.status = status;
    }
}
