package com.clinica.proyecto.application.controller;

import com.clinica.proyecto.infrastructure.modelo.Cita;
import com.clinica.proyecto.infrastructure.repository.CitaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
@Validated
public class CitaController {
    private final CitaRepository repository;
    public CitaController(CitaRepository repository) {
        this.repository = repository;
    }
    @GetMapping
    public List<Cita> listar() {
        return repository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cita> obtener(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public Cita crear(@RequestBody Cita entidad) {
        return repository.save(entidad);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Cita> actualizar(@PathVariable Long id, @RequestBody Cita entidad) {
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
