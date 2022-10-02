package com.rooter.carportv8.dto.car;

import com.rooter.carportv8.model.enums.EBodyType;
import com.rooter.carportv8.model.enums.EColor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter @Setter @AllArgsConstructor
public class SaveCar {
    @NotNull
    @Min(value = 0)
    private Long modelId;
    @NotNull
    private EColor color;
    @NotNull
    private EBodyType bodyType;
}
