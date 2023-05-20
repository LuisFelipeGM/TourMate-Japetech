package com.japetech.tourmate.controller;

import com.japetech.tourmate.dtos.PreferenciaDto;
import com.japetech.tourmate.models.PreferenciaModel;
import com.japetech.tourmate.services.PreferenciaService;
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

@Tag(name = "Preferencias", description = "API para o gerenciamento de Preferencias no sistema")
@RestController
@RequestMapping("/preferencias")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PreferenciaController {

    final PreferenciaService preferenciaService;

    public PreferenciaController(PreferenciaService preferenciaService) {
        this.preferenciaService = preferenciaService;
    }

    @Operation(summary = "Lista todos as preferencias", description = "Lista todos as preferencias do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Preferencias encontradas com sucesso",
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PreferenciaModel.class))
                    )}),
            @ApiResponse(responseCode = "204", description = "Nenhuma Preferencia encontrada",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "No content")
                    )})
    })
    @GetMapping("/")
    public ResponseEntity<Object> get() {
        List<PreferenciaModel> preferencias = preferenciaService.getAll();
        if (preferencias.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(preferencias);
        }
    }

    @Operation(summary = "Recupera uma preferencia por ID", description = "Recupera os dados de uma preferencia a partir do seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Preferencia encontrada com sucesso",
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PreferenciaModel.class))
                    )}),
            @ApiResponse(responseCode = "404", description = "Preferencia não encontrada",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "No content")
                    )})
    })
    @GetMapping("/{id}")
    public ResponseEntity<PreferenciaModel> getByid(@PathVariable Long id) {
        try {
            PreferenciaModel preferencia = preferenciaService.findById(id);
            return ResponseEntity.ok(preferencia);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Salva uma preferencia", description = "Salva uma preferencia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Preferencia salva com sucesso",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PreferenciaModel.class)
                    )}),
            @ApiResponse(responseCode = "409", description = "Violação de restrição de dados",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "Conflict")
                    )})
    })
    @PostMapping("/")
    public ResponseEntity<Object> save(@Valid @RequestBody PreferenciaDto preferenciaDto){
        try {
            PreferenciaModel model = new PreferenciaModel();
            model.setFlowUsuario(preferenciaDto.getFlowUsuario());
            model.setClimaChuvoso(preferenciaDto.getClimaChuvoso());
            model.setClimaEnsolarado(preferenciaDto.getClimaEnsolarado());
            model.setClimaNublado(preferenciaDto.getClimaNublado());
            model.setClimaNeve(preferenciaDto.getClimaNeve());
            model.setClimaFrio(preferenciaDto.getClimaFrio());
            model.setClimaQuente(preferenciaDto.getClimaQuente());
            model.setViajemLazer(preferenciaDto.getViajemLazer());
            model.setViajemNegocio(preferenciaDto.getViajemNegocio());
            model.setViajemTurismo(preferenciaDto.getViajemTurismo());
            model.setViajemRomantico(preferenciaDto.getViajemRomantico());

            return ResponseEntity.status(HttpStatus.CREATED).body(preferenciaService.save(model));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao salvar a preferencia: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar a preferencia: " + e.getMessage());
        }
    }

    @Operation(summary = "Exclui uma preferencia pelo ID", description = "Exclui uma preferencia a partir do seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Preferencia excluida com sucesso",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PreferenciaModel.class)
                    )}),
            @ApiResponse(responseCode = "404", description = "Preferencia não encontrada",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "No content")
                    )})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        try {
            preferenciaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Atualiza uma preferencia", description = "Atualiza uma preferencia existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Preferencia atualizada com sucesso",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PreferenciaModel.class)
                    )}),
            @ApiResponse(responseCode = "404", description = "Preferencia não encontrada",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "No content")
                    )}),
            @ApiResponse(responseCode = "409", description = "Violação de restrição de dados",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "Conflict")
                    )})
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody PreferenciaDto preferenciaDto) {
        try {
            PreferenciaModel existingPreferencia = preferenciaService.findById(id);
            existingPreferencia.setFlowUsuario(preferenciaDto.getFlowUsuario());
            existingPreferencia.setClimaChuvoso(preferenciaDto.getClimaChuvoso());
            existingPreferencia.setClimaEnsolarado(preferenciaDto.getClimaEnsolarado());
            existingPreferencia.setClimaNublado(preferenciaDto.getClimaNublado());
            existingPreferencia.setClimaNeve(preferenciaDto.getClimaNeve());
            existingPreferencia.setClimaFrio(preferenciaDto.getClimaFrio());
            existingPreferencia.setClimaQuente(preferenciaDto.getClimaQuente());
            existingPreferencia.setViajemLazer(preferenciaDto.getViajemLazer());
            existingPreferencia.setViajemNegocio(preferenciaDto.getViajemNegocio());
            existingPreferencia.setViajemTurismo(preferenciaDto.getViajemTurismo());
            existingPreferencia.setViajemRomantico(preferenciaDto.getViajemRomantico());

            PreferenciaModel updatedPreferencia = preferenciaService.save(existingPreferencia);
            return ResponseEntity.ok(updatedPreferencia);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao atualizar a preferencia: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar a preferencia: " + e.getMessage());
        }
    }

    @Operation(summary = "Atualiza parcialmente uma preferencia", description = "Atualiza parcialmente os dados de uma preferencia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Preferencia atualizada com sucesso",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PreferenciaModel.class)
                    )}),
            @ApiResponse(responseCode = "404", description = "Preferencia não encontrada",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "No content")
                    )}),
            @ApiResponse(responseCode = "409", description = "Violação de restrição de dados",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "Conflict")
                    )})
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Object> patch(@PathVariable Long id, @RequestBody PreferenciaDto preferenciaDto) {
        try {
            PreferenciaModel existingPreferencia = preferenciaService.findById(id);

            if (preferenciaDto.getFlowUsuario() != null) {
                existingPreferencia.setFlowUsuario(preferenciaDto.getFlowUsuario());
            }
            if (preferenciaDto.getClimaFrio() != null) {
                existingPreferencia.setClimaFrio(preferenciaDto.getClimaFrio());
            }
            if (preferenciaDto.getClimaQuente() != null) {
                existingPreferencia.setClimaQuente(preferenciaDto.getClimaQuente());
            }
            if (preferenciaDto.getViajemTurismo() != null) {
                existingPreferencia.setViajemTurismo(preferenciaDto.getViajemTurismo());
            }
            if (preferenciaDto.getViajemNegocio() != null) {
                existingPreferencia.setViajemNegocio(preferenciaDto.getViajemNegocio());
            }
            if (preferenciaDto.getViajemLazer() != null) {
                existingPreferencia.setViajemLazer(preferenciaDto.getViajemLazer());
            }
            if (preferenciaDto.getViajemRomantico() != null) {
                existingPreferencia.setViajemRomantico(preferenciaDto.getViajemRomantico());
            }
            if (preferenciaDto.getClimaChuvoso() != null) {
                existingPreferencia.setClimaChuvoso(preferenciaDto.getClimaChuvoso());
            }
            if (preferenciaDto.getClimaEnsolarado() != null) {
                existingPreferencia.setClimaEnsolarado(preferenciaDto.getClimaEnsolarado());
            }
            if (preferenciaDto.getClimaNublado() != null) {
                existingPreferencia.setClimaNublado(preferenciaDto.getClimaNublado());
            }
            if (preferenciaDto.getClimaNeve() != null) {
                existingPreferencia.setClimaNeve(preferenciaDto.getClimaNeve());
            }

            PreferenciaModel updatedPreferencia = preferenciaService.save(existingPreferencia);
            return ResponseEntity.ok(updatedPreferencia);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao atualizar a preferencia: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar a preferencia: " + e.getMessage());
        }
    }
}
