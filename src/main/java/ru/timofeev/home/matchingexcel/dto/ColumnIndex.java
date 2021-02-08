package ru.timofeev.home.matchingexcel.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ColumnIndex {

    private int description;
    private int brand;
    private int size;
    private int radBiasSolid;
    private int plyRating;
    private int tyreType;
    private int traCode;
    private int pattern;
    private int starRating;
    private int category;
    private int machine;
    private int loadIndex;
    private int speedIndex;
    private int compound;
    private int casing;
    private int typeOfUse;
    private int addMarks;
}
