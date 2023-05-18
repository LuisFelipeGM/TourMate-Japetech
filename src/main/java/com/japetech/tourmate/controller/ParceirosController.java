package com.japetech.tourmate.controller;

import com.japetech.tourmate.dtos.ParceirosDto;
import com.japetech.tourmate.models.ParceirosModel;
import com.japetech.tourmate.services.ParceirosService;
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

@Tag(name = "Parceiros", description = "API para o gerenciamento de parceiros no sistema")
@RestController
@RequestMapping("/parceiros")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ParceirosController {

    final ParceirosService parceirosService;

    public ParceirosController(ParceirosService parceirosService) {
        this.parceirosService = parceirosService;
    }

    @Operation(summary = "Lista todos os parceiros", description = "Lista todos os parceiros do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parceiros encontrados com sucesso",
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ParceirosModel.class))
                    )}),
            @ApiResponse(responseCode = "204", description = "Nenhum Parceiro encontrado",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "No content")
                    )})
    })
    @GetMapping("/")
    public ResponseEntity<Object> get() {
        List<ParceirosModel> parceiros = parceirosService.getAll();
        if(parceiros.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(parceiros);
        }
    }


    @Operation(summary = "Recupera um parceiro por ID", description = "Recupera os dados de um parceiro a partir do seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parceiro encontrado com sucesso",
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ParceirosModel.class))
                    )}),
            @ApiResponse(responseCode = "404", description = "Parceiro não encontrado",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "No content")
                    )})
    })
    @GetMapping("/{id}")
    public ResponseEntity<ParceirosModel> getByid(@PathVariable Long id) {
        try {
            ParceirosModel parceiros = parceirosService.findById(id);
            return ResponseEntity.ok(parceiros);
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Salva um parceiro", description = "Salva um parceiro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Parceiro salvo com sucesso",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ParceirosModel.class)
                    )}),
            @ApiResponse(responseCode = "409", description = "Violação de restrição de dados",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "Conflict")
                    )})
    })
    @PostMapping("/")
    public ResponseEntity<Object> save(@Valid @RequestBody ParceirosDto parceirosDto){
        try {
            ParceirosModel model = new ParceirosModel();
            model.setNomeFantasia(parceirosDto.getNomeFantasia());
            model.setCnpj(parceirosDto.getCnpj());
            model.setTelefone(parceirosDto.getTelefone());

            return ResponseEntity.status(HttpStatus.CREATED).body(parceirosService.save(model));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao salvar o parceiro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar o parceiro: " + e.getMessage());
        }
    }

    @Operation(summary = "Exclui um parceiro pelo ID", description = "Exclui um parceiro a partir do seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Parceiro excluido com sucesso",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ParceirosModel.class)
                    )}),
            @ApiResponse(responseCode = "404", description = "Parceiro não encontrado",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "No content")
                    )})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        try {
            parceirosService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
