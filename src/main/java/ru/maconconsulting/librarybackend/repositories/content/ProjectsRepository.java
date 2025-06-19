package ru.maconconsulting.librarybackend.repositories.content;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maconconsulting.librarybackend.models.content.Project;

import java.util.Optional;

@Repository
public interface ProjectsRepository extends JpaRepository<Project, Integer> {

    Optional<Project> findByTitle(String title);
}
