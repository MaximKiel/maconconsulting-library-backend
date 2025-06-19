package ru.maconconsulting.librarybackend.repositories.content;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maconconsulting.librarybackend.models.content.Publication;

import java.util.Optional;

@Repository
public interface PublicationsRepository extends JpaRepository<Publication, Integer> {

    Optional<Publication> findByTitle(String title);
}
