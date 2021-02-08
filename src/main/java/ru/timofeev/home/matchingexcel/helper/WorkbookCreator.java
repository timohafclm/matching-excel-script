package ru.timofeev.home.matchingexcel.helper;

import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WorkbookCreator {

    @SneakyThrows
    public static Workbook formWorkbook(String filename) {
        var file = formFile(filename);
        return new XSSFWorkbook(file);
    }

    private static FileInputStream formFile(String filename) throws IOException {
        try {
            return new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
