package com.japetech.tourmate.interfaces;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;

public interface IEntityService<T> {

    @Transactional
    List<T> getAll();

    @Transactional
    @NotNull
    T save(T object);

    @Transactional
    void deleteById(Long id);

    @Transactional
    Optional<T> findById(Long id);

}
