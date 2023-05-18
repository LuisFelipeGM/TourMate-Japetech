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
    @NotBlank(message = "O Plano de Viajem é obrigatório!")
    private String planoViajem;

    @Schema(example = "Maranhão - Fernando de Noronha")
    @NotBlank(message = "O Local de viajem é obrigatório!")
    private String localViajem;

    @Schema(example = "2023-09-15")
    @NotNull(message = "A data de inicio é obrigatório!")
    private LocalDate dataInicio;

    @Schema(example = "2023-09-25")
    private LocalDate dataFim;
}
