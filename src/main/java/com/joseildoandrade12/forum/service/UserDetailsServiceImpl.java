package com.joseildoandrade12.forum.service;

import com.joseildoandrade12.forum.model.Usuario;
import com.joseildoandrade12.forum.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> byEmail = usuarioRepository.findByEmail(email);
        return byEmail.orElseThrow(() -> new UsernameNotFoundException("Email inválido!"));
    }
}
