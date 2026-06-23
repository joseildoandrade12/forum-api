package com.joseildoandrade12.forum.controller;

import com.joseildoandrade12.forum.dto.CategoriaRequest;
import com.joseildoandrade12.forum.dto.CategoriaResponse;
import com.joseildoandrade12.forum.dto.TopicoResponse;
import com.joseildoandrade12.forum.model.Categoria;
import com.joseildoandrade12.forum.model.Topico;
import com.joseildoandrade12.forum.service.CategoriaService;
import com.joseildoandrade12.forum.service.TopicoService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<CategoriaResponse>> getCategorias() {
        List<Categoria> categorias = categoriaService.listarCategorias();
        List<CategoriaResponse> listCategorias = categorias.stream().map(CategoriaResponse::new).toList();
        return ResponseEntity.ok(listCategorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> getCategoriaPorID(@PathVariable Long id) {
        Categoria categoria = categoriaService.buscarPorId(id);
        return ResponseEntity.ok(new CategoriaResponse(categoria));
    }

    @PostMapping()
    public ResponseEntity<CategoriaResponse> postCategoria(@Valid @RequestBody CategoriaRequest request) {
        Categoria categoria = new Categoria();
        categoria.setNome(request.getNome());
        categoria.setDescricao(request.getDescricao());
        Categoria categoriaSaved = categoriaService.criarCategoria(categoria);

        return ResponseEntity.status(201).body(new CategoriaResponse(categoriaSaved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> putCategoria(@Valid @PathVariable Long id, @RequestBody CategoriaRequest request) {
        Categoria categoria = new Categoria();
        categoria.setNome(request.getNome());
        categoria.setDescricao(request.getDescricao());
        Categoria categoriaSaved = categoriaService.atualizarCategoria(id, categoria);

        return ResponseEntity.ok(new CategoriaResponse(categoriaSaved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        categoriaService.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}/topicos")
    public ResponseEntity<List<TopicoResponse>> getTopicosPorCategoria(@PathVariable Long id) {
        List<Topico> topicos = topicoService.listarTopicosPorCategoria(id);
        List<TopicoResponse> listTopicos = topicos.stream().map(TopicoResponse::new).toList();
        return ResponseEntity.ok(listTopicos);
    }
}
