package com.project.juntas.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegisterResponseDto {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private Integer dni;
    private LocalDate birthdayDate;
    private LocalDate registerDate;
    private Boolean isConfirmedEmail;
    private Boolean hasConfirmedDni;
    private String profileImage;
    private String token;
}
