package com.rooter.carportv8.dto.trip;

import lombok.*;


@NoArgsConstructor
@Getter @Setter
public class SaveAddress {
    private String city;

    private String address;

    private Float latitude;

    private Float longitude;

    @Builder
    public SaveAddress(String city, String address, Float latitude, Float longitude) {
        this.city = city;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
