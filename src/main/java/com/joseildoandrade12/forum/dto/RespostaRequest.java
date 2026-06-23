package com.joseildoandrade12.forum.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespostaRequest {
    @NotBlank(message = "mensagem é obrigátoria!")
    private String mensagem;

    @NotNull(message = "Tópico é obrigatório! ")
    private Long topicoId;
}
