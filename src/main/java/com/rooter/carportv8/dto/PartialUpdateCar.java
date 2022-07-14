package com.rooter.carportv8.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class PartialUpdateCar {
    @NotNull
    @Min(0)
    private Long colorId;
}
