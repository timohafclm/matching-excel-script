package ru.timofeev.home.matchingexcel.logic;

import lombok.Data;
import org.apache.poi.ss.usermodel.Workbook;

public class MatchEngine {

    private TargetSheet source;
    private TargetSheet handled;

    public static MatchEngine of(Workbook sourceBook, String sourceSheet, Workbook handledBook, String handledSheet) {
        var engine = new MatchEngine();
        var source = new TargetSheet(sourceBook, sourceSheet);
        var handled = new TargetSheet(handledBook, handledSheet);
        engine.source = source;
        engine.handled = handled;
        return engine;
    }

    public void run() {
        var sourceSheet = source.getWorkbook().getSheet(source.getSheet());
    }

    private MatchEngine() {
    }

    @Data
    private static class TargetSheet {

        private final Workbook workbook;
        private final String sheet;
    }
}
