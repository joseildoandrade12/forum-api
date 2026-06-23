package com.joseildoandrade12.forum.service;

import com.joseildoandrade12.forum.model.Categoria;
import com.joseildoandrade12.forum.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria criarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada!"));
    }

    public Categoria atualizarCategoria(Long id, Categoria dados) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada!"));
        categoria.setNome(dados.getNome());
        categoria.setDescricao(dados.getDescricao());
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    public void deletarCategoria(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new EntityNotFoundException("Categoria não encontrada!");
        }
        categoriaRepository.findById(id).ifPresent(categoriaRepository::delete);
    }
}
