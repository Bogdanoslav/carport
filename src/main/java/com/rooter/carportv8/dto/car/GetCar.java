package com.rooter.carportv8.dto.car;

import com.rooter.carportv8.model.enums.EBodyType;
import com.rooter.carportv8.model.enums.EColor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetCar {
    private Long id;

    private EColor color;

    private EBodyType bodyType;

    private GetBrand brand;

    private GetModel model;
}
