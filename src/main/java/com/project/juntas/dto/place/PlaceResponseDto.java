package com.project.juntas.dto.place;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlaceResponseDto {

    private String city;
    private String province;
    private String country;
    private Double lat;
    private Double lon;
}
