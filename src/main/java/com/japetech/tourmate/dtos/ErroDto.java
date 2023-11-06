package com.japetech.tourmate.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;


public record ErroDto (

        String mensagem,

        String detalhes

) {

}
