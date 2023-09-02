package com.japetech.tourmate.controller;

import com.japetech.tourmate.dtos.UsuarioDto;
import com.japetech.tourmate.models.UsuarioModel;
import com.japetech.tourmate.services.UsuarioService;
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

import java.util.List;
import java.util.Optional;

@Tag(name = "Usuario", description = "API para o gerenciamento de usuarios no sistema")
@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UsuarioController extends GenericController{

    final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Lista todos os usuarios", description = "Lista todos os usuarios do sistema")
    @ApiResponse(responseCode = "200", description = "Usuarios encontrados com sucesso",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UsuarioModel.class))))
    @GetMapping("/")
    public ResponseEntity<Object> get(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.getAll());
    }

    @Operation(summary = "Recupera um usuario por ID", description = "Recupera os dados de um usuario a partir do seu ID")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioModel.class)))
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioModel> getById(@PathVariable Long id) {
        Optional<UsuarioModel> optionalUsuario = usuarioService.findById(id);
        return optionalUsuario.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Recupera uma lista de usuarios pelo Email", description = "Recupera os dados de um usuario a partir do seu Email")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioModel.class)))
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    @GetMapping("/email/{email}")
    public ResponseEntity<List<UsuarioModel>> getByEmail(@PathVariable String email) {
        Optional<List<UsuarioModel>> optionalUsuario = Optional.ofNullable(usuarioService.findByemailContainingIgnoreCase(email));
        return optionalUsuario.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Salva o usuario", description = "Salva o usuario")
    @ApiResponse(responseCode = "201", description = "Usuario salvo com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioModel.class)))
    @PostMapping("/")
    public ResponseEntity<Object> save(@Valid @RequestBody UsuarioDto usuarioDto, BindingResult result){
        try {
            return result.hasErrors() ? ResponseEntity.unprocessableEntity().body(getErrors(result))
                    : ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.adicionaUsuario(usuarioDto));

        } catch (Exception e) {
            return handleErrors(e);
        }
    }

    @Operation(summary = "Exclui um usuario pelo Id" , description = "Exclui um usuario a partir do seu ID")
    @ApiResponse(responseCode = "204", description = "Usuario excluido com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        Optional<UsuarioModel> optionalUsuario = usuarioService.findById(id);
        return optionalUsuario
                .map(usuario -> {
                    try {
                        usuarioService.deleteById(id);
                        return ResponseEntity.noContent().build();
                    } catch (Exception e) {
                        return handleErrors(e);
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Altera um usuario pelo Id" , description = "Altera um usuario a partir do seu ID")
    @ApiResponse(responseCode = "200", description = "Usuario alterado com sucesso")
    @PutMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable Long id, @Valid @RequestBody UsuarioDto usuarioDto, BindingResult result){
        Optional<UsuarioModel> optionalUsuario = usuarioService.findById(id);
        if (optionalUsuario.isEmpty())
                return ResponseEntity.notFound().build();
        try {
            return result.hasErrors() ? ResponseEntity.unprocessableEntity().body(getErrors(result))
                    : ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.putUsuario(usuarioDto, id));
        } catch (Exception e) {
            return handleErrors(e);
        }
    }


}
