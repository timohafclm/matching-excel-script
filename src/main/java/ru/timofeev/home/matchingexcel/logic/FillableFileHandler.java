package ru.timofeev.home.matchingexcel.logic;

import ru.timofeev.home.matchingexcel.dto.SourceCells;

import java.util.Map;

public class FillableFileHandler {

    private final String handledFileName;
    private final String handledSheet;
    private final Map<String, SourceCells> descriptionToColumn;

    public FillableFileHandler(String handledFileName, String handledSheet, Map<String, SourceCells> descriptionToColumn) {
        this.handledFileName = handledFileName;
        this.handledSheet = handledSheet;
        this.descriptionToColumn = descriptionToColumn;
    }

    public void activate() {

    }
}
