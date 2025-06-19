package ru.maconconsulting.librarybackend.repositories.parameters;

import org.springframework.stereotype.Repository;
import ru.maconconsulting.librarybackend.models.parameters.Segment;

@Repository
public interface SegmentsRepository extends CommonParametersRepository<Segment> {
}
