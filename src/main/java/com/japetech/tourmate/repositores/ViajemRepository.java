package com.japetech.tourmate.repositores;

import com.japetech.tourmate.models.ViajemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViajemRepository extends JpaRepository<ViajemModel, Long> {

}
