package ru.maconconsulting.librarybackend.utils.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.maconconsulting.librarybackend.dto.parameters.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SearchPublication {

    private String generalSearch;

    private String title;

    private String relatedProjectTitle;

    private String annotation;

    private String source;

    private Integer year;

    private String relevance;

    private String location;

    private TypeOfPublicationDTO typeOfPublication;

    private SegmentDTO segment;

    private FormatDTO format;
}