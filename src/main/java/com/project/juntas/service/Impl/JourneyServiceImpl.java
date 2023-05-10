package com.project.juntas.service.Impl;

import com.project.juntas.dto.journey.JourneyRequestDto;
import com.project.juntas.dto.journey.JourneyResponseDto;
import com.project.juntas.exception.ResourceNotFoundException;
import com.project.juntas.mapper.GenericMapper;
import com.project.juntas.model.Comfort;
import com.project.juntas.model.Journey;
import com.project.juntas.model.Place;
import com.project.juntas.model.Vehicle;
import com.project.juntas.repository.ComfortRepository;
import com.project.juntas.repository.JourneyRepository;
import com.project.juntas.repository.PlaceRepository;
import com.project.juntas.repository.VehicleRepository;
import com.project.juntas.service.JourneyService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JourneyServiceImpl implements JourneyService {

    private final GenericMapper mapper;
    private final JourneyRepository repository;
    private final ComfortRepository comfortRepository;
    private final VehicleRepository vehicleRepository;
    private final PlaceRepository placeRepository;


    @Override
    public JourneyResponseDto create(JourneyRequestDto request) {

        Journey journey = mapper.map(request, Journey.class);
        Comfort comfort = comfortRepository.findById(request.getComfortId()).orElseThrow(()->new ResourceNotFoundException(""));
        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId()).orElseThrow(()->new ResourceNotFoundException(""));
        Place arrival = placeRepository.findById(request.getArrivalId()).orElseThrow(()->new ResourceNotFoundException(""));
        Place departure = placeRepository.findById(request.getDepartureId()).orElseThrow(()->new ResourceNotFoundException(""));

        journey.setComfort(comfort);
        journey.setVehicle(vehicle);
        journey.setArrival(arrival);
        journey.setDeparture(departure);

        return mapper.map(repository.save(journey), JourneyResponseDto.class);
    }

    @Override
    public JourneyResponseDto update(JourneyRequestDto request) {
        Journey journey = repository.findById(request.getId()).orElseThrow(()->new ResourceNotFoundException(""));
        Comfort comfort = comfortRepository.findById(request.getComfortId()).orElseThrow(()->new ResourceNotFoundException(""));
        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId()).orElseThrow(()->new ResourceNotFoundException(""));
        Place arrival = placeRepository.findById(request.getArrivalId()).orElseThrow(()->new ResourceNotFoundException(""));
        Place departure = placeRepository.findById(request.getDepartureId()).orElseThrow(()->new ResourceNotFoundException(""));

        journey.setArrivalDate(request.getArrivalDate());
        journey.setDepartureDate(request.getDepartureDate());
        journey.setComfort(comfort);
        journey.setVehicle(vehicle);
        journey.setArrival(arrival);
        journey.setDeparture(departure);

        return mapper.map(repository.save(journey), JourneyResponseDto.class);
    }

    @Override
    public JourneyResponseDto getById(Long id){
        Journey journey = repository.findById(id).orElseThrow(()->new ResourceNotFoundException(""));
        return mapper.map(journey,JourneyResponseDto.class);
    }

    @Override
    public List<JourneyResponseDto> getAll(){
        return mapper.mapAll(repository.findAll(),JourneyResponseDto.class);
    }

    @Override
    public void delete(Long id){
        Journey journey = repository.findById(id).orElseThrow(()->new ResourceNotFoundException(""));
        repository.delete(journey);
    }

}
