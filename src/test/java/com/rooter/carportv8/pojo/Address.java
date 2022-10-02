package com.rooter.carportv8.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address{
    private Long id;
    private String city;
    private String address;
    private Float latitude;
    private Float longitude;
}
