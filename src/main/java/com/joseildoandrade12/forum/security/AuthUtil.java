package com.joseildoandrade12.forum.security;

import com.joseildoandrade12.forum.model.Usuario;
import com.joseildoandrade12.forum.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.file.AccessDeniedException;

@Component
public class AuthUtil {
    private final UsuarioRepository usuarioRepository;

    public AuthUtil(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario pegarUsuarioPorEmail() throws AccessDeniedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                return usuarioRepository.findByEmail(userDetails.getUsername()).orElseThrow(() -> new EntityNotFoundException("Usuário inválido!"));
            } else {
                throw new AccessDeniedException("Usuário não autenticado");
            }
        } else {
            throw new AccessDeniedException("Usuário não autenticado");
        }
    }
}
