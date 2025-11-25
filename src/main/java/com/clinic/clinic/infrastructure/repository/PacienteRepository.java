package com.clinic.clinic.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.clinic.infrastructure.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}