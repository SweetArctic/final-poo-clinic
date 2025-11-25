package com.clinica.proyecto.application.controller;

import com.clinica.proyecto.application.dto.DoctorDTO;
import com.clinica.proyecto.application.mapper.DoctorMapper;
import com.clinica.proyecto.infrastructure.modelo.Doctor;
import com.clinica.proyecto.infrastructure.repository.DoctorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/doctores")
@Validated
public class DoctorController {
    private final DoctorRepository repository;
    public DoctorController(DoctorRepository repository) {
        this.repository = repository;
    }
    @GetMapping
    public List<DoctorDTO> listar() {
        return repository.findAll().stream().map(DoctorMapper::toDTO).collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> obtener(@PathVariable Long id) {
        return repository.findById(id).map(DoctorMapper::toDTO).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public DoctorDTO crear(@RequestBody DoctorDTO dto) {
        Doctor entidad = DoctorMapper.toEntity(dto);
        entidad.setId(null);
        return DoctorMapper.toDTO(repository.save(entidad));
    }
    @PutMapping("/{id}")
    public ResponseEntity<DoctorDTO> actualizar(@PathVariable Long id, @RequestBody DoctorDTO dto) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Doctor entidad = repository.findById(id).orElse(null);
        if (entidad == null) return ResponseEntity.notFound().build();
        entidad.setNombre(dto.getNombre());
        entidad.setEspecialidad(dto.getEspecialidad());
        return ResponseEntity.ok(DoctorMapper.toDTO(repository.save(entidad)));
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
