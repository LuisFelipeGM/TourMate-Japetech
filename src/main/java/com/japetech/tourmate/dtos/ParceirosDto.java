package com.japetech.tourmate.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ParceirosDto {

    @Schema(example = "Hotel 5")
    @NotBlank(message = "O nome fantasia é obrigatório!")
    private String nomeFantasia;

    @Schema(example = "12345678912345")
    @NotBlank(message = "O nome é obrigatório!")
    private String cnpj;

    @Schema(example = "(11) 97460-9481")
    @NotBlank(message = "O telefone é obrigatório!")
    private String telefone;

}
