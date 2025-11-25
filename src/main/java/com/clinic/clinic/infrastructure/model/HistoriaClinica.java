package com.clinic.clinic.infrastructure.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "historias_clinicas")
@Data
public class HistoriaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaCreacion;

    // Relación Uno a Uno inversa con Paciente
    @OneToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    // Una Historia Clínica tiene muchos Tratamientos
    @OneToMany(mappedBy = "historiaClinica", cascade = CascadeType.ALL)
    private List<Tratamiento> tratamientos;
}