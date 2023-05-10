package com.project.juntas.controller;

import com.project.juntas.dto.place.PlaceRequestDto;
import com.project.juntas.service.PlaceService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


import static com.project.juntas.exception.handler.ResponseBuilder.responseBuilder;

@RestController
@RequestMapping("/places")
public record PlaceController(PlaceService placeService) {


    @PostMapping
    public ResponseEntity<?> createPlace(@Valid @RequestBody PlaceRequestDto toCreate) {
        return responseBuilder(HttpStatus.CREATED,placeService.createPlace(toCreate));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePlace(@PathVariable Long id, @RequestBody PlaceRequestDto toUpdate) {

        return responseBuilder(HttpStatus.OK,placeService.updatePlace(id, toUpdate));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return responseBuilder(HttpStatus.FOUND,placeService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlace(@PathVariable Long id) {
        placeService.deletePlace(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}