package com.japetech.tourmate.services;

import com.japetech.tourmate.models.ParceirosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ParceirosService extends GenericService<ParceirosModel, Long> {

    ParceirosService(JpaRepository<ParceirosModel, Long> repository) {
        super(repository);
    }
}
