package com.clinica.proyecto.infrastructure.repository;

import com.clinica.proyecto.infrastructure.modelo.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaRepository extends JpaRepository<Cita, Long> {
}
