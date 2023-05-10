package com.project.juntas.dto.journey;

import lombok.Getter;

import java.time.LocalDate;


@Getter
public class JourneyRequestDto {

    private LocalDate createDate;
    private LocalDate departureDate;
    private LocalDate arrivalDate;

    //Comfort
    private boolean acceptChild;
    private boolean petFriendly;
    private boolean acceptSmokers;
    private boolean luggageBig;
    private boolean luggageMid;
    private boolean luggageSma;

    //Departure
    private String cityDep;
    private String provinceDep;
    //Arrival
    private String cityArr;
    private String provinceArr;

    //Vehicle
    private String vehicleColor;
    private Integer emptySeats;
    private String primaryBrand;
    private String modelName;
    private String patentNumber;
}