package ru.maconconsulting.librarybackend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class AbstractBasedEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_at")
    @NotNull
    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "AbstractBasedEntity{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                '}';
    }
}
