package com.joseildoandrade12.forum.controller;

import com.joseildoandrade12.forum.model.Categoria;
import com.joseildoandrade12.forum.model.Topico;
import com.joseildoandrade12.forum.service.CategoriaService;
import com.joseildoandrade12.forum.service.TopicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/categorias")
@RestController
public class CategoriaController {
    private final CategoriaService categoriaService;
    private final TopicoService topicoService;

    public CategoriaController(CategoriaService categoriaService, TopicoService topicoService) {
        this.categoriaService = categoriaService;
        this.topicoService = topicoService;
    }

    @GetMapping()
    public ResponseEntity<List<Categoria>> getCategorias() {
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaPorID(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.buscarPorId(id));
    }

    @PostMapping()
    public ResponseEntity<Categoria> postCategoria(@RequestBody Categoria categoria) {
        return ResponseEntity.status(201).body(categoriaService.criarCategoria(categoria));
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

    @GetMapping("{id}/topicos")
    public ResponseEntity<List<Topico>> getTopicosPorCategoria(@PathVariable Long id) {
        return ResponseEntity.ok(topicoService.listarTopicosPorCategoria(id));
    }
}
