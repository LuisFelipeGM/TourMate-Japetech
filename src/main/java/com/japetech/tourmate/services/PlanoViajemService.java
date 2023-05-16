package com.japetech.tourmate.services;

import com.japetech.tourmate.models.PlanoViajemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PlanoViajemService extends GenericService<PlanoViajemModel, Long>{

    PlanoViajemService(JpaRepository<PlanoViajemModel, Long> repository) {
        super(repository);
    }

}
