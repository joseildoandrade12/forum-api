package com.joseildoandrade12.forum.controller;

import com.joseildoandrade12.forum.model.Categoria;
import com.joseildoandrade12.forum.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequestMapping("/categorias")
@RestController
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping()
    public ResponseEntity<List<Categoria>> getCategorias() {
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<Categoria> getCategoriaPorID(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(categoriaService.buscarPorId(id));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping()
    public ResponseEntity<Categoria> postCategoria(@RequestBody Categoria categoria) {
        try {
            return ResponseEntity.status(201).body(categoriaService.criarCategoria(categoria));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> putCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.atualizarCategoria(id, categoria));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        categoriaService.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
