package com.rooter.carportv8.dto.car;

import com.rooter.carportv8.model.enums.EColor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class PartialUpdateCar {
    @NotNull
    private EColor color;

    public PartialUpdateCar() {
    }

    public PartialUpdateCar(EColor color) {
        this.color = color;
    }
}
