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
public class SearchProject {

    private int year;

    private String relevance;

    private String title;

    private String client;

    private String location;

    private TypeDTO typeDTO;

    private SegmentDTO segment;

    private FormatDTO format;

    private String keyWord;

    private String methodology;
}
