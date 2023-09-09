package com.japetech.tourmate.repositories;

import com.japetech.tourmate.models.PreferenciaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferenciaRepository extends JpaRepository<PreferenciaModel, Long> {
}
