package com.joseildoandrade12.forum.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotBlank(message = "Email é obrigatório!")
    private String email;
    @NotBlank(message = "senha é obrigatória!")
    private String senha;
}
