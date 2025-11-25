package com.clinica.proyecto.infrastructure.repository;

import com.clinica.proyecto.infrastructure.modelo.Tratamiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {
}
