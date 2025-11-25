package com.clinica.proyecto.application.dto;

import com.clinica.proyecto.infrastructure.modelo.enums.RolUsuario;

public class UsuarioCreateDTO {
    private String nombre;
    private String email;
    private String password;
    private RolUsuario rol;
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public RolUsuario getRol() { return rol; }
    public void setRol(RolUsuario rol) { this.rol = rol; }
}
