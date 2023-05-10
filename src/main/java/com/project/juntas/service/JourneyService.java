package com.project.juntas.service;


import com.project.juntas.dto.journey.JourneyRequestDto;
import com.project.juntas.dto.journey.JourneyResponseDto;

import java.util.List;

public interface JourneyService {
    JourneyResponseDto create(JourneyRequestDto requestDto);

    JourneyResponseDto update(JourneyRequestDto requestDto);

    JourneyResponseDto getById(Long id);

    List<JourneyResponseDto> getAll();

    void delete(Long id);
}
