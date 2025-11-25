package com.clinic.clinic.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.clinic.infrastructure.model.Cita;

public interface CitaRepository extends JpaRepository<Cita, Long> {
}