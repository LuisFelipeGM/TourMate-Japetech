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
    @NotBlank(message = "O nome é obrigatório!")
    private String nome;

    @Schema(example = "luis.felipe@gmail.com")
    @NotBlank(message = "O email é obrigatório!")
    private String email;

    @Schema(example = "Luis123456#")
    @NotBlank(message = "A senha é obrigatória!")
    private String senha;

    @Schema(example = "98651735847")
    @NotNull(message = "O CPF é obrigatório!")
    private String cpf;

    @Schema(example = "2003-09-01")
    @NotNull(message = "A Data de nascimento é obrigatório!")
    private LocalDate dataNascimento;

    @Schema(example = "U")
    @NotBlank(message = "O Tipo do usuario é obrigatório!")
    @Size(min = 1, max = 1, message = "O Tipo do usuario deve ter um único caractere.")
    private String tipoUsuario;

    @Schema(example = "M")
    @NotNull(message = "O gênero é obrigatório!")
    @Size(min = 1, max = 1, message = "O gênero deve ter um único caractere.")
    private String sexo;

}
