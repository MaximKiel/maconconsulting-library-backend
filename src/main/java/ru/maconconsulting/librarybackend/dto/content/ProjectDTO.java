package ru.maconconsulting.librarybackend.dto.content;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class ProjectDTO {

    private Integer id;

    @NotNull(message = "Год проекта не должен быть пустым!")
    private Integer year;

    private String relevance;

    @NotBlank(message = "Название проекта не должно быть пустым!")
    private String title;

    private String client;

    @NotBlank(message = "Локация не должна быть пустой!")
    private String location;

    private Set<TypeDTO> types;

    private Set<SegmentDTO> segments;

    private Set<FormatDTO> formats;

    private String keyWords;

    private String methodology;

    public List<String> getKeyWordsToList() {
        return keyWords != null ? Arrays.stream(keyWords.split("\n")).toList() : new ArrayList<>();
    }

    public List<String> getMethodologyToList() {
        return methodology != null ? Arrays.stream(methodology.split("\n")).toList() : new ArrayList<>();
    }
}
