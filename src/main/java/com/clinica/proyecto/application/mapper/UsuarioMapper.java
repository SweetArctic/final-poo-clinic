package com.clinica.proyecto.application.mapper;

import com.clinica.proyecto.application.dto.UsuarioCreateDTO;
import com.clinica.proyecto.application.dto.UsuarioDTO;
import com.clinica.proyecto.infrastructure.modelo.Usuario;

public class UsuarioMapper {
    public static UsuarioDTO toDTO(Usuario e) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(e.getId());
        dto.setNombre(e.getNombre());
        dto.setEmail(e.getEmail());
        dto.setRol(e.getRol());
        return dto;
    }
    public static Usuario toEntity(UsuarioCreateDTO dto) {
        Usuario e = new Usuario();
        e.setNombre(dto.getNombre());
        e.setEmail(dto.getEmail());
        e.setPassword(dto.getPassword());
        e.setRol(dto.getRol());
        return e;
    }
}
