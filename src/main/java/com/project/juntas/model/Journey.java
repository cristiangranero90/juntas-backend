package com.project.juntas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "journeys")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Journey implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long journeyId;

    private LocalDate createDate;
    private LocalDate departureDate;
    private LocalDate arrivalDate;

    @OneToOne
    @JoinColumn(name = "driver")
    private User driver;

    @OneToOne
    @JoinColumn(name = "comfort_id", referencedColumnName = "comfortId")
    private Comfort comfort;

    @OneToOne
    @JoinColumn(name="vehicle_id", referencedColumnName = "vehicleId")
    private Vehicle vehicle;

    @OneToOne
    private Place arrival;
    @OneToOne
    private Place departure;
}