package com.project.juntas.controller;

import com.project.juntas.dto.journey.JourneyRequestDto;
import com.project.juntas.service.JourneyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.project.juntas.exception.handler.ResponseBuilder.responseBuilder;

@RestController
@RequestMapping("/journey")
public record JourneyController(JourneyService service){

    @PostMapping
    public ResponseEntity<?> create(@RequestBody JourneyRequestDto requestDto){
        return responseBuilder(HttpStatus.CREATED,service.create(requestDto));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<?> update(@RequestBody JourneyRequestDto requestDto) {
        return responseBuilder(HttpStatus.OK,service.update(requestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return responseBuilder(HttpStatus.OK,service.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return responseBuilder(HttpStatus.OK,service.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}