package com.project.juntas.dto.journey;

import com.project.juntas.dto.user.DriverDto;
import com.project.juntas.model.Comfort;
import com.project.juntas.model.Place;
import com.project.juntas.model.User;
import com.project.juntas.model.Vehicle;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class JourneyResponseDto {

    private LocalDate createDate;
    private LocalDate departureDate;
    private LocalDate arrivalDate;

    //Comfort
    private Comfort comfort;
    //Departure
    private Place departure;
    //Arrival
    private Place arrival;
    //Vehicle
    private Vehicle vehicle;
    //Driver
    private DriverDto driver;
}