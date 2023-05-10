package com.project.juntas.controller;

import com.project.juntas.dto.comfort.ComfortRequestDto;
import com.project.juntas.service.ComfortService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.project.juntas.exception.handler.ResponseBuilder.responseBuilder;

@RestController
@RequestMapping("/comfort")
public record ComfortController(ComfortService service) {

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ComfortRequestDto requestDto){
        return responseBuilder(HttpStatus.CREATED,service.create(requestDto));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<?> update(@PathVariable Long id, @RequestBody ComfortRequestDto requestDto) {
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
