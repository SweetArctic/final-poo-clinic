package com.clinica.proyecto.application.controller;

import com.clinica.proyecto.application.dto.CitaDTO;
import com.clinica.proyecto.application.mapper.CitaMapper;
import com.clinica.proyecto.infrastructure.modelo.Cita;
import com.clinica.proyecto.infrastructure.modelo.Doctor;
import com.clinica.proyecto.infrastructure.modelo.Paciente;
import com.clinica.proyecto.infrastructure.modelo.Tratamiento;
import com.clinica.proyecto.infrastructure.repository.CitaRepository;
import com.clinica.proyecto.infrastructure.repository.DoctorRepository;
import com.clinica.proyecto.infrastructure.repository.PacienteRepository;
import com.clinica.proyecto.infrastructure.repository.TratamientoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/citas")
@Validated
public class CitaController {
    private final CitaRepository repository;
    private final PacienteRepository pacienteRepository;
    private final DoctorRepository doctorRepository;
    private final TratamientoRepository tratamientoRepository;
    public CitaController(CitaRepository repository, PacienteRepository pacienteRepository, DoctorRepository doctorRepository, TratamientoRepository tratamientoRepository) {
        this.repository = repository;
        this.pacienteRepository = pacienteRepository;
        this.doctorRepository = doctorRepository;
        this.tratamientoRepository = tratamientoRepository;
    }
    @GetMapping
    public List<CitaDTO> listar() {
        return repository.findAll().stream().map(CitaMapper::toDTO).collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CitaDTO> obtener(@PathVariable Long id) {
        return repository.findById(id).map(CitaMapper::toDTO).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<CitaDTO> crear(@RequestBody CitaDTO dto) {
        Paciente paciente = dto.getPacienteId() != null ? pacienteRepository.findById(dto.getPacienteId()).orElse(null) : null;
        Doctor doctor = dto.getDoctorId() != null ? doctorRepository.findById(dto.getDoctorId()).orElse(null) : null;
        Tratamiento tratamiento = dto.getTratamientoId() != null ? tratamientoRepository.findById(dto.getTratamientoId()).orElse(null) : null;
        if (paciente == null || doctor == null) {
            return ResponseEntity.badRequest().build();
        }
        Cita entidad = new Cita();
        entidad.setFechaHora(dto.getFechaHora());
        entidad.setEstado(dto.getEstado());
        entidad.setPaciente(paciente);
        entidad.setDoctor(doctor);
        entidad.setTratamiento(tratamiento);
        return ResponseEntity.ok(CitaMapper.toDTO(repository.save(entidad)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CitaDTO> actualizar(@PathVariable Long id, @RequestBody CitaDTO dto) {
        Cita entidad = repository.findById(id).orElse(null);
        if (entidad == null) {
            return ResponseEntity.notFound().build();
        }
        Paciente paciente = dto.getPacienteId() != null ? pacienteRepository.findById(dto.getPacienteId()).orElse(null) : null;
        Doctor doctor = dto.getDoctorId() != null ? doctorRepository.findById(dto.getDoctorId()).orElse(null) : null;
        Tratamiento tratamiento = dto.getTratamientoId() != null ? tratamientoRepository.findById(dto.getTratamientoId()).orElse(null) : null;
        if (paciente == null || doctor == null) {
            return ResponseEntity.badRequest().build();
        }
        entidad.setFechaHora(dto.getFechaHora());
        entidad.setEstado(dto.getEstado());
        entidad.setPaciente(paciente);
        entidad.setDoctor(doctor);
        entidad.setTratamiento(tratamiento);
        return ResponseEntity.ok(CitaMapper.toDTO(repository.save(entidad)));
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
