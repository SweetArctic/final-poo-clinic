package com.clinica.proyecto.infrastructure.repository;

import com.clinica.proyecto.infrastructure.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
