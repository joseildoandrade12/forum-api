package com.joseildoandrade12.forum.dto;

import com.joseildoandrade12.forum.model.Categoria;
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
    private Categoria categoria;

    public TopicoResponse(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
        this.categoria = topico.getCategoria();
    }
}
