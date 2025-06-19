package ru.maconconsulting.librarybackend.dto.content;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.maconconsulting.librarybackend.dto.parameters.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PublicationDTO {

    private Integer id;

    @NotBlank(message = "Название не должно быть пустым!")
    private String title;

    private String relatedProjectTitle;

    private String annotation;

    private String source;

    private Integer year;

    private String relevance;

    private String path;

    private String location;

    private Set<TypeOfPublicationDTO> typesOfPublication;

    private Set<SegmentDTO> segments;

    private Set<FormatDTO> formats;

    public List<String> getAnnotationToList() {
        return annotation != null ? Arrays.stream(annotation.split("\n")).toList() : new ArrayList<>();
    }
}
