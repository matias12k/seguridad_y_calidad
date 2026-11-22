package com.duoc.seguridadcalidad.controller;

import com.duoc.seguridadcalidad.model.Evento;
import com.duoc.seguridadcalidad.repository.EventoRepository;
import com.duoc.seguridadcalidad.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class EventoController {

    private final EventoRepository eventoRepository;
    private final JwtUtil jwtUtil;

    public EventoController(EventoRepository eventoRepository, JwtUtil jwtUtil) {
        this.eventoRepository = eventoRepository;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping({"/", "/eventos"})
    public String index(Model model) {
        model.addAttribute("eventosDestacados", eventoRepository.findByDestacadoTrue());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/detalle")
    public String detalle() {
        return "detalle";
    }

    @GetMapping("/api/eventos/public/buscar")
    @ResponseBody
    public List<Evento> buscar(@RequestParam(required = false) String query,
                               @RequestParam(required = false) String modalidad) {
        return eventoRepository.buscarEventos(query, modalidad);
    }

    @GetMapping("/api/eventos/privado/{id}")
    @ResponseBody
    public ResponseEntity<?> obtenerDetalle(@PathVariable Long id, @RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body(Map.of("error", "Acceso denegado: Cabecera Authorization con Bearer JWT requerida"));
        }

        String token = authHeader.substring(7);
        if (!jwtUtil.validarToken(token)) {
            return ResponseEntity.status(403).body(Map.of("error", "Token JWT inválido o expirado"));
        }

        return eventoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}