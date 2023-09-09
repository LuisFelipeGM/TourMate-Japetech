package com.japetech.tourmate.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PreferenciaDto {

    @Schema(example = "N")
    @NotBlank(message = "O clima frio é obrigatório")
    private String climaFrio;

    @Schema(example = "S")
    @NotBlank(message = "O clima quente é obrigatório")
    private String climaQuente;

    @Schema(example = "S")
    @NotBlank(message = "A viagem turismo é obrigatório")
    private String viagemTurismo;

    @Schema(example = "N")
    @NotBlank(message = "A viagem negocio é obrigatório")
    private String viagemNegocio;

    @Schema(example = "N")
    private String viagemLazer;

    @Schema(example = "S")
    private String viagemRomantico;

    @Schema(example = "N")
    private String climaChuvoso;

    @Schema(example = "S")
    private String climaEnsolarado;

    @Schema(example = "S")
    private String climaNublado;

    @Schema(example = "N")
    private String climaNeve;

}
