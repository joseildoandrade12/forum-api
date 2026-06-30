package com.joseildoandrade12.forum.service;

import com.joseildoandrade12.forum.model.Resposta;
import com.joseildoandrade12.forum.model.Usuario;
import com.joseildoandrade12.forum.repository.RespostaRepository;
import com.joseildoandrade12.forum.repository.TopicoRepository;
import com.joseildoandrade12.forum.security.AuthUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
public class RespostaService {
    private final RespostaRepository respostaRepository;
    private final TopicoRepository topicoRepository;
    private final AuthUtil authUtil;

    public RespostaService(RespostaRepository respostaRepository, TopicoRepository topicoRepository, AuthUtil authUtil) {
        this.respostaRepository = respostaRepository;
        this.topicoRepository = topicoRepository;
        this.authUtil = authUtil;
    }

    public List<Resposta> listarRespostas() {
        return respostaRepository.findAll();
    }

    public Resposta criarResposta(Resposta resposta) {
        if (resposta.getTopico() == null || !topicoRepository.existsById(resposta.getTopico().getId())) {
            throw new EntityNotFoundException("Tópico inválido");
        }
        try {
            Usuario usuario = authUtil.pegarUsuarioPorEmail();
            resposta.setAutor(usuario);
        } catch (AccessDeniedException e) {
            throw new RuntimeException(e);
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
        try {
            Usuario usuario = authUtil.pegarUsuarioPorEmail();
            if (!usuario.getId().equals(resposta.getAutor().getId())) {
                throw new AccessDeniedException("Você não tem permissão para realizar essa ação");
            }
        } catch (AccessDeniedException e) {
            throw new RuntimeException(e);
        }
        resposta.setTopico(dados.getTopico());
        return respostaRepository.save(resposta);
    }

    public void deletarResposta(Long id) {
        Resposta resposta = respostaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Resposta não encontrada!"));
        try {
            Usuario usuario = authUtil.pegarUsuarioPorEmail();
            if (!usuario.getId().equals(resposta.getAutor().getId())) {
                throw new AccessDeniedException("Você não tem permissão para realizar essa ação");
            }
        } catch (AccessDeniedException e) {
            throw new RuntimeException(e);
        }
        respostaRepository.delete(resposta);
    }

    public List<Resposta> listarRespostasPorTopico(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new EntityNotFoundException("Tópico não encontrado!");
        }
        return respostaRepository.findByTopico_Id(id);
    }
}
