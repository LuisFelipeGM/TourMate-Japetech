package com.japetech.tourmate.dtos;

import com.japetech.tourmate.enums.Sexo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UsuarioDto (

        @Schema(example = "Luís Felipe Garcia Menezes")
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @Schema(example = "luis.felipe@gmail.com")
        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Formato de e-mail inválido")
        String email,

        @Schema(example = "Luis123456#")
        @NotBlank(message = "A senha é obrigatório")
        String senha,

        @Schema(example = "12345678901")
        @NotNull(message = "CPF é obrigatório")
        String cpf,

        @Schema(example = "2003-09-01")
        LocalDate dataNascimento,

        Sexo sexo,

        @Schema(example = "S")
        @NotBlank(message = "Plano")
        String plus

) { }
