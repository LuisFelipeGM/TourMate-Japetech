package com.japetech.tourmate.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ViagemDto {

    @Schema(example = "São Paulo")
    @NotBlank(message = "O nome do lugar desejado é obrigatório")
    private String lugarDesejado;

    @Schema(example = "2023-12-20")
    private LocalDate dataInicio;

    @Schema(example = "2023-12-10")
    private LocalDate dataFim;

    @Schema(example = "1")
    @NotNull(message = "O Id do Usuario é obrigatório")
    private Long idUsuario;



}
