package com.joseildoandrade12.forum.service;

import com.joseildoandrade12.forum.model.Topico;
import com.joseildoandrade12.forum.model.Usuario;
import com.joseildoandrade12.forum.repository.CategoriaRepository;
import com.joseildoandrade12.forum.repository.TopicoRepository;
import com.joseildoandrade12.forum.security.AuthUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
public class TopicoService {
    private final TopicoRepository topicoRepository;
    private final CategoriaRepository categoriaRepository;
    private final AuthUtil authUtil;

    public TopicoService(TopicoRepository topicoRepository, CategoriaRepository categoriaRepository, AuthUtil authUtil) {
        this.topicoRepository = topicoRepository;
        this.categoriaRepository = categoriaRepository;
        this.authUtil = authUtil;
    }

    public Topico criarTopico(Topico topico) {
        if (topico.getCategoria() == null || !categoriaRepository.existsById(topico.getCategoria().getId())) {
            throw new EntityNotFoundException("Categoria não encontrada!");
        }
        try {
            Usuario usuario = authUtil.pegarUsuarioPorEmail();
            topico.setAutor(usuario);
        } catch (AccessDeniedException e) {
            throw new RuntimeException("Erro ao tentar encontrar o autor do tópico", e);
        }
        return topicoRepository.save(topico);
    }

    public Topico buscarPorId(Long id) {
        return topicoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado!"));
    }

    public Topico atualizarTopico(Long id, Topico dados) {
        Topico topico = topicoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado!"));
        topico.setTitulo(dados.getTitulo());
        topico.setMensagem(dados.getMensagem());
        if (!categoriaRepository.existsById(dados.getCategoria().getId())) {
            throw new EntityNotFoundException("Categoria inválida");
        }
        try {
            Usuario usuario = authUtil.pegarUsuarioPorEmail();
            if (!usuario.getId().equals(topico.getAutor().getId())) {
                throw new AccessDeniedException("Você não tem permissão para realizar essa ação");
            }
        } catch (AccessDeniedException e) {
            throw new RuntimeException(e);
        }
        topico.setCategoria(dados.getCategoria());
        return topicoRepository.save(topico);
    }

    public List<Topico> listarTopicos() {
        return topicoRepository.findAll();
    }

    public void deletarTopico(Long id) {
        Topico topico = topicoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tópico inválido"));
        try {
            Usuario usuario = authUtil.pegarUsuarioPorEmail();
            if (!usuario.getId().equals(topico.getAutor().getId())) {
                throw new AccessDeniedException("Você não tem permissão para realizar essa ação");
            }
        } catch (AccessDeniedException e) {
            throw new RuntimeException(e);
        }
        topicoRepository.delete(topico);
    }

    public List<Topico> listarTopicosPorCategoria(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new EntityNotFoundException("Categoria inválida");
        }
        return topicoRepository.findByCategoria_Id(id);
    }
}
