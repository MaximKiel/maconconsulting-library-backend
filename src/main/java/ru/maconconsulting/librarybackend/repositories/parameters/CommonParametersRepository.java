package ru.maconconsulting.librarybackend.repositories.parameters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.maconconsulting.librarybackend.models.parameters.AbstractParameterEntity;

import java.util.Optional;

@NoRepositoryBean
public interface CommonParametersRepository<E extends AbstractParameterEntity> extends JpaRepository<E, Integer> {

    Optional<E> findByName(String name);

    void deleteByName(String name);
}
