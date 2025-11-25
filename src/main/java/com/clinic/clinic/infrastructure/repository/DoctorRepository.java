package com.clinic.clinic.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.clinic.infrastructure.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}