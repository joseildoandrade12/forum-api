package com.joseildoandrade12.forum.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioRequest {
    @NotBlank(message = "Nome é obrigatório!")
    private String nome;

    @NotBlank(message = "Email é obrigatório!")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    private String senha;
}
