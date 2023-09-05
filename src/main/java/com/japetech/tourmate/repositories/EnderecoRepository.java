package com.japetech.tourmate.repositories;

import com.japetech.tourmate.models.EnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoModel, Long> {

    List<EnderecoModel> findByestadoContainingIgnoreCase(String estado);

    List<EnderecoModel> findBycidadeContainingIgnoreCase(String cidade);

    List<EnderecoModel> findBysiglaEstado(String siglaEstado);

}
