package com.clinica.proyecto.application.controller;

import com.clinica.proyecto.infrastructure.modelo.Paciente;
import com.clinica.proyecto.infrastructure.repository.PacienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@Validated
public class PacienteController {
    private final PacienteRepository repository;
    public PacienteController(PacienteRepository repository) {
        this.repository = repository;
    }
    @GetMapping
    public List<Paciente> listar() {
        return repository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> obtener(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public Paciente crear(@RequestBody Paciente entidad) {
        return repository.save(entidad);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizar(@PathVariable Long id, @RequestBody Paciente entidad) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        entidad.setId(id);
        return ResponseEntity.ok(repository.save(entidad));
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
