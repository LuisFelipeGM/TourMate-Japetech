package com.japetech.tourmate.repositores;

import com.japetech.tourmate.models.ParceirosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParceirosRepository extends JpaRepository<ParceirosModel, Long> {
}
