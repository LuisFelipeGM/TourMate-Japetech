package com.japetech.tourmate.services;

import com.japetech.tourmate.models.EnderecoModel;
import com.japetech.tourmate.repositores.EnderecoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService extends GenericService<EnderecoModel, Long> {

    private final EnderecoRepository enderecoRepository;

    EnderecoService(JpaRepository<EnderecoModel, Long> repository, EnderecoRepository enderecoRepository){
        super(repository);
        this.enderecoRepository = enderecoRepository;
    }

    public List<EnderecoModel> findByestado(String estado){
        return ((EnderecoRepository) repository).findByestadoContainingIgnoreCase(estado);
    }

    public List<EnderecoModel> findBycidade(String cidade){
        return ((EnderecoRepository) repository).findBycidadeContainingIgnoreCase(cidade);
    }

    public List<EnderecoModel> findBysiglaEstado(String siglaEstado){
        return ((EnderecoRepository) repository).findBysiglaEstado(siglaEstado);
    }

}
