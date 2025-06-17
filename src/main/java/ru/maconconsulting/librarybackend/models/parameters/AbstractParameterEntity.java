package ru.maconconsulting.librarybackend.models.parameters;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import ru.maconconsulting.librarybackend.models.AbstractBasedEntity;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class AbstractParameterEntity extends AbstractBasedEntity {

    @Column(name = "name")
    @NotBlank
    private String name;

    @Override
    public String toString() {
        return "AbstractParameterEntity{" +
                "name='" + name + '\'' +
                '}';
    }
}
