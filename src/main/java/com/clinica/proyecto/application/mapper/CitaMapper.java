package com.clinica.proyecto.application.mapper;

import com.clinica.proyecto.application.dto.CitaDTO;
import com.clinica.proyecto.infrastructure.modelo.Cita;

public class CitaMapper {
    public static CitaDTO toDTO(Cita e) {
        CitaDTO dto = new CitaDTO();
        dto.setId(e.getId());
        dto.setFechaHora(e.getFechaHora());
        dto.setEstado(e.getEstado());
        dto.setPacienteId(e.getPaciente() != null ? e.getPaciente().getId() : null);
        dto.setDoctorId(e.getDoctor() != null ? e.getDoctor().getId() : null);
        dto.setTratamientoId(e.getTratamiento() != null ? e.getTratamiento().getId() : null);
        return dto;
    }
}
