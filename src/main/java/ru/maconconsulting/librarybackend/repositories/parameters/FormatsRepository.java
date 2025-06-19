package ru.maconconsulting.librarybackend.repositories.parameters;

import org.springframework.stereotype.Repository;
import ru.maconconsulting.librarybackend.models.parameters.Format;

@Repository
public interface FormatsRepository extends CommonParametersRepository<Format> {
}
