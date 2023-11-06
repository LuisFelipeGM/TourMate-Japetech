package com.japetech.tourmate.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErroDto {
    private String mensagem;
    private String detalhes;

    public ErroDto(){}

    public ErroDto(String mensagem, String detalhes) {
        this.mensagem = mensagem;
        this.detalhes = detalhes;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }
}
