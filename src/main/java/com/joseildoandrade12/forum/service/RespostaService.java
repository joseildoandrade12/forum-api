package com.joseildoandrade12.forum.service;

import com.joseildoandrade12.forum.model.Resposta;
import com.joseildoandrade12.forum.repository.RespostaRepository;
import com.joseildoandrade12.forum.repository.TopicoRepository;
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
        if (resposta.getMensagem() == null || resposta.getMensagem().isEmpty()) {
            throw new IllegalArgumentException("Mensagem é um campo obrigatório");
        }
        if (!topicoRepository.existsById(resposta.getTopico().getId())) {
            throw new IllegalArgumentException("Tópico inválido");
        }
        return respostaRepository.save(resposta);
    }

    public Resposta buscarPorId(Long id) {
        return respostaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Resposta não encontrada, id inválido!"));
    }

    public Resposta atualizarResposta(Long id, Resposta dados) {
        Resposta resposta = respostaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Resposta não encontrada, id inválido!"));
        resposta.setMensagem(dados.getMensagem());
        if (!topicoRepository.existsById(dados.getTopico().getId())) {
            throw new IllegalArgumentException("Tópico não encontrado, id inválido!");
        }
        resposta.setTopico(dados.getTopico());
        return respostaRepository.save(resposta);
    }

    public void deletarResposta(Long id) {
        Resposta resposta = respostaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Resposta não encontrada, id inválido!"));
        respostaRepository.delete(resposta);
    }

    public List<Resposta> listarRespostasPorTopico(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new IllegalArgumentException("Tópico não encontrado, id inválido!");
        }
        return respostaRepository.findByTopico_Id(id);
    }
}
