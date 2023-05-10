package com.project.juntas.dto.vehicle;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@RequiredArgsConstructor
public class VehicleRequestDto {

    @NotBlank(message = "The color of the vehicle may not be blank")
    private String vehicleColor;
    @NotNull(message = "Empty seats of the vehicle may not be null")
    private Integer emptySeats;
    @NotBlank(message = "The brand of the vehicle may not be blank")
    private String primaryBrand;
    @NotBlank(message = "The model of the vehicle may not be blank")
    private String modelName;
    @NotBlank(message = "The patent of the vehicle may not be blank")
    private String patentNumber;
    @NotNull(message = "the driver of the vehicle may not be null")
    private Long journeyId;
}