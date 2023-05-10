package com.project.juntas.dto.place;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PlaceApi {
    @JsonProperty("nombre")
    private String name;

    @JsonProperty("lat")
    private Double lat;

    @JsonProperty("lon")
    private Double lon;
}
