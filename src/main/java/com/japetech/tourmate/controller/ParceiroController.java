package com.japetech.tourmate.controller;

import com.japetech.tourmate.dtos.ParceiroDto;
import com.japetech.tourmate.models.ParceiroModel;
import com.japetech.tourmate.services.ParceiroService;
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

@Tag(name = "Parceiro", description = "API para o gerenciamento de parceiros no sistema")
@RestController
@RequestMapping("/parceiros")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ParceiroController extends GenericController{

    final ParceiroService parceiroService;


    public ParceiroController(ParceiroService parceiroService) {
        this.parceiroService = parceiroService;
    }

    @Operation(summary = "Lista todos os parceiros", description = "Lista todos os parceiros do sistema")
    @ApiResponse(responseCode = "200", description = "Parceiros encontrados com sucesso",
            content = @Content(mediaType = "application/json", array =  @ArraySchema(schema = @Schema(implementation = ParceiroModel.class))))
    @GetMapping("/")
    public ResponseEntity<Object> get(){
        return ResponseEntity.status(HttpStatus.OK).body(parceiroService.getAll());
    }

    @Operation(summary = "Recupera um parceiro por ID", description = "Recupera os dados de um parceiro a partir do seu ID")
    @ApiResponse(responseCode = "200", description = "Parceiro encontrado com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ParceiroModel.class)))
    @ApiResponse(responseCode = "404", description = "Parceiro não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<ParceiroModel> getById(@PathVariable Long id){
        Optional<ParceiroModel> optionalParceiro = parceiroService.findById(id);
        return optionalParceiro.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Salva o parceiro", description = "Salva o parceiro")
    @ApiResponse(responseCode = "201", description = "Parceiro salvo com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ParceiroModel.class)))
    @PostMapping("/")
    public ResponseEntity<Object> save(@Valid @RequestBody ParceiroDto parceiroDto, BindingResult result){
        try {
            return result.hasErrors() ? ResponseEntity.unprocessableEntity().body(getErrors(result))
                    : ResponseEntity.status(HttpStatus.CREATED).body(parceiroService.adicionaParceiro(parceiroDto));
        } catch (Exception e) {
            return handleErrors(e);
        }
    }

    @Operation(summary = "Excluir um parceiro pelo ID", description = "Exclui um parceiro a partir do seu ID")
    @ApiResponse(responseCode = "204", description = "Parceiro excluido com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        Optional<ParceiroModel> optionalParceiro = parceiroService.findById(id);
        return optionalParceiro
                .map(parceiro -> {
                    try {
                        parceiroService.deleteById(id);
                        return ResponseEntity.noContent().build();
                    } catch (Exception e) {
                        return handleErrors(e);
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Altera um parceiro pelo ID", description = "Altera um parceiro a partir do seu ID")
    @ApiResponse(responseCode = "200", description = "Parceiro alterado com sucesso")
    @PutMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable Long id, @Valid @RequestBody ParceiroDto parceiroDto, BindingResult result){
        Optional<ParceiroModel> optionalParceiro = parceiroService.findById(id);
        if (optionalParceiro.isEmpty())
            return ResponseEntity.notFound().build();
        try {
            return result.hasErrors() ? ResponseEntity.unprocessableEntity().body(getErrors(result))
                    : ResponseEntity.status(HttpStatus.CREATED).body(parceiroService.putParceiro(parceiroDto, id));
        } catch (Exception e) {
            return handleErrors(e);
        }
    }


}
