package com.joseildoandrade12.forum.controller;

import com.joseildoandrade12.forum.dto.UsuarioRequest;
import com.joseildoandrade12.forum.dto.UsuarioResponse;
import com.joseildoandrade12.forum.model.Usuario;
import com.joseildoandrade12.forum.service.UserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private final UserDetailsService userDetailsService;

    public AuthController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<UsuarioResponse> criarUsuario(@RequestBody UsuarioRequest dados) {
        userDetailsService.buscarUsuarioPorEmail(dados.getEmail());
        Usuario usuario = new Usuario();
        usuario.setNome(dados.getNome());
        usuario.setEmail(dados.getEmail());
        usuario.setSenha(dados.getSenha());
        return ResponseEntity.status(201).body(new UsuarioResponse(userDetailsService.criarUsuario(usuario)));
    }
}
