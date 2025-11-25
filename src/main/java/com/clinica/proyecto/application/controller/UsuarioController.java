package com.clinica.proyecto.application.controller;

import com.clinica.proyecto.application.dto.UsuarioCreateDTO;
import com.clinica.proyecto.application.dto.UsuarioDTO;
import com.clinica.proyecto.application.mapper.UsuarioMapper;
import com.clinica.proyecto.infrastructure.modelo.Usuario;
import com.clinica.proyecto.infrastructure.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
@Validated
public class UsuarioController {
    private final UsuarioRepository repository;
    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }
    @GetMapping
    public List<UsuarioDTO> listar() {
        return repository.findAll().stream().map(UsuarioMapper::toDTO).collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtener(@PathVariable Long id) {
        return repository.findById(id).map(UsuarioMapper::toDTO).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public UsuarioDTO crear(@RequestBody UsuarioCreateDTO dto) {
        Usuario e = UsuarioMapper.toEntity(dto);
        return UsuarioMapper.toDTO(repository.save(e));
    }
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizar(@PathVariable Long id, @RequestBody UsuarioDTO dto) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Usuario e = repository.findById(id).orElse(null);
        if (e == null) return ResponseEntity.notFound().build();
        e.setNombre(dto.getNombre());
        e.setEmail(dto.getEmail());
        e.setRol(dto.getRol());
        return ResponseEntity.ok(UsuarioMapper.toDTO(repository.save(e)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
