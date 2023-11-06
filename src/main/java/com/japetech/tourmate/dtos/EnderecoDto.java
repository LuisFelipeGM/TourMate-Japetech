package com.japetech.tourmate.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record EnderecoDto (

        @Schema(example = "06681789")
        @NotNull(message = "O CEP é obrigatório")
        Long cep,

        @Schema(example = "Rua Fidêncio Ramos")
        @NotBlank(message = "O Logradouro é obrigatório")
        String logradouro,

        @Schema(example = "1222")
        Integer numero,

        @Schema(example = "Prédio 2 - 548")
        String complementoNumero,

        @Schema(example = "Segunda entrada da rua principal")
        String pontoReferencia,

        @Schema(example = "Vila Olímpia")
        @NotBlank(message = "O Bairro é obrigatório")
        String bairro,

        @Schema(example = "São Paulo")
        @NotBlank(message = "A Cidade é obrigatório")
        String cidade,

        @Schema(example = "São Paulo")
        @NotBlank(message = "O Estado é obrigatório")
        String estado,

        @Schema(example = "SP")
        @NotBlank(message = "A Sigla do Estado é obrigatório")
        String siglaEstado,

        @Schema(example = "1")
        @NotNull(message = "O Id do Usuario é obrigatório")
        Long idUsuario
) { }
