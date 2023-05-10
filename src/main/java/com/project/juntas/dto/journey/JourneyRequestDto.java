package com.project.juntas.dto.journey;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class JourneyRequestDto {
    private Long id;
    private LocalDate createDate;
    private LocalDateTime departureDate;
    private LocalDate arrivalDate;
    private int price;
    @NotNull
    private Long driverId;
    @NotNull
    private Long comfortId;
    @NotNull
    private Long vehicleId;
    @NotNull
    private Long arrivalId;
    @NotNull
    private Long departureId;
}