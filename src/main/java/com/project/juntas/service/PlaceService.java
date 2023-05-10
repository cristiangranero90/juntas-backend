package com.project.juntas.service;

import com.project.juntas.dto.place.PlaceRequestDto;
import com.project.juntas.dto.place.PlaceResponseDto;

import java.util.List;

public interface PlaceService {

    PlaceResponseDto createPlace(PlaceRequestDto toCreate);

    PlaceResponseDto updatePlace(Long id, PlaceRequestDto toUpdate);

    PlaceResponseDto findById(Long id);

    void deletePlace(Long id);
}
