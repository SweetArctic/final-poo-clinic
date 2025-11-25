package com.clinica.proyecto.infrastructure.repository;

import com.clinica.proyecto.infrastructure.modelo.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
