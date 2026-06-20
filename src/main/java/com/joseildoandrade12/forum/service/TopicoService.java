package com.joseildoandrade12.forum.service;

import com.joseildoandrade12.forum.model.Categoria;
import com.joseildoandrade12.forum.model.Topico;
import com.joseildoandrade12.forum.repository.CategoriaRepository;
import com.joseildoandrade12.forum.repository.TopicoRepository;
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
        if (topico.getTitulo() == null || topico.getTitulo().isEmpty()) {
            throw new IllegalArgumentException("Título é um campo obrigatório!");
        }
        if (topico.getMensagem() == null || topico.getMensagem().isEmpty()) {
            throw new IllegalArgumentException("Mensagem é um campo obrigatório!");
        }
        if (topico.getCategoria() == null || !categoriaRepository.existsById(topico.getCategoria().getId())) {
            throw new IllegalArgumentException("Categoria é um campo obrigatório!");
        }
        return topicoRepository.save(topico);
    }

    public Topico buscarPorId(Long id) {
        return topicoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Tópico não encontrado, ID inválido!"));
    }

    public Topico atualizarTopico(Long id, Topico dados) {
        Topico topico = topicoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Tópico não encontrado, ID inválido!"));
        topico.setTitulo(dados.getTitulo());
        topico.setMensagem(dados.getMensagem());
        if (!categoriaRepository.existsById(dados.getCategoria().getId())) {
            throw new IllegalArgumentException("Categoria inválida");
        }
        topico.setCategoria(dados.getCategoria());
        return topicoRepository.save(topico);
    }

    public List<Topico> listarTopicos() {
        return topicoRepository.findAll();
    }

    public void deletarTopico(Long id) {
        Topico topico = topicoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Tópico inválido"));
        topicoRepository.delete(topico);
    }

    public List<Topico> listarTopicosPorCategoria(Long id) {
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Categoria inválida"));
        return topicoRepository.findByCategoria_Id(id);
    }


}
