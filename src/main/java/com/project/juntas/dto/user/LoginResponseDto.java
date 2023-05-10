package com.project.juntas.dto.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponseDto {

    private String name;
    private String lastName;
    private String token;
}