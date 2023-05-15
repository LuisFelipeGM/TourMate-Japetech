package com.japetech.tourmate.interfaces;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface IMyService<T, Id> {

    @Transactional
    List<T> getAll();

    @Transactional
    @NotNull
    T save(T object);

    @Transactional
    void deleteById(Id id);

    @Transactional
    T findById(Id id);

}
