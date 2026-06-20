package com.joseildoandrade12.forum.controller;

import com.joseildoandrade12.forum.model.Resposta;
import com.joseildoandrade12.forum.model.Topico;
import com.joseildoandrade12.forum.service.RespostaService;
import com.joseildoandrade12.forum.service.TopicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/topicos")
@RestController
public class TopicoController {
    private final TopicoService topicoService;
    private final RespostaService respostaService;

    public TopicoController(TopicoService topicoService, RespostaService respostaService) {
        this.topicoService = topicoService;
        this.respostaService = respostaService;
    }

    @GetMapping
    public ResponseEntity<List<Topico>> getTopicos() {
        return ResponseEntity.ok(topicoService.listarTopicos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> getTopicoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(topicoService.buscarPorId(id));
    }

    @GetMapping("{id}/respostas")
    public ResponseEntity<List<Resposta>> getRespostasPorTopico(@PathVariable Long id) {
        return ResponseEntity.ok(respostaService.listarRespostasPorTopico(id));
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
