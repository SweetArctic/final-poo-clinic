package com.clinic.clinic.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.clinic.infrastructure.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Método mágico de JPA para buscar por username
    Optional<Usuario> findByUsername(String username);
}