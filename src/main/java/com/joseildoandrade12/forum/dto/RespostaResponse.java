package com.joseildoandrade12.forum.dto;

import com.joseildoandrade12.forum.model.Resposta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RespostaResponse {
    private Long id;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private TopicoResponse topico;
    private String nomeAutor;

    public RespostaResponse(Resposta resposta) {
        this.id = resposta.getId();
        this.mensagem = resposta.getMensagem();
        this.dataCriacao = resposta.getDataCriacao();
        this.topico = new TopicoResponse(resposta.getTopico());
        this.nomeAutor = topico.getNomeAutor();
    }
}
