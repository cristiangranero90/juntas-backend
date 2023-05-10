package com.project.juntas.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegisterRequestDto {

    private String name;
    private String lastName;
    private String email;
    private Integer dni;
    private String password;
    private LocalDate birthdayDate;
    private String profileImage;
}
