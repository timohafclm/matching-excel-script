package ru.timofeev.home.matchingexcel.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SourceCells {

    private String description;
    private CellContext brand;
    private CellContext size;
    private CellContext radBiasSolid;
    private CellContext plyRating;
    private CellContext tyreType;
    private CellContext traCode;
    private CellContext pattern;
    private CellContext starRating;
    private CellContext category;
    private CellContext machine;
    private CellContext loadIndex;
    private CellContext speedIndex;
    private CellContext compound;
    private CellContext casing;
    private CellContext typeOfUse;
    private CellContext addMarks;
}
