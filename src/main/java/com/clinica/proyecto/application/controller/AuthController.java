package com.clinica.proyecto.application.controller;

import com.clinica.proyecto.application.dto.UsuarioDTO;
import com.clinica.proyecto.application.mapper.UsuarioMapper;
import com.clinica.proyecto.infrastructure.modelo.Usuario;
import com.clinica.proyecto.infrastructure.repository.UsuarioRepository;
import com.clinica.proyecto.infrastructure.security.AuthTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthTokenService tokenService;
    public AuthController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, AuthTokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");
        var usuarioOpt = usuarioRepository.findByEmail(email);
        if (usuarioOpt.isEmpty()) return ResponseEntity.status(401).build();
        Usuario u = usuarioOpt.get();
        if (!passwordEncoder.matches(password, u.getPassword())) return ResponseEntity.status(401).build();
        String token = tokenService.generate(u);
        UsuarioDTO dto = UsuarioMapper.toDTO(u);
        return ResponseEntity.ok(Map.of("token", token, "usuario", dto));
    }
}
