package com.japetech.tourmate.repositories;

import com.japetech.tourmate.models.ViagemParceiroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViagemParceiroRepository extends JpaRepository<ViagemParceiroModel, Long> {
}
