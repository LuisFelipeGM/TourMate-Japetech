package com.japetech.tourmate.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnderecoDto {

    @Schema(example = "06681789")
    @NotNull(message = "O CEP é obrigatório")
    private Long cep;

    @Schema(example = "Rua Fidêncio Ramos")
    @NotBlank(message = "O Logradouro é obrigatório")
    private String logradouro;

    @Schema(example = "1222")
    private Integer numero;

    @Schema(example = "Prédio 2 - 548")
    private String complementoNumero;

    @Schema(example = "Segunda entrada da rua principal")
    private String pontoReferencia;

    @Schema(example = "Vila Olímpia")
    @NotBlank(message = "O Bairro é obrigatório")
    private String bairro;

    @Schema(example = "São Paulo")
    @NotBlank(message = "A Cidade é obrigatório")
    private String cidade;

    @Schema(example = "São Paulo")
    @NotBlank(message = "O Estado é obrigatório")
    private String estado;

    @Schema(example = "SP")
    @NotBlank(message = "A Sigla do Estado é obrigatório")
    private String siglaEstado;

    @Schema(example = "1")
    @NotNull(message = "O Id do Usuario é obrigatório")
    private Long idUsuario;

}
