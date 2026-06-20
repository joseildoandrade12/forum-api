package com.joseildoandrade12.forum.controller;

import com.joseildoandrade12.forum.model.Topico;
import com.joseildoandrade12.forum.service.TopicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/topicos")
@RestController
public class TopicoController {
    private final TopicoService topicoService;

    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    @GetMapping
    public ResponseEntity<List<Topico>> getTopicos() {
        return ResponseEntity.ok(topicoService.listarTopicos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> getTopicoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(topicoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Topico> postTopico(@RequestBody Topico topico) {
        return ResponseEntity.status(201).body(topicoService.criarTopico(topico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topico> putTopico(@PathVariable Long id, @RequestBody Topico topico) {
        return ResponseEntity.ok(topicoService.atualizarTopico(id, topico));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTopico(@PathVariable Long id) {
        topicoService.deletarTopico(id);
        return ResponseEntity.noContent().build();
    }
}
