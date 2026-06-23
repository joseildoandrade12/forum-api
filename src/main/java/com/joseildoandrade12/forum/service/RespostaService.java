package com.joseildoandrade12.forum.service;

import com.joseildoandrade12.forum.model.Resposta;
import com.joseildoandrade12.forum.repository.RespostaRepository;
import com.joseildoandrade12.forum.repository.TopicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RespostaService {
    private final RespostaRepository respostaRepository;
    private final TopicoRepository topicoRepository;

    public RespostaService(RespostaRepository respostaRepository, TopicoRepository topicoRepository) {
        this.respostaRepository = respostaRepository;
        this.topicoRepository = topicoRepository;
    }

    public List<Resposta> listarRespostas() {
        return respostaRepository.findAll();
    }

    public Resposta criarResposta(Resposta resposta) {
        if (resposta.getTopico() == null || !topicoRepository.existsById(resposta.getTopico().getId())) {
            throw new EntityNotFoundException("Tópico inválido");
        }
        return respostaRepository.save(resposta);
    }

    public Resposta buscarPorId(Long id) {
        return respostaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Resposta não encontrada!"));
    }

    public Resposta atualizarResposta(Long id, Resposta dados) {
        Resposta resposta = respostaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Resposta não encontrada!"));
        resposta.setMensagem(dados.getMensagem());
        if (!topicoRepository.existsById(dados.getTopico().getId())) {
            throw new EntityNotFoundException("Tópico não encontrado!");
        }
        resposta.setTopico(dados.getTopico());
        return respostaRepository.save(resposta);
    }

    public void deletarResposta(Long id) {
        Resposta resposta = respostaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Resposta não encontrada!"));
        respostaRepository.delete(resposta);
    }

    public List<Resposta> listarRespostasPorTopico(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new EntityNotFoundException("Tópico não encontrado!");
        }
        return respostaRepository.findByTopico_Id(id);
    }
}
