package com.japetech.tourmate.repositores;

import com.japetech.tourmate.models.PlanoViajemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanoViajemRepository extends JpaRepository<PlanoViajemModel, Long> {

}
