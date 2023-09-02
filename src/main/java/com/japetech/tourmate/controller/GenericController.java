package com.japetech.tourmate.controller;

import com.japetech.tourmate.dtos.ErroDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class GenericController {

    List<String> getErrors(@NotNull BindingResult result) {
        return result.getAllErrors()
                .stream()
                .map(error -> {
                    if (error instanceof FieldError) {
                        FieldError fieldError = (FieldError) error;
                        return fieldError.getField().toUpperCase(Locale.ROOT) + " : " + error.getDefaultMessage();
                    } else {
                        return error.getDefaultMessage();
                    }
                })
                .collect(Collectors.toList());
    }

    <D, M> M copyProperties(D dto, Class<M> modelClass) {
        M model = BeanUtils.instantiateClass(modelClass);
        BeanUtils.copyProperties(dto, model);
        return model;
    }

    ResponseEntity<Object> handleErrors(@NotNull Exception e) {
        ErroDto erroDTO = new ErroDto("Ocorreu um erro: ", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroDTO);
    }

}
