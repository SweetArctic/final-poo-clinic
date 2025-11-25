package com.clinica.proyecto.application.mapper;

import com.clinica.proyecto.application.dto.PacienteDTO;
import com.clinica.proyecto.infrastructure.modelo.Paciente;

public class PacienteMapper {
    public static PacienteDTO toDTO(Paciente e) {
        PacienteDTO dto = new PacienteDTO();
        dto.setId(e.getId());
        dto.setNombre(e.getNombre());
        dto.setEmail(e.getEmail());
        dto.setTelefono(e.getTelefono());
        return dto;
    }
    public static Paciente toEntity(PacienteDTO dto) {
        Paciente e = new Paciente();
        e.setId(dto.getId());
        e.setNombre(dto.getNombre());
        e.setEmail(dto.getEmail());
        e.setTelefono(dto.getTelefono());
        return e;
    }
}
