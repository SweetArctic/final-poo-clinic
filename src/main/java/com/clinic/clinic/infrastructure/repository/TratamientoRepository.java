package com.clinic.clinic.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.clinic.infrastructure.model.Tratamiento;

public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {
}