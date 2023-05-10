package com.project.juntas.controller;

import com.project.juntas.dto.user.LoginRequestDto;
import com.project.juntas.dto.user.RegisterRequestDto;
import com.project.juntas.service.UserService;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import static com.project.juntas.exception.handler.ResponseBuilder.responseBuilder;


@RestController
@RequestMapping("/users")
public record UserController(UserService service) {


    @ApiOperation(value = "Create a new user", notes = "Creates a new user with the given details")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto dto){
        return responseBuilder(HttpStatus.CREATED,service.register(dto));
    }

    @ApiOperation(value = "Log in an user", notes = "log in an user with email and password")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto dto){
        return responseBuilder(HttpStatus.OK,service.login(dto));
    }

    @GetMapping("/oauth")
    public ResponseEntity<?> oauthLogin( OAuth2AuthenticationToken authenticate){
        return responseBuilder(HttpStatus.OK,service.oauthLogin(authenticate));
    }
}
