package ru.maconconsulting.librarybackend.models.content;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.maconconsulting.librarybackend.models.AbstractBasedEntity;
import ru.maconconsulting.librarybackend.models.parameters.Format;
import ru.maconconsulting.librarybackend.models.parameters.Segment;
import ru.maconconsulting.librarybackend.models.parameters.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "project")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Project extends AbstractBasedEntity {

    @Column(name = "year")
    @NotNull(message = "Год проекта не должен быть пустым!")
    private Integer year;

    @Column(name = "relevance")
    private String relevance;

    @Column(name = "title")
    @NotBlank(message = "Название проекта не должно быть пустым!")
    private String title;

    @Column(name = "client")
    private String client;

    @Column(name = "location")
    @NotBlank(message = "Локация не должна быть пустой!")
    private String location;

    @ManyToMany
    @JoinTable(
            name = "project_type",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id"))
    private Set<Type> types;

    @ManyToMany
    @JoinTable(
            name = "project_segment",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "segment_id"))
    private Set<Segment> segments;

    @ManyToMany
    @JoinTable(
            name = "project_format",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "format_id")
    )
    private Set<Format> formats;

    @Column(name = "key_words")
    private String keyWords;

    @Column(name = "methodology")
    private String methodology;

    public List<String> getKeyWordsToList() {
        return keyWords != null ? Arrays.stream(keyWords.split("\n")).toList() : new ArrayList<>();
    }

    public List<String> getMethodologyToList() {
        return methodology != null ? Arrays.stream(methodology.split("\n")).toList() : new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Project{" +
                "year=" + year +
                ", relevance='" + relevance + '\'' +
                ", title='" + title + '\'' +
                ", client='" + client + '\'' +
                ", location='" + location + '\'' +
                ", types=" + types +
                ", segments=" + segments +
                ", formats=" + formats +
                ", keyWords='" + keyWords + '\'' +
                ", methodology='" + methodology + '\'' +
                '}';
    }
}
