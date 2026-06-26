package com.joseildoandrade12.forum.controller;

import com.joseildoandrade12.forum.dto.UsuarioRequest;
import com.joseildoandrade12.forum.dto.UsuarioResponse;
import com.joseildoandrade12.forum.model.Usuario;
import com.joseildoandrade12.forum.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private final UsuarioService userDetailsService;

    public AuthController(UsuarioService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<UsuarioResponse> criarUsuario(@Valid @RequestBody UsuarioRequest dados) {
        Usuario usuario = new Usuario();
        usuario.setNome(dados.getNome());
        usuario.setEmail(dados.getEmail());
        usuario.setSenha(dados.getSenha());
        return ResponseEntity.status(201).body(new UsuarioResponse(userDetailsService.criarUsuario(usuario)));
    }
}
