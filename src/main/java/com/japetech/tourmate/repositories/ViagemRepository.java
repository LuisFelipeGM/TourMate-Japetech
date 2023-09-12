package com.japetech.tourmate.repositories;

import com.japetech.tourmate.models.ViagemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViagemRepository extends JpaRepository<ViagemModel, Long> {
}
