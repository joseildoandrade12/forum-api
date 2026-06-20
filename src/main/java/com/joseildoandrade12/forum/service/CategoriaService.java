package com.joseildoandrade12.forum.service;

import com.joseildoandrade12.forum.model.Categoria;
import com.joseildoandrade12.forum.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria criarCategoria(Categoria categoria) {
        if (categoria.getNome() == null || categoria.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome é um campo obrigatório!");
        }
        if (categoria.getDescricao() == null || categoria.getDescricao().isEmpty()) {
            throw new IllegalArgumentException("Descrição é um campo obrigatório!");
        }
        return categoriaRepository.save(categoria);
    }

    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada, ID inválido!"));
    }

    public Categoria atualizarCategoria(Long id, Categoria dados) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada, ID inválido!"));
        categoria.setNome(dados.getNome());
        categoria.setDescricao(dados.getDescricao());
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    public void deletarCategoria(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new IllegalArgumentException("Categoria não encontrada, ID inválido!");
        }
        categoriaRepository.findById(id).ifPresent(categoriaRepository::delete);
    }
}
