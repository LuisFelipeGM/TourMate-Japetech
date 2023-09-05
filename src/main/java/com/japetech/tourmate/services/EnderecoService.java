package com.japetech.tourmate.services;

import com.japetech.tourmate.dtos.EnderecoDto;
import com.japetech.tourmate.models.EnderecoModel;
import com.japetech.tourmate.models.UsuarioModel;
import com.japetech.tourmate.repositories.EnderecoRepository;
import com.japetech.tourmate.repositories.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EnderecoService extends EntityService<EnderecoModel>{

    private final EnderecoRepository enderecoRepository;
    private final UsuarioRepository usuarioRepository;


    EnderecoService(JpaRepository<EnderecoModel, Long> repository, EnderecoRepository enderecoRepository, UsuarioRepository usuarioRepository) {
        super(repository);
        this.enderecoRepository = enderecoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public EnderecoModel adicionarEndereco(EnderecoDto enderecoDto) {
        try {
            Optional<UsuarioModel> usuarioOptional = usuarioRepository.findById(enderecoDto.getIdUsuario());
            if(usuarioOptional.isPresent()){
                UsuarioModel usu = usuarioOptional.get();
                EnderecoModel end = new EnderecoModel();
                BeanUtils.copyProperties(enderecoDto, end);
                end.setUsuario(usu);
                return repository.save(end);
            } else {
                throw new Exception("Usuário não encontrado");
            }
        } catch (Exception e){
            log.error("Erro ao copiar as propriedades do DTO para o modelo de Endereco: " + e.getMessage());
            throw new RuntimeException("Erro ao salvar o novo endereco.");
        }
    }

    public EnderecoModel putEndereco(EnderecoDto enderecoDto, Long id){
        try {
            Optional<EnderecoModel> enderoOptional = enderecoRepository.findById(id);
            if (enderoOptional.isPresent()){
                EnderecoModel end = enderoOptional.get();
                BeanUtils.copyProperties(enderecoDto, end);

                log.info("Atualizando endereco de ID: " + id);
                return repository.save(end);
            } else {
                throw new RuntimeException("Erro ao atualizar o endereco.");
            }
        } catch (Exception e) {
            log.error("Erro ao copiar as propriedades do DTO para o modelo de Endereco: " + e.getMessage());
            throw new RuntimeException("Erro ao atualizar o endereco.");
        }
    }

    public List<EnderecoModel> findByestadoContainingIgnoreCase(String estado){
        return ((EnderecoRepository) repository).findByestadoContainingIgnoreCase(estado);
    }

    public List<EnderecoModel> findBycidadeContainingIgnoreCase(String cidade){
        return ((EnderecoRepository) repository).findBycidadeContainingIgnoreCase(cidade);
    }

    public List<EnderecoModel> findBysiglaEstado(String siglaEstado){
        return ((EnderecoRepository) repository).findBysiglaEstado(siglaEstado);
    }

}
