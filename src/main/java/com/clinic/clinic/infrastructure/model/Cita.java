package com.clinic.clinic.infrastructure.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "citas")
@Data
public class Cita {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaHora;
    private String motivo;
    
    // Relación Muchos a Uno: Muchas citas pueden ser de UN Doctor
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    // Relación Muchos a Uno: Muchas citas pueden ser de UN Paciente
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
}