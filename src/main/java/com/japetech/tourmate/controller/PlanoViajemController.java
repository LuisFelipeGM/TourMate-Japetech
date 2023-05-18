package com.japetech.tourmate.controller;

import com.japetech.tourmate.dtos.PlanoViajemDto;
import com.japetech.tourmate.models.PlanoViajemModel;
import com.japetech.tourmate.services.PlanoViajemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Tag(name = "Plano Viajem", description = "API para o gerenciamento de planos de viajem no sistema")
@RestController
@RequestMapping("/plano-viajem")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PlanoViajemController {

    final PlanoViajemService planoViajemService;

    public PlanoViajemController(PlanoViajemService planoViajemService) {
        this.planoViajemService = planoViajemService;
    }

    @Operation(summary = "Lista todos os planos de viajem", description = "Lista todos os planos de viajem do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Planos de viajem encontrados com sucesso",
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PlanoViajemModel.class))
                    )}),
            @ApiResponse(responseCode = "204", description = "Nenhum Plano de viajem encontrado",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "No content")
                    )})
    })
    @GetMapping("/")
    public ResponseEntity<Object> get() {
        List<PlanoViajemModel> planoViajem = planoViajemService.getAll();
        if(planoViajem.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(planoViajem);
        }
    }

    @Operation(summary = "Recupera um plano de viajem por ID", description = "Recupera os dados de um plano de viajem a partir do seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plano de viajem encontrado com sucesso",
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PlanoViajemModel.class))
                    )}),
            @ApiResponse(responseCode = "404", description = "Plano de viajem não encontrado",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "No content")
                    )})
    })
    @GetMapping("/{id}")
    public ResponseEntity<PlanoViajemModel> getByid(@PathVariable Long id) {
        try {
            PlanoViajemModel planoViajem = planoViajemService.findById(id);
            return ResponseEntity.ok(planoViajem);
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Salva um plano de viajem", description = "Salva um plano de viajem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Plano de viajem salvo com sucesso",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PlanoViajemModel.class)
                    )}),
            @ApiResponse(responseCode = "409", description = "Violação de restrição de dados",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "Conflict")
                    )})
    })
    @PostMapping("/")
    public ResponseEntity<Object> save(@Valid @RequestBody PlanoViajemDto planoViajemDto){
        try{
            PlanoViajemModel model = new PlanoViajemModel();
            model.setNomeFantasia(planoViajemDto.getNomeFantasia());
            model.setPlanoViajem(planoViajemDto.getPlanoViajem());
            model.setLocalViajem(planoViajemDto.getLocalViajem());
            model.setDataInicio(planoViajemDto.getDataInicio());
            model.setDataFim(planoViajemDto.getDataFim());

            return ResponseEntity.status(HttpStatus.CREATED).body(planoViajemService.save(model));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao salvar o plano de viajem: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar o plano de viajem: " + e.getMessage());
        }
    }

    @Operation(summary = "Exclui um plano de viajem pelo ID", description = "Exclui um plano de viajem a partir do seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Plano de viajem excluido com sucesso",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PlanoViajemModel.class)
                    )}),
            @ApiResponse(responseCode = "404", description = "Plano de viajem não encontrado",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "No content")
                    )})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        try {
            planoViajemService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
