package com.joseildoandrade12.forum.controller;

import com.joseildoandrade12.forum.dto.RespostaResponse;
import com.joseildoandrade12.forum.dto.TopicoRequest;
import com.joseildoandrade12.forum.dto.TopicoResponse;
import com.joseildoandrade12.forum.model.Categoria;
import com.joseildoandrade12.forum.model.Topico;
import com.joseildoandrade12.forum.service.CategoriaService;
import com.joseildoandrade12.forum.service.RespostaService;
import com.joseildoandrade12.forum.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/topicos")
@RestController
public class TopicoController {
    private final TopicoService topicoService;
    private final RespostaService respostaService;
    private final CategoriaService categoriaService;

    public TopicoController(TopicoService topicoService, RespostaService respostaService, CategoriaService categoriaService) {
        this.topicoService = topicoService;
        this.respostaService = respostaService;
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<TopicoResponse>> getTopicos() {
        List<TopicoResponse> listTopicos = topicoService.listarTopicos().stream().map(TopicoResponse::new).toList();
        return ResponseEntity.ok(listTopicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoResponse> getTopicoPorId(@PathVariable Long id) {
        Topico topico = topicoService.buscarPorId(id);
        return ResponseEntity.ok(new TopicoResponse(topico));
    }

    @GetMapping("{id}/respostas")
    public ResponseEntity<List<RespostaResponse>> getRespostasPorTopico(@PathVariable Long id) {
        List<RespostaResponse> listRespostas = respostaService.listarRespostasPorTopico(id).stream().map(RespostaResponse::new).toList();
        return ResponseEntity.ok(listRespostas);
    }

    @PostMapping
    public ResponseEntity<TopicoResponse> postTopico(@Valid @RequestBody TopicoRequest request) {
        Topico topico = new Topico();
        topico.setTitulo(request.getTitulo());
        topico.setMensagem(request.getMensagem());

        Categoria categoria = categoriaService.buscarPorId(request.getCategoriaId());
        topico.setCategoria(categoria);

        Topico topicoSaved = topicoService.criarTopico(topico);

        return ResponseEntity.status(201).body(new TopicoResponse(topicoSaved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoResponse> putTopico(@Valid @PathVariable Long id, @RequestBody TopicoRequest request) {
        Topico topico = new Topico();
        topico.setTitulo(request.getTitulo());
        topico.setMensagem(request.getMensagem());

        Categoria categoria = categoriaService.buscarPorId(request.getCategoriaId());
        topico.setCategoria(categoria);

        Topico topicoSaved = topicoService.atualizarTopico(id, topico);

        return ResponseEntity.ok(new TopicoResponse(topicoSaved));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTopico(@PathVariable Long id) {
        topicoService.deletarTopico(id);
        return ResponseEntity.noContent().build();
    }
}
