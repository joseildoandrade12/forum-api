package com.joseildoandrade12.forum.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaRequest {
    @NotBlank(message = "Nome é obrigatório!")
    private String nome;

    @NotBlank(message = "Descrição é obrigatória!")
    private String descricao;
}
