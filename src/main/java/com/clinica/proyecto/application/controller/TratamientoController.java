package com.clinica.proyecto.application.controller;

import com.clinica.proyecto.application.dto.TratamientoDTO;
import com.clinica.proyecto.application.mapper.TratamientoMapper;
import com.clinica.proyecto.infrastructure.modelo.Tratamiento;
import com.clinica.proyecto.infrastructure.repository.TratamientoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tratamientos")
@Validated
public class TratamientoController {
    private final TratamientoRepository repository;
    public TratamientoController(TratamientoRepository repository) {
        this.repository = repository;
    }
    @GetMapping
    public List<TratamientoDTO> listar() {
        return repository.findAll().stream().map(TratamientoMapper::toDTO).collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<TratamientoDTO> obtener(@PathVariable Long id) {
        return repository.findById(id).map(TratamientoMapper::toDTO).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public TratamientoDTO crear(@RequestBody TratamientoDTO dto) {
        Tratamiento entidad = TratamientoMapper.toEntity(dto);
        entidad.setId(null);
        return TratamientoMapper.toDTO(repository.save(entidad));
    }
    @PutMapping("/{id}")
    public ResponseEntity<TratamientoDTO> actualizar(@PathVariable Long id, @RequestBody TratamientoDTO dto) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Tratamiento entidad = repository.findById(id).orElse(null);
        if (entidad == null) return ResponseEntity.notFound().build();
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setCosto(dto.getCosto());
        return ResponseEntity.ok(TratamientoMapper.toDTO(repository.save(entidad)));
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
