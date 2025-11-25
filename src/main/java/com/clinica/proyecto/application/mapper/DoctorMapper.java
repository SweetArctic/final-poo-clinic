package com.clinica.proyecto.application.mapper;

import com.clinica.proyecto.application.dto.DoctorDTO;
import com.clinica.proyecto.infrastructure.modelo.Doctor;

public class DoctorMapper {
    public static DoctorDTO toDTO(Doctor e) {
        DoctorDTO dto = new DoctorDTO();
        dto.setId(e.getId());
        dto.setNombre(e.getNombre());
        dto.setEspecialidad(e.getEspecialidad());
        return dto;
    }
    public static Doctor toEntity(DoctorDTO dto) {
        Doctor e = new Doctor();
        e.setId(dto.getId());
        e.setNombre(dto.getNombre());
        e.setEspecialidad(dto.getEspecialidad());
        return e;
    }
}
