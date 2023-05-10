package com.project.juntas.service.Impl;

import com.project.juntas.dto.vehicle.VehicleRequestDto;
import com.project.juntas.dto.vehicle.VehicleResponseDto;
import com.project.juntas.exception.ResourceNotFoundException;
import com.project.juntas.mapper.GenericMapper;
import com.project.juntas.model.User;
import com.project.juntas.model.Vehicle;
import com.project.juntas.repository.UserRepository;
import com.project.juntas.repository.VehicleRepository;
import com.project.juntas.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final GenericMapper mapper;
    private final VehicleRepository repository;



    @Override
    public VehicleResponseDto create(VehicleRequestDto request) {
        Vehicle vehicle = mapper.map(request, Vehicle.class);
        return mapper.map(repository.save(vehicle), VehicleResponseDto.class);
    }

    @Override
    public VehicleResponseDto update(Long id,VehicleRequestDto request) {
        Vehicle vehicle = repository.findById(id).orElseThrow(()->new ResourceNotFoundException(""));

        vehicle.setVehicleColor(request.getVehicleColor());
        vehicle.setEmptySeats(request.getEmptySeats());
        vehicle.setPrimaryBrand(request.getPrimaryBrand());
        vehicle.setModelName(request.getModelName());
        vehicle.setPatentNumber(request.getPatentNumber());


        return mapper.map(repository.save(vehicle), VehicleResponseDto.class);
    }

    @Override
    public VehicleResponseDto getById(Long id){
        Vehicle vehicle = repository.findById(id).orElseThrow(()->new ResourceNotFoundException(""));
        return mapper.map(vehicle,VehicleResponseDto.class);
    }

    @Override
    public void delete(Long id){
        Vehicle vehicle = repository.findById(id).orElseThrow(()->new ResourceNotFoundException(""));
        repository.delete(vehicle);
    }

}
