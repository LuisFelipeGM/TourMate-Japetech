package com.japetech.tourmate.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public record PreferenciaDto (

        @Schema(example = "N")
        @NotBlank(message = "O clima frio é obrigatório")
        String climaFrio,

        @Schema(example = "S")
        @NotBlank(message = "O clima quente é obrigatório")
        String climaQuente,

        @Schema(example = "S")
        @NotBlank(message = "A viagem turismo é obrigatório")
        String viagemTurismo,

        @Schema(example = "N")
        @NotBlank(message = "A viagem negocio é obrigatório")
        String viagemNegocio,

        @Schema(example = "N")
        String viagemLazer,

        @Schema(example = "S")
        String viagemRomantico,

        @Schema(example = "N")
        String climaChuvoso,

        @Schema(example = "S")
        String climaEnsolarado,

        @Schema(example = "S")
        String climaNublado,

        @Schema(example = "N")
        String climaNeve

) { }
