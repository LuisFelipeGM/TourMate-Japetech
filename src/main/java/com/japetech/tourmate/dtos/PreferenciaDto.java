package com.japetech.tourmate.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PreferenciaDto {

    @Schema(example = "T")
    private String flowUsuario;

    @Schema(example = "F")
    private String climaFrio;

    @Schema(example = "T")
    private String climaQuente;

    @Schema(example = "T")
    private String viajemTurismo;

    @Schema(example = "F")
    private String viajemNegocio;

    @Schema(example = "F")
    private String viajemLazer;

    @Schema(example = "F")
    private String viajemRomantico;

    @Schema(example = "F")
    private String climaChuvoso;

    @Schema(example = "T")
    private String climaEnsolarado;

    @Schema(example = "T")
    private String climaNublado;

    @Schema(example = "F")
    private String climaNeve;

}
