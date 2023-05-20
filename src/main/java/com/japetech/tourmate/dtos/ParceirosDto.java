package com.japetech.tourmate.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ParceirosDto {

    @Schema(example = "Hotel 5")
    private String nomeFantasia;

    @Schema(example = "12345678912345")
    private String cnpj;

    @Schema(example = "(11) 97460-9481")
    private String telefone;

}
