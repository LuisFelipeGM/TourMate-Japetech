package com.japetech.tourmate.services;

import com.japetech.tourmate.models.PreferenciaModel;
import com.japetech.tourmate.repositores.PreferenciaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PreferenciaService extends GenericService<PreferenciaModel, Long>{

    PreferenciaService(JpaRepository<PreferenciaModel, Long> repository){
        super(repository);
    }

}
