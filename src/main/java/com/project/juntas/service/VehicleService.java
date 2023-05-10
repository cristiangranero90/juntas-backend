package com.project.juntas.service;

import com.project.juntas.dto.vehicle.VehicleRequestDto;
import com.project.juntas.dto.vehicle.VehicleResponseDto;

public interface VehicleService {

    VehicleResponseDto create(VehicleRequestDto requestDto);

    VehicleResponseDto update(Long id, VehicleRequestDto requestDto);

    VehicleResponseDto getById(Long id);

    void delete(Long id);
}
