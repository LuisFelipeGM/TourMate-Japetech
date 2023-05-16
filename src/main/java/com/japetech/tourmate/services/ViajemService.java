package com.japetech.tourmate.services;

import com.japetech.tourmate.models.ViajemModel;
import com.japetech.tourmate.repositores.ViajemRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ViajemService extends GenericService<ViajemModel, Long> {
    ViajemService(JpaRepository<ViajemModel, Long> repository){
        super(repository);
    }

}
