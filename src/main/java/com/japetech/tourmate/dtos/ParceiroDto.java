package com.japetech.tourmate.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ParceiroDto {

    @Schema(example = "FIAP")
    @NotBlank(message = "Nome Fantasia é obrigatório")
    private String nomeFantasia;

    @Schema(example = "12345678912345")
    @NotBlank(message = "CNPJ é obrigatório")
    private String cnpj;

    @Schema(example = "11948753618")
    private String telefone;

}
