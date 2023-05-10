package com.project.juntas.dto.comfort;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComfortResponseDto {

    private Long comfortId;
    private Boolean acceptChild;
    private Boolean petFriendly;
    private Boolean acceptSmokers;
    private Boolean luggage;
}