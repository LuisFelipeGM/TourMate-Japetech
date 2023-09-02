package com.japetech.tourmate.services;

import com.japetech.tourmate.interfaces.IEntityService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
public abstract class EntityService<T> implements IEntityService<T> {

    public Class<T> type;

    final JpaRepository<T,Long> repository;

    @SuppressWarnings("unchecked")
    EntityService(JpaRepository<T, Long> repository) {
        Type genericSuperclass = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type actualTypeArgument = parameterizedType.getActualTypeArguments()[0];
        type = (Class<T>) actualTypeArgument;

        log.info("Criando o service para a classe {}", type.getSimpleName());

        this.repository = Objects.requireNonNull(repository, "repository não pode ser nulo.");
    }

    @Override
    @Transactional
    public List<T> getAll() {
        log.info("Buscando todos os registros da classe {}", type.getSimpleName());
        List<T> result = repository.findAll();
        log.info("Encontrados {} registros da classe {}", result.size(), type.getSimpleName());
        return result;

    }

    @Override
    @Transactional
    public T save(T object){
        log.info("Salvando objeto da classe {}", type.getSimpleName());
        return repository.save(object);
    }

    @Transactional
    public void deleteById(@NotNull Long id) {
        log.info("Iniciando a exclusão do objeto da classe " + type + " com o ID: " + id);

        Objects.requireNonNull(id, "id não pode ser nulo.");

        try {
            repository.deleteById(id);
            log.info("Exclusão do objeto da classe " + type.getSimpleName() + " com o ID: " + id + " realizada com sucesso.");
        } catch (Exception e) {
            log.error("Erro ao excluir o objeto da classe " + type.getSimpleName() + " com o ID: " + id, e);
        }
    }

    @Transactional
    public Optional<T> findById(@NotNull Long id) {
        log.info("Iniciando a pesquisa do objeto da classe " + type.getSimpleName() + " com o ID: " + id);

        Objects.requireNonNull(id, "id não pode ser nulo.");

        try {
            Optional<T> result = repository.findById(id);
            if (result.isPresent()) {
                log.info("Objeto da classe " + type + " com o ID: " + id + " encontrado.");
            } else {
                log.info("Objeto da classe " + type + " com o ID: " + id + " não encontrado.");
            }
            return result;
        } catch (Exception e) {
            log.error("Erro ao procurar o objeto da classe " + type + " com o ID: " + id, e);
            return Optional.empty();
        }
    }

    @Transactional
    public List<T> findAll() {
        return repository.findAll();
    }

}
