package com.joseildoandrade12.forum.service;

import com.joseildoandrade12.forum.model.Usuario;
import com.joseildoandrade12.forum.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    public UserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario criarUsuario(Usuario usuario) {
        String encode = new BCryptPasswordEncoder().encode(usuario.getSenha());
        usuario.setSenha(encode);
        return usuarioRepository.save(usuario);
    }

    public void buscarUsuarioPorEmail(String email) {
        Optional<Usuario> byEmail = usuarioRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            throw new EntityNotFoundException("Email inválido!");
        }
    }
}
