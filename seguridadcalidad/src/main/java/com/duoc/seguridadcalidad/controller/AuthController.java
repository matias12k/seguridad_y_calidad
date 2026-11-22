package com.duoc.seguridadcalidad.controller;

import com.duoc.seguridadcalidad.model.Usuario;
import com.duoc.seguridadcalidad.repository.UsuarioRepository;
import com.duoc.seguridadcalidad.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;

    public AuthController(UsuarioRepository usuarioRepository, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        Optional<Usuario> userOpt = usuarioRepository.findByUsername(username);
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            String token = jwtUtil.generarToken(username);
            return ResponseEntity.ok(Map.of(
                "token", token,
                "username", username,
                "nombre", userOpt.get().getNombre()
            ));
        }
        return ResponseEntity.status(401).body(Map.of("error", "Credenciales incorrectas"));
    }
}