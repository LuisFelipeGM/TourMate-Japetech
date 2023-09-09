package com.japetech.tourmate.controller;

import com.japetech.tourmate.dtos.PreferenciaDto;
import com.japetech.tourmate.models.PreferenciaModel;
import com.japetech.tourmate.models.UsuarioModel;
import com.japetech.tourmate.services.PreferenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "Preferencia", description = "API para o gerenciamento de preferencias no sistema")
@RestController
@RequestMapping("/preferencias")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PreferenciaController extends GenericController{

    final PreferenciaService preferenciaService;


    public PreferenciaController(PreferenciaService preferenciaService) {
        this.preferenciaService = preferenciaService;
    }


    @Operation(summary = "Lista todas as preferencias", description = "Lista todas as preferencias do sistema")
    @ApiResponse(responseCode = "200", description = "Preferencias encontradas com sucesso",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PreferenciaModel.class))))
    @GetMapping("/")
    public ResponseEntity<Object> get(){
        return ResponseEntity.status(HttpStatus.OK).body(preferenciaService.getAll());
    }

    @Operation(summary = "Recupera uma preferencia por ID", description = "Recupera os dados de uma preferencia a partir do seu ID")
    @ApiResponse(responseCode = "200", description = "Preferencia encontrada com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PreferenciaModel.class)))
    @ApiResponse(responseCode = "404", description = "Preferencia não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<PreferenciaModel> getById(@PathVariable Long id) {
        Optional<PreferenciaModel> optionalPreferencia = preferenciaService.findById(id);
        return optionalPreferencia.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Altera uma preferencia pelo Id" , description = "Altera uma preferencia a partir do seu ID")
    @ApiResponse(responseCode = "200", description = "Preferencia alterada com sucesso")
    @PutMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable Long id, @Valid @RequestBody PreferenciaDto preferenciaDto, BindingResult result){
        Optional<PreferenciaModel> optionalPreferencia = preferenciaService.findById(id);
        if (optionalPreferencia.isEmpty())
            return ResponseEntity.notFound().build();
        try {
            return result.hasErrors() ? ResponseEntity.unprocessableEntity().body(getErrors(result))
                    : ResponseEntity.status(HttpStatus.CREATED).body(preferenciaService.putPreferencia(preferenciaDto, id));
        } catch (Exception e) {
            return handleErrors(e);
        }
    }

}
