package com.clinica.proyecto.application.mapper;

import com.clinica.proyecto.application.dto.TratamientoDTO;
import com.clinica.proyecto.infrastructure.modelo.Tratamiento;

public class TratamientoMapper {
    public static TratamientoDTO toDTO(Tratamiento e) {
        TratamientoDTO dto = new TratamientoDTO();
        dto.setId(e.getId());
        dto.setDescripcion(e.getDescripcion());
        dto.setCosto(e.getCosto());
        return dto;
    }
    public static Tratamiento toEntity(TratamientoDTO dto) {
        Tratamiento e = new Tratamiento();
        e.setId(dto.getId());
        e.setDescripcion(dto.getDescripcion());
        e.setCosto(dto.getCosto());
        return e;
    }
}
