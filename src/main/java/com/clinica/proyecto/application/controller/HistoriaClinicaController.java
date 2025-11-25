package com.clinica.proyecto.application.controller;

import com.clinica.proyecto.infrastructure.modelo.HistoriaClinica;
import com.clinica.proyecto.infrastructure.repository.HistoriaClinicaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/historias")
@Validated
public class HistoriaClinicaController {
    private final HistoriaClinicaRepository repository;
    public HistoriaClinicaController(HistoriaClinicaRepository repository) {
        this.repository = repository;
    }
    @GetMapping
    public List<HistoriaClinica> listar() {
        return repository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<HistoriaClinica> obtener(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public HistoriaClinica crear(@RequestBody HistoriaClinica entidad) {
        return repository.save(entidad);
    }
    @PutMapping("/{id}")
    public ResponseEntity<HistoriaClinica> actualizar(@PathVariable Long id, @RequestBody HistoriaClinica entidad) {
        return repository.findById(id)
                .map(existente -> {
                    existente.setPaciente(entidad.getPaciente());
                    existente.setMedico(entidad.getMedico());
                    existente.setTratamiento(entidad.getTratamiento());
                    existente.setDetalles(entidad.getDetalles());
                    existente.setDiagnostico(entidad.getDiagnostico());
                    existente.setFecha(entidad.getFecha());
                    return ResponseEntity.ok(repository.save(existente));
                })
                .orElse(ResponseEntity.notFound().build());
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
