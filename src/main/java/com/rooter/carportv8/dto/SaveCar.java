package com.rooter.carportv8.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class SaveCar {
    @NotNull
    @Min(0)
    Long modelId;
    @NotNull
    @Min(0)
    Long colorId;
    @NotNull
    @Min(0)
    Long bodyTypeId;
}
