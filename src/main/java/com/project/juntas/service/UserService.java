package com.project.juntas.service;

import com.project.juntas.dto.user.LoginRequestDto;
import com.project.juntas.dto.user.LoginResponseDto;
import com.project.juntas.dto.user.RegisterRequestDto;
import com.project.juntas.dto.user.RegisterResponseDto;
import com.project.juntas.model.User;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public interface UserService {

    RegisterResponseDto register(RegisterRequestDto dto);
    LoginResponseDto login(LoginRequestDto dto);
    RegisterResponseDto oauthLogin(OAuth2AuthenticationToken authenticate);
}