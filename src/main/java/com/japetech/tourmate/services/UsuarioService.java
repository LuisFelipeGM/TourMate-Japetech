package com.japetech.tourmate.services;

import com.japetech.tourmate.models.UsuarioModel;
import com.japetech.tourmate.repositores.UsuarioRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService extends GenericService<UsuarioModel, Long>{

    private final UsuarioRepository usuarioRepository;

    UsuarioService(JpaRepository<UsuarioModel, Long> repository, UsuarioRepository usuarioRepository){
        super(repository);
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioModel> findByemail(String email){
        return ((UsuarioRepository) repository).findByemail(email);
    }

}
