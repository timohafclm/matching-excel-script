package ru.timofeev.home.matchingexcel.logic;

import lombok.SneakyThrows;

public class MatchingOperation {

    private final String sourceFileName;
    private final String sourceSheet;
    private final String handledFileName;
    private final String handledSheet;

    public MatchingOperation(String sourceFileName, String sourceSheet, String handledFileName, String handledSheet) {
        this.sourceFileName = sourceFileName;
        this.sourceSheet = sourceSheet;
        this.handledFileName = handledFileName;
        this.handledSheet = handledSheet;
    }

    @SneakyThrows
    public void activate() {
        var sourceHandler = new SourceFileHandler(sourceFileName, sourceSheet);
        var descriptionToColumn = sourceHandler.activate();
        var fillableFileHandler = new FillableFileHandler(handledFileName, handledSheet, descriptionToColumn);
        fillableFileHandler.activate();
    }
}
