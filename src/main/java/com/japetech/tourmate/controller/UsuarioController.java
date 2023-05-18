package com.japetech.tourmate.controller;

import com.japetech.tourmate.dtos.UsuarioDto;
import com.japetech.tourmate.models.UsuarioModel;
import com.japetech.tourmate.services.UsuarioService;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Tag(name = "Usuario", description = "API para o gerenciamento de usuarios no sistema")
@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UsuarioController {

    final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Lista todos os usuarios", description = "Lista todos os usuarios do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuarios encontrados com sucesso",
                    content = {@Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = UsuarioModel.class))
            )}),
            @ApiResponse(responseCode = "204", description = "Nenhum Usuario encontrado",
            content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "No content")
            )})
    })
    @GetMapping("/")
    public ResponseEntity<Object> get() {
        List<UsuarioModel> usuarios = usuarioService.getAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(usuarios);
        }
    }



    @Operation(summary = "Recupera um usuario por ID", description = "Recupera os dados de um usuario a partir do seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado com sucesso",
            content = {@Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = UsuarioModel.class))
            )}),
            @ApiResponse(responseCode = "404", description = "Usuario não encontrado",
            content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "No content")
            )})
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioModel> getByid(@PathVariable Long id) {
        try {
            UsuarioModel usuario = usuarioService.findById(id);
            return ResponseEntity.ok(usuario);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }




    @Operation(summary = "Recupera um usuario pelo email", description = "Recupera os dados de um usuario a partir do seu email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado com sucesso",
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UsuarioModel.class))
                    )}),
            @ApiResponse(responseCode = "404", description = "Usuario não encontrado",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "No content")
                    )})
    })
    @GetMapping("/email/{email}")
    public ResponseEntity<List<UsuarioModel>> getByEmail(@PathVariable String email) {
        List<UsuarioModel> usuarios = usuarioService.findByemail(email);
        if (usuarios.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(usuarios);
        }
    }



    @Operation(summary = "Salva um usuario", description = "Salva um usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario salvo com sucesso",
                    content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UsuarioModel.class)
            )}),
            @ApiResponse(responseCode = "409", description = "Violação de restrição de dados",
                    content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "Conflict")
            )})
    })
    @PostMapping("/")
    public ResponseEntity<Object> save(@Valid @RequestBody UsuarioDto usuarioDto) {
        try {
            UsuarioModel model = new UsuarioModel();
            model.setNome(usuarioDto.getNome());
            model.setEmail(usuarioDto.getEmail());
            model.setSenha(usuarioDto.getSenha());
            model.setCpf(usuarioDto.getCpf());
            model.setDataNascimento(usuarioDto.getDataNascimento());
            model.setTipoUsuario(usuarioDto.getTipoUsuario());
            model.setSexo(usuarioDto.getSexo());

            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(model));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao salvar o usuário: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar o usuário: " + e.getMessage());
        }
    }


    @Operation(summary = "Exclui um usuario pelo ID", description = "Exclui um usuario a partir do seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuario excluido com sucesso",
                    content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UsuarioModel.class)
            )}),
            @ApiResponse(responseCode = "404", description = "Usuario não encontrado",
                    content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "No content")
            )})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            usuarioService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
