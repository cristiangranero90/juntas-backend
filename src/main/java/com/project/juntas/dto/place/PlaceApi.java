package com.project.juntas.dto.place;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
//@JsonRootName(value = "municipios")
public class PlaceApi {
    private double lat;
    private double lon;
    @JsonProperty("municipios")
    private void unpackNested(List<Map<String, Object>> municipios) {
        Map<String, Object> centroide = (Map<String, Object>)municipios.get(0).get("centroide");
        this.lat = (double)centroide.get("lat");
        this.lon = (double)centroide.get("lon");
    }
}
