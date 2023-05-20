package com.japetech.tourmate.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PlanoViajemDto {

    @Schema(example = "Viajem Praia Nordeste")
    private String nomeFantasia;

    @Schema(example = "Planejamento de viajem para a praia de Fernando de Noronha")
    private String planoViajem;

    @Schema(example = "Maranhão - Fernando de Noronha")
    private String localViajem;

    @Schema(example = "2023-09-15")
    private LocalDate dataInicio;

    @Schema(example = "2023-09-25")
    private LocalDate dataFim;
}
