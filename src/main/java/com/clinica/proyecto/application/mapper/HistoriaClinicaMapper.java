package com.clinica.proyecto.application.mapper;

import com.clinica.proyecto.application.dto.HistoriaClinicaDTO;
import com.clinica.proyecto.infrastructure.modelo.HistoriaClinica;

public class HistoriaClinicaMapper {
    public static HistoriaClinicaDTO toDTO(HistoriaClinica e) {
        HistoriaClinicaDTO dto = new HistoriaClinicaDTO();
        dto.setId(e.getId());
        dto.setPacienteId(e.getPaciente() != null ? e.getPaciente().getId() : null);
        dto.setMedicoId(e.getMedico() != null ? e.getMedico().getId() : null);
        dto.setTratamientoId(e.getTratamiento() != null ? e.getTratamiento().getId() : null);
        dto.setDetalles(e.getDetalles());
        dto.setDiagnostico(e.getDiagnostico());
        dto.setFecha(e.getFecha());
        return dto;
    }
}
