package com.japetech.tourmate.repositories;

import com.japetech.tourmate.models.ParceiroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParceiroRepository extends JpaRepository<ParceiroModel, Long> {
}
