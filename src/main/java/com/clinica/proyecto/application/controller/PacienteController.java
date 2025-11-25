package com.clinica.proyecto.application.controller;

import com.clinica.proyecto.application.dto.PacienteDTO;
import com.clinica.proyecto.application.mapper.PacienteMapper;
import com.clinica.proyecto.infrastructure.modelo.Paciente;
import com.clinica.proyecto.infrastructure.repository.PacienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pacientes")
@Validated
public class PacienteController {
    private final PacienteRepository repository;
    public PacienteController(PacienteRepository repository) {
        this.repository = repository;
    }
    @GetMapping
    public List<PacienteDTO> listar() {
        return repository.findAll().stream().map(PacienteMapper::toDTO).collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> obtener(@PathVariable Long id) {
        return repository.findById(id).map(PacienteMapper::toDTO).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public PacienteDTO crear(@RequestBody PacienteDTO dto) {
        Paciente entidad = PacienteMapper.toEntity(dto);
        entidad.setId(null);
        return PacienteMapper.toDTO(repository.save(entidad));
    }
    @PostMapping("/batch")
    public List<PacienteDTO> crearBatch(@RequestBody List<PacienteDTO> dtos) {
        List<Paciente> entidades = dtos.stream().map(PacienteMapper::toEntity).peek(p -> p.setId(null)).collect(Collectors.toList());
        return repository.saveAll(entidades).stream().map(PacienteMapper::toDTO).collect(Collectors.toList());
    }
    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> actualizar(@PathVariable Long id, @RequestBody PacienteDTO dto) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Paciente entidad = repository.findById(id).orElse(null);
        if (entidad == null) return ResponseEntity.notFound().build();
        entidad.setNombre(dto.getNombre());
        entidad.setEmail(dto.getEmail());
        entidad.setTelefono(dto.getTelefono());
        return ResponseEntity.ok(PacienteMapper.toDTO(repository.save(entidad)));
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
