package com.japetech.tourmate.controller;

import com.japetech.tourmate.dtos.ViagemDto;
import com.japetech.tourmate.models.UsuarioModel;
import com.japetech.tourmate.models.ViagemModel;
import com.japetech.tourmate.services.ViagemService;
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

@Tag(name = "Viagem", description = "API para o gerenciamento de viagens no sistema")
@RestController
@RequestMapping("/viagens/")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ViagemController extends GenericController{

    final ViagemService viagemService;

    public ViagemController(ViagemService viagemService) {
        this.viagemService = viagemService;
    }


    @Operation(summary = "Lista todos as viagens", description = "Lista todas as viagens do sistema")
    @ApiResponse(responseCode = "200", description = "Viagens encontradas com sucesso",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ViagemModel.class))))
    @GetMapping("/")
    public ResponseEntity<Object> get(){
        return ResponseEntity.status(HttpStatus.OK).body(viagemService.getAll());
    }


    @Operation(summary = "Recupera uma viagem pelo ID", description = "Recupera os dados de uma viagem a partir do seu ID")
    @ApiResponse(responseCode = "200", description = "Viagem encontrada com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ViagemModel.class)))
    @ApiResponse(responseCode = "404", description = "Viagem não encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<ViagemModel> getById(@PathVariable Long id){
        Optional<ViagemModel> optionalViagem = viagemService.findById(id);
        return optionalViagem.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Salva a viagem", description = "Salva a viagem")
    @ApiResponse(responseCode = "201", description = "Viagem salva com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ViagemModel.class)))
    @PostMapping("/")
    public ResponseEntity<Object> save(@Valid @RequestBody ViagemDto viagemDto, BindingResult result){
        try {
            return result.hasErrors() ? ResponseEntity.unprocessableEntity().body(getErrors(result))
                    : ResponseEntity.status(HttpStatus.CREATED).body(viagemService.adicionaViagem(viagemDto));
        } catch (Exception e) {
            return handleErrors(e);
        }
    }


    @Operation(summary = "Exclui uma viagem pelo Id" , description = "Exclui uma viagem a partir do seu ID")
    @ApiResponse(responseCode = "204", description = "Viagem excluida com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        Optional<ViagemModel> optionalViagem = viagemService.findById(id);
        return optionalViagem
                .map(viagens -> {
                    try {
                        viagemService.deleteById(id);
                        return ResponseEntity.noContent().build();
                    } catch (Exception e) {
                        return handleErrors(e);
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }



}
