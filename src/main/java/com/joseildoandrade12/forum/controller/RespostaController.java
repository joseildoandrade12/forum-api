package com.joseildoandrade12.forum.controller;

import com.joseildoandrade12.forum.model.Resposta;
import com.joseildoandrade12.forum.service.RespostaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/respostas")
@RestController
public class RespostaController {
    private final RespostaService respostaService;

    public RespostaController(RespostaService respostaService) {
        this.respostaService = respostaService;
    }

    @GetMapping
    public ResponseEntity<List<Resposta>> getRespostas() {
        return ResponseEntity.ok(respostaService.listarRespostas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resposta> getRespostaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(respostaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Resposta> postResposta(@RequestBody Resposta resposta) {
        return ResponseEntity.status(201).body(respostaService.criarResposta(resposta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resposta> putResposta(@PathVariable Long id, @RequestBody Resposta resposta) {
        return ResponseEntity.ok(respostaService.atualizarResposta(id, resposta));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteResposta(@PathVariable Long id) {
        respostaService.deletarResposta(id);
        return ResponseEntity.noContent().build();
    }
}
