package com.joseildoandrade12.forum.service;

import com.joseildoandrade12.forum.model.Topico;
import com.joseildoandrade12.forum.repository.CategoriaRepository;
import com.joseildoandrade12.forum.repository.TopicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {
    private final TopicoRepository topicoRepository;
    private final CategoriaRepository categoriaRepository;

    public TopicoService(TopicoRepository topicoRepository, CategoriaRepository categoriaRepository) {
        this.topicoRepository = topicoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Topico criarTopico(Topico topico) {
        if (topico.getCategoria() == null || !categoriaRepository.existsById(topico.getCategoria().getId())) {
            throw new EntityNotFoundException("Categoria não encontrada!");
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
        topico.setCategoria(dados.getCategoria());
        return topicoRepository.save(topico);
    }

    public List<Topico> listarTopicos() {
        return topicoRepository.findAll();
    }

    public void deletarTopico(Long id) {
        Topico topico = topicoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tópico inválido"));
        topicoRepository.delete(topico);
    }

    public List<Topico> listarTopicosPorCategoria(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new EntityNotFoundException("Categoria inválida");
        }
        return topicoRepository.findByCategoria_Id(id);
    }
}
