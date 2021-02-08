package ru.timofeev.home.matchingexcel.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FillableColumns {

    private String brand;
    private String size;
    private String radBiasSolid;
    private String plyRating;
    private String tyreType;
    private String traCode;
    private String pattern;
    private String starRating;
    private String category;
    private String machine;
    private String loadIndex;
    private String speedIndex;
    private String compound;
    private String casing;
    private String typeOfUse;
    private String addMarks;
}
