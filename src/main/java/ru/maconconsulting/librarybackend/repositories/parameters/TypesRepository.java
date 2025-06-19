package ru.maconconsulting.librarybackend.repositories.parameters;

import org.springframework.stereotype.Repository;
import ru.maconconsulting.librarybackend.models.parameters.Type;

@Repository
public interface TypesRepository extends CommonParametersRepository<Type> {
}
