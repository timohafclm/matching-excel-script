package ru.timofeev.home.matchingexcel;

import ru.timofeev.home.matchingexcel.logic.MatchingOperation;

public class Application {

    public static void main(String[] args) {
        validateArgs(args);
        var sourceFile = args[0];
        var sourceSheet = args[1];
        var handledFile = args[2];
        var handledSheet = args[3];
        var matchingOperation = new MatchingOperation(sourceFile, sourceSheet, handledFile, handledSheet);
        matchingOperation.activate();
    }

    private static void validateArgs(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("args must be contain 4 values");
        }
    }
}
