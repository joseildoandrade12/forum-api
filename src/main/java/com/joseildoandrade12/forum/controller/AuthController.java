package com.joseildoandrade12.forum.controller;

import com.joseildoandrade12.forum.dto.LoginRequest;
import com.joseildoandrade12.forum.dto.LoginResponse;
import com.joseildoandrade12.forum.dto.UsuarioRequest;
import com.joseildoandrade12.forum.dto.UsuarioResponse;
import com.joseildoandrade12.forum.model.Usuario;
import com.joseildoandrade12.forum.security.JwtUtil;
import com.joseildoandrade12.forum.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private final UsuarioService userDetailsService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UsuarioService userDetailsService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/registrar")
    public ResponseEntity<UsuarioResponse> criarUsuario(@Valid @RequestBody UsuarioRequest dados) {
        Usuario usuario = new Usuario();
        usuario.setNome(dados.getNome());
        usuario.setEmail(dados.getEmail());
        usuario.setSenha(dados.getSenha());
        return ResponseEntity.status(201).body(new UsuarioResponse(userDetailsService.criarUsuario(usuario)));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUsuario(@Valid @RequestBody LoginRequest loginRequest) {
        Usuario usuario = userDetailsService.buscarUsuarioPorEmail(loginRequest.getEmail());
        if (!passwordEncoder.matches(loginRequest.getSenha(), usuario.getSenha())) {
            return ResponseEntity.status(401).build();
        }
        String token = jwtUtil.gerarToken(usuario.getEmail());
        return ResponseEntity.ok(new LoginResponse(token));
    }
}
