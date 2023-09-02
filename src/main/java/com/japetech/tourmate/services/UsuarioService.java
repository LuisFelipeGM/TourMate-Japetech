package com.japetech.tourmate.services;

import com.japetech.tourmate.dtos.UsuarioDto;
import com.japetech.tourmate.models.UsuarioModel;
import com.japetech.tourmate.repositories.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UsuarioService extends EntityService<UsuarioModel> {

    private final UsuarioRepository usuarioRepository;

    UsuarioService(JpaRepository<UsuarioModel, Long> repository, UsuarioRepository usuarioRepository) {
        super(repository);
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioModel adicionaUsuario(UsuarioDto usuarioDto){
        try {
            UsuarioModel usuario = new UsuarioModel();
            BeanUtils.copyProperties(usuarioDto, usuario);

            log.info("Cadastrando novo Usuario");

            return repository.save(usuario);
        } catch (Exception e) {
            log.error("Erro ao copiar as propriedades do DTO para o modelo de Usuario: " + e.getMessage());
            throw new RuntimeException("Erro ao salvar o novo usuario.");
        }

    }

    public UsuarioModel putUsuario(UsuarioDto usuarioDto, Long id){
        try {
            Optional<UsuarioModel> usuarioOptional = usuarioRepository.findById(id);
            if(usuarioOptional.isPresent()){
                UsuarioModel usu = usuarioOptional.get();
                BeanUtils.copyProperties(usuarioDto, usu);

                log.info("Atualizando Usuario de ID: " + id);
                return repository.save(usu);
            } else {
                throw new RuntimeException("Erro ao salvar o novo usuario.");
            }
        }catch (Exception e) {
            log.error("Erro ao copiar as propriedades do DTO para o modelo de Usuario: " + e.getMessage());
            throw new RuntimeException("Erro ao salvar o novo usuario.");
        }
    }

    public List<UsuarioModel> findByemailContainingIgnoreCase(String email){
        return ((UsuarioRepository) repository).findByemailContainingIgnoreCase(email);
    }

}
