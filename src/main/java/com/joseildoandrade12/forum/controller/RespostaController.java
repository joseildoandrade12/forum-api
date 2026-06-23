package com.joseildoandrade12.forum.controller;

import com.joseildoandrade12.forum.dto.RespostaRequest;
import com.joseildoandrade12.forum.dto.RespostaResponse;
import com.joseildoandrade12.forum.model.Resposta;
import com.joseildoandrade12.forum.model.Topico;
import com.joseildoandrade12.forum.service.RespostaService;
import com.joseildoandrade12.forum.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/respostas")
@RestController
public class RespostaController {
    private final RespostaService respostaService;
    private final TopicoService topicoService;

    public RespostaController(RespostaService respostaService, TopicoService topicoService) {
        this.respostaService = respostaService;
        this.topicoService = topicoService;
    }

    @GetMapping
    public ResponseEntity<List<RespostaResponse>> getRespostas() {
        List<RespostaResponse> listRespostas = respostaService.listarRespostas().stream().map(RespostaResponse::new).toList();
        return ResponseEntity.ok(listRespostas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespostaResponse> getRespostaPorId(@PathVariable Long id) {
        Resposta resposta = respostaService.buscarPorId(id);
        return ResponseEntity.ok(new RespostaResponse(resposta));
    }

    @PostMapping
    public ResponseEntity<RespostaResponse> postResposta(@Valid @RequestBody RespostaRequest request) {
        Resposta resposta = new Resposta();
        resposta.setMensagem(request.getMensagem());
        Topico topico = topicoService.buscarPorId(request.getTopicoId());
        resposta.setTopico(topico);
        Resposta respostaSaved = respostaService.criarResposta(resposta);

        return ResponseEntity.status(201).body(new RespostaResponse(respostaSaved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespostaResponse> putResposta(@Valid @PathVariable Long id, @RequestBody RespostaRequest request) {
        Resposta resposta = new Resposta();
        resposta.setMensagem(request.getMensagem());

        Topico topico = topicoService.buscarPorId(request.getTopicoId());
        resposta.setTopico(topico);

        Resposta respostaSaved = respostaService.atualizarResposta(id, resposta);
        return ResponseEntity.ok(new RespostaResponse(respostaSaved));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteResposta(@PathVariable Long id) {
        respostaService.deletarResposta(id);
        return ResponseEntity.noContent().build();
    }
}
