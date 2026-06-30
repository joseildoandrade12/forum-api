package com.joseildoandrade12.forum.dto;

import com.joseildoandrade12.forum.model.Topico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TopicoResponse {
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private CategoriaResponse categoria;
    private String nomeAutor;

    public TopicoResponse(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
        this.categoria = new CategoriaResponse(topico.getCategoria());
        this.nomeAutor = topico.getAutor().getNome();
    }
}
