package com.clinica.proyecto.application.controller;

import com.clinica.proyecto.infrastructure.modelo.Doctor;
import com.clinica.proyecto.infrastructure.repository.DoctorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/doctores")
@Validated
public class DoctorController {
    private final DoctorRepository repository;
    public DoctorController(DoctorRepository repository) {
        this.repository = repository;
    }
    @GetMapping
    public List<Doctor> listar() {
        return repository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> obtener(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public Doctor crear(@RequestBody Doctor entidad) {
        return repository.save(entidad);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> actualizar(@PathVariable Long id, @RequestBody Doctor entidad) {
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
