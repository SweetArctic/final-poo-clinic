package com.clinica.proyecto.application.controller;

import com.clinica.proyecto.application.dto.HistoriaClinicaDTO;
import com.clinica.proyecto.application.mapper.HistoriaClinicaMapper;
import com.clinica.proyecto.infrastructure.modelo.*;
import com.clinica.proyecto.infrastructure.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/historias")
@Validated
public class HistoriaClinicaController {
    private final HistoriaClinicaRepository repository;
    private final PacienteRepository pacienteRepository;
    private final DoctorRepository doctorRepository;
    private final TratamientoRepository tratamientoRepository;
    public HistoriaClinicaController(HistoriaClinicaRepository repository, PacienteRepository pacienteRepository, DoctorRepository doctorRepository, TratamientoRepository tratamientoRepository) {
        this.repository = repository;
        this.pacienteRepository = pacienteRepository;
        this.doctorRepository = doctorRepository;
        this.tratamientoRepository = tratamientoRepository;
    }
    @GetMapping
    public List<HistoriaClinicaDTO> listar() {
        return repository.findAll().stream().map(HistoriaClinicaMapper::toDTO).collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<HistoriaClinicaDTO> obtener(@PathVariable Long id) {
        return repository.findById(id).map(HistoriaClinicaMapper::toDTO).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<HistoriaClinicaDTO> crear(@RequestBody HistoriaClinicaDTO dto) {
        Paciente paciente = dto.getPacienteId() != null ? pacienteRepository.findById(dto.getPacienteId()).orElse(null) : null;
        Doctor doctor = dto.getMedicoId() != null ? doctorRepository.findById(dto.getMedicoId()).orElse(null) : null;
        Tratamiento tratamiento = dto.getTratamientoId() != null ? tratamientoRepository.findById(dto.getTratamientoId()).orElse(null) : null;
        if (paciente == null || doctor == null) {
            return ResponseEntity.badRequest().build();
        }
        HistoriaClinica entidad = new HistoriaClinica();
        entidad.setPaciente(paciente);
        entidad.setMedico(doctor);
        entidad.setTratamiento(tratamiento);
        entidad.setDetalles(dto.getDetalles());
        entidad.setDiagnostico(dto.getDiagnostico());
        entidad.setFecha(dto.getFecha());
        return ResponseEntity.ok(HistoriaClinicaMapper.toDTO(repository.save(entidad)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<HistoriaClinicaDTO> actualizar(@PathVariable Long id, @RequestBody HistoriaClinicaDTO dto) {
        HistoriaClinica entidad = repository.findById(id).orElse(null);
        if (entidad == null) {
            return ResponseEntity.notFound().build();
        }
        Paciente paciente = dto.getPacienteId() != null ? pacienteRepository.findById(dto.getPacienteId()).orElse(null) : null;
        Doctor doctor = dto.getMedicoId() != null ? doctorRepository.findById(dto.getMedicoId()).orElse(null) : null;
        Tratamiento tratamiento = dto.getTratamientoId() != null ? tratamientoRepository.findById(dto.getTratamientoId()).orElse(null) : null;
        if (paciente == null || doctor == null) {
            return ResponseEntity.badRequest().build();
        }
        entidad.setPaciente(paciente);
        entidad.setMedico(doctor);
        entidad.setTratamiento(tratamiento);
        entidad.setDetalles(dto.getDetalles());
        entidad.setDiagnostico(dto.getDiagnostico());
        entidad.setFecha(dto.getFecha());
        return ResponseEntity.ok(HistoriaClinicaMapper.toDTO(repository.save(entidad)));
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
