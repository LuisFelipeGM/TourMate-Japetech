package com.japetech.tourmate.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioDto {

    @Schema(example = "Luís Felipe Garcia Menezes")
    private String nome;

    @Schema(example = "luis.felipe@gmail.com")
    private String email;

    @Schema(example = "Luis123456#")
    private String senha;

    @Schema(example = "98651735847")
    private String cpf;

    @Schema(example = "2003-09-01")
    private LocalDate dataNascimento;

    @Schema(example = "U")
    @Size(min = 1, max = 1, message = "O Tipo do usuario deve ter um único caractere.")
    private String tipoUsuario;

    @Schema(example = "M")
    @Size(min = 1, max = 1, message = "O gênero deve ter um único caractere.")
    private String sexo;

}
