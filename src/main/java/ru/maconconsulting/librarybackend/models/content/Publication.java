package ru.maconconsulting.librarybackend.models.content;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import ru.maconconsulting.librarybackend.models.AbstractBasedEntity;
import ru.maconconsulting.librarybackend.models.parameters.Format;
import ru.maconconsulting.librarybackend.models.parameters.Segment;
import ru.maconconsulting.librarybackend.models.parameters.TypeOfPublication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "publication")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Publication extends AbstractBasedEntity {

    @Column(name = "title")
    @NotBlank(message = "Название не должно быть пустым!")
    private String title;

    @Column(name = "related_project_title")
    private String relatedProjectTitle;

    @Column(name = "annotation")
    private String annotation;

    @Column(name = "source")
    private String source;

    @Column(name = "year")
    private Integer year;

    @Column(name = "relevance")
    private String relevance;

    @Column(name = "path")
    private String path;

    @Column(name = "location")
    private String location;

    @ManyToMany
    @JoinTable(
            name = "publication_type",
            joinColumns = @JoinColumn(name = "publication_id"),
            inverseJoinColumns = @JoinColumn(name = "type_of_publication_id"))
    private Set<TypeOfPublication> typesOfPublication;

    @ManyToMany
    @JoinTable(
            name = "publication_segment",
            joinColumns = @JoinColumn(name = "publication_id"),
            inverseJoinColumns = @JoinColumn(name = "segment_id"))
    private Set<Segment> segments;

    @ManyToMany
    @JoinTable(
            name = "publication_format",
            joinColumns = @JoinColumn(name = "publication_id"),
            inverseJoinColumns = @JoinColumn(name = "format_id")
    )
    private Set<Format> formats;

    public List<String> getAnnotationToList() {
        return annotation != null ? Arrays.stream(annotation.split("\n")).toList() : new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Publication{" +
                "title='" + title + '\'' +
                ", relatedProjectTitle='" + relatedProjectTitle + '\'' +
                ", annotation='" + annotation + '\'' +
                ", source='" + source + '\'' +
                ", year=" + year +
                ", relevance='" + relevance + '\'' +
                ", path='" + path + '\'' +
                ", location='" + location + '\'' +
                ", typesOfPublication=" + typesOfPublication +
                ", segments=" + segments +
                ", formats=" + formats +
                '}';
    }
}
