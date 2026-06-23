package com.joseildoandrade12.forum.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicoRequest {
    @NotBlank(message = "Título é obrigátorio!")
    private String titulo;

    @NotBlank(message = "Mensagem é obrigátoria!")
    private String mensagem;

    @NotNull(message = "Categoria é obrigatória! ")
    private Long categoriaId;
}
