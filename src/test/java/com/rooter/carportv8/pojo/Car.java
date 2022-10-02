package com.rooter.carportv8.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rooter.carportv8.model.enums.EBodyType;
import com.rooter.carportv8.model.enums.ECarStatus;
import com.rooter.carportv8.model.enums.EColor;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Car {
    private Long id;
    @JsonProperty("color")
    private EColor color;
    @JsonProperty("model")
    private CarModel model;
    @JsonProperty("modelId")
    private Long modelId;
    @JsonProperty("bodyType")
    private EBodyType bodyType;
    @JsonProperty("owner")
    private User owner;
    @JsonProperty("ownerId")
    private Long ownerId;
    @JsonProperty("status")
    private ECarStatus status;


}
