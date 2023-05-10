package com.project.juntas.dto.place;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PlaceRequestDto{

    @NotBlank
    private String city;
    @NotBlank
    private String province;
    @NotBlank
    private String country;
}