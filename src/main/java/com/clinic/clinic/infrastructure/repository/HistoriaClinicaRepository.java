package com.clinic.clinic.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.clinic.infrastructure.model.HistoriaClinica;

public interface HistoriaClinicaRepository extends JpaRepository<HistoriaClinica, Long> {
}