package com.joseildoandrade12.forum.dto;

import com.joseildoandrade12.forum.model.Resposta;
import com.joseildoandrade12.forum.model.Topico;
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
    private Topico topico;

    public RespostaResponse(Resposta resposta) {
        this.id = resposta.getId();
        this.mensagem = resposta.getMensagem();
        this.dataCriacao = resposta.getDataCriacao();
        this.topico = resposta.getTopico();
    }
}
