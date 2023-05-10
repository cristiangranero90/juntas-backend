package com.project.juntas.service;

import com.project.juntas.dto.comfort.ComfortRequestDto;
import com.project.juntas.dto.comfort.ComfortResponseDto;

public interface ComfortService {

    ComfortResponseDto create(ComfortRequestDto requestDto);

    ComfortResponseDto update(Long id, ComfortRequestDto requestDto);

    ComfortResponseDto getById(Long id);

    void delete(Long id);
}
