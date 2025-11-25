package com.clinica.proyecto.application.controller;

import com.clinica.proyecto.infrastructure.modelo.Tratamiento;
import com.clinica.proyecto.infrastructure.repository.TratamientoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tratamientos")
@Validated
public class TratamientoController {
    private final TratamientoRepository repository;
    public TratamientoController(TratamientoRepository repository) {
        this.repository = repository;
    }
    @GetMapping
    public List<Tratamiento> listar() {
        return repository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Tratamiento> obtener(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public Tratamiento crear(@RequestBody Tratamiento entidad) {
        return repository.save(entidad);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Tratamiento> actualizar(@PathVariable Long id, @RequestBody Tratamiento entidad) {
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
