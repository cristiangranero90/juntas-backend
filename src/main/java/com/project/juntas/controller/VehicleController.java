package com.project.juntas.controller;

import com.project.juntas.dto.vehicle.VehicleRequestDto;
import com.project.juntas.service.VehicleService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.project.juntas.exception.handler.ResponseBuilder.responseBuilder;

@RestController
@RequestMapping("/vehicle")
public record VehicleController (VehicleService service){

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody VehicleRequestDto requestDto){
        return responseBuilder(HttpStatus.CREATED,service.create(requestDto));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<?> update(@PathVariable Long id, @RequestBody VehicleRequestDto requestDto) {
        return responseBuilder(HttpStatus.OK,service.update(id,requestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return responseBuilder(HttpStatus.OK,service.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}