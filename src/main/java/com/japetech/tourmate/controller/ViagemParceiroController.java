package com.japetech.tourmate.controller;

import com.japetech.tourmate.dtos.ViagemParceiroDto;
import com.japetech.tourmate.models.ViagemParceiroModel;
import com.japetech.tourmate.services.ViagemParceiroService;
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

@Tag(name = "Viagem-Parceiro", description = "API para o gerenciamento de viagens parceirros no sistema")
@RestController
@RequestMapping("/viagens-parceiros/")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ViagemParceiroController extends GenericController{

    final ViagemParceiroService viagemParceiroService;

    public ViagemParceiroController(ViagemParceiroService viagemParceiroService) {
        this.viagemParceiroService = viagemParceiroService;
    }

    @Operation(summary = "Lista todos as viagens-parceiros", description = "Lista todas as viagens-parceiros do sistema")
    @ApiResponse(responseCode = "200", description = "Viagens-parceiros encontradas com sucesso",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ViagemParceiroModel.class))))
    @GetMapping("/")
    public ResponseEntity<Object> get(){
        return ResponseEntity.status(HttpStatus.OK).body(viagemParceiroService.getAll());
    }

    @Operation(summary = "Recupera uma viagem-parceiro pelo ID", description = "Recupera os dados de uma viagem-parceiro a partir do seu ID")
    @ApiResponse(responseCode = "200", description = "Viagem-parceiro encontrada com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ViagemParceiroModel.class)))
    @ApiResponse(responseCode = "404", description = "Viagem-parceiro não encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<ViagemParceiroModel> getById(@PathVariable Long id){
        Optional<ViagemParceiroModel> optionalViagemParceiro = viagemParceiroService.findById(id);
        return optionalViagemParceiro.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Salva a viagem-parceiro", description = "Salva a viagem-parceiro")
    @ApiResponse(responseCode = "201", description = "Viagem-parceiro salva com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ViagemParceiroModel.class)))
    @PostMapping("/")
    public ResponseEntity<Object> save(@Valid @RequestBody ViagemParceiroDto viagemParceiroDto, BindingResult result){
        try {
            return result.hasErrors() ? ResponseEntity.unprocessableEntity().body(getErrors(result))
                    : ResponseEntity.status(HttpStatus.CREATED).body(viagemParceiroService.adicionaViagemParceiro(viagemParceiroDto));
        } catch (Exception e) {
            return handleErrors(e);
        }
    }

    @Operation(summary = "Exclui uma viagem-parceiro pelo Id" , description = "Exclui uma viagem-parceiro a partir do seu ID")
    @ApiResponse(responseCode = "204", description = "Viagem-parceiro excluida com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        Optional<ViagemParceiroModel> optionalViagemParceiro = viagemParceiroService.findById(id);
        return optionalViagemParceiro
                .map(viagensParceiro -> {
                    try {
                        viagemParceiroService.deleteById(id);
                        return ResponseEntity.noContent().build();
                    } catch (Exception e) {
                        return handleErrors(e);
                    }
                }).orElse(ResponseEntity.notFound().build());
    }

}
