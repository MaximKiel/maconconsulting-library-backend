package ru.maconconsulting.librarybackend.services.parameters;

import ru.maconconsulting.librarybackend.models.parameters.AbstractParameterEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CommonParametersService<E extends AbstractParameterEntity> {

    List<E> findAll();

    Optional<E> findByName(String name);

    void save(E entity);

    void update(String name, E updatedEntity);

    void delete(String name);

    default void enrichParameterFieldEntity(AbstractParameterEntity entity) {
        entity.setCreatedAt(LocalDateTime.now());
    }
}
