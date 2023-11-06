package com.japetech.tourmate.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record ParceiroDto (

        @Schema(example = "FIAP")
        @NotBlank(message = "Nome Fantasia é obrigatório")
        String nomeFantasia,

        @Schema(example = "12345678912345")
        @NotBlank(message = "CNPJ é obrigatório")
        String cnpj,

        @Schema(example = "11948753618")
        String telefone

) { }
