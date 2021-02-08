package ru.timofeev.home.matchingexcel.dto;

import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum NeedColumn {

    DESCRIPTION("G31 (Описание товара)"),
    BRAND("Brand"),
    SIZE("Size"),
    RAD_BIAS_SOLID("Rad/Bias/Solid"),
    PLY_RATING("Ply rating"),
    TYRE_TYPE("Tyre type"),
    TRA_CODE("TRA CODE"),
    PATTERN("pattern"),
    STAR_RATING("star rating"),
    CATEGORY("category"),
    MACHINE("machine"),
    LOAD_INDEX("load index"),
    SPEED_INDEX("speed index"),
    COMPOUND("compound"),
    CASING("casing"),
    TYPE_OF_USE("type of use MI/BS"),
    ADD_MARKS("add. marks"),
    G_31_7("G31_7")
    ;

    private final String name;
}
