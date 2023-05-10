package com.project.juntas.repository;

import com.project.juntas.model.Journey;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JourneyRepository extends JpaRepository<Journey, Long> {
}
