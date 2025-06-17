package ru.maconconsulting.librarybackend.models.parameters;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;
import ru.maconconsulting.librarybackend.models.content.Project;

import java.util.Set;

@Entity
@Table(name = "type")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Type extends AbstractParameterEntity {

    @ManyToMany(mappedBy = "types")
    private Set<Project> projects;

    @Override
    public String toString() {
        return super.toString();
    }
}
