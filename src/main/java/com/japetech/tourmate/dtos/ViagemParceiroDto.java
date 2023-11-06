package com.japetech.tourmate.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record ViagemParceiroDto(

        @Schema(example = "2")
        @NotNull(message = "O Id do Parceiro é obrigatório")
        Long idParceiro,

        @Schema(example = "3")
        @NotNull(message = "O Id da Vaigem é obrigatório")
        Long idViagem

) {
}
