package ru.timofeev.home.matchingexcel.logic;

import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import ru.timofeev.home.matchingexcel.dto.FillableColumns;
import ru.timofeev.home.matchingexcel.dto.NeedColumn;
import ru.timofeev.home.matchingexcel.helper.WorkbookCreator;

import java.util.HashMap;
import java.util.Map;

import static ru.timofeev.home.matchingexcel.dto.NeedColumn.*;

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
        formingDictionaryFromSourceFile();
    }

    private Map<String, FillableColumns> formingDictionaryFromSourceFile() throws Exception {
        try (var workbook = WorkbookCreator.formWorkbook(sourceFileName)) {
            return handleSourceFile(workbook);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private Map<String, FillableColumns> handleSourceFile(Workbook workbook) {
        var sheet = workbook.getSheet(sourceSheet);
        handle(sheet);
        return null;
    }

    private void handle(Sheet sheet) {
        Map<NeedColumn, Integer> columnToIndex = new HashMap<>();
        int count = 0;
        for (var row : sheet) {
            if (count == 0) {
                columnToIndex = fillColumnIndex(row);
            } else {
                handleRow(row, columnToIndex);
            }
            count++;
        }
    }

    private void handleRow(Row row, Map<NeedColumn, Integer> columnToIndex) {
        var subStringDescription = subStringDescription(row, columnToIndex);
    }

    private String subStringDescription(Row row, Map<NeedColumn, Integer> columnToIndex) {
        var description = row.getCell(columnToIndex.get(DESCRIPTION)).getStringCellValue();
        var quantity = String.valueOf(Double.valueOf(row.getCell(columnToIndex.get(G_31_7)).getNumericCellValue()).intValue());
        int length = description.length() - 4;
        int endIndex = description.indexOf(quantity, length);
        return description.substring(0, endIndex);
    }

    private Map<NeedColumn, Integer> fillColumnIndex(Row row) {
        var map = new HashMap<NeedColumn, Integer>();
        for (var cell : row) {
            var value = cell.getStringCellValue();
            var index = cell.getColumnIndex();
            if (value.contains(DESCRIPTION.getName())) {
                map.put(DESCRIPTION, index);
            }
            if (value.contains(BRAND.getName())) {
                map.put(BRAND, index);
            }
            if (value.contains(SIZE.getName())) {
                map.put(SIZE, index);
            }
            if (value.contains(RAD_BIAS_SOLID.getName())) {
                map.put(RAD_BIAS_SOLID, index);
            }
            if (value.contains(PLY_RATING.getName())) {
                map.put(PLY_RATING, index);
            }
            if (value.contains(TYRE_TYPE.getName())) {
                map.put(TYRE_TYPE, index);
            }
            if (value.contains(TRA_CODE.getName())) {
                map.put(TRA_CODE, index);
            }
            if (value.contains(PATTERN.getName())) {
                map.put(PATTERN, index);
            }
            if (value.contains(STAR_RATING.getName())) {
                map.put(STAR_RATING, index);
            }
            if (value.contains(CATEGORY.getName())) {
                map.put(CATEGORY, index);
            }
            if (value.contains(MACHINE.getName())) {
                map.put(MACHINE, index);
            }
            if (value.contains(LOAD_INDEX.getName())) {
                map.put(LOAD_INDEX, index);
            }
            if (value.contains(SPEED_INDEX.getName())) {
                map.put(SPEED_INDEX, index);
            }
            if (value.contains(COMPOUND.getName())) {
                map.put(COMPOUND, index);
            }
            if (value.contains(CASING.getName())) {
                map.put(CASING, index);
            }
            if (value.contains(TYPE_OF_USE.getName())) {
                map.put(TYPE_OF_USE, index);
            }
            if (value.contains(ADD_MARKS.getName())) {
                map.put(ADD_MARKS, index);
            }
            if (value.contains(G_31_7.getName())) {
                map.put(G_31_7, index);
            }
        }
        return map;
    }
}
