package ru.maconconsulting.librarybackend.models.parameters;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;
import ru.maconconsulting.librarybackend.models.content.Project;
import ru.maconconsulting.librarybackend.models.content.Publication;

import java.util.Set;

@Entity
@Table(name = "segment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Segment extends AbstractParameterEntity {

    @ManyToMany(mappedBy = "segments")
    private Set<Project> projects;

    @ManyToMany(mappedBy = "segments")
    private Set<Publication> publications;

    @Override
    public String toString() {
        return super.toString();
    }
}
