package com.clinica.proyecto.application.dto;

import com.clinica.proyecto.infrastructure.modelo.enums.RolUsuario;

public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String email;
    private RolUsuario rol;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public RolUsuario getRol() { return rol; }
    public void setRol(RolUsuario rol) { this.rol = rol; }
}
