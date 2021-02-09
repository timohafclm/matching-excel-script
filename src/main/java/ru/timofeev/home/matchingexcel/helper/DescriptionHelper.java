package ru.timofeev.home.matchingexcel.helper;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import ru.timofeev.home.matchingexcel.dto.NeedColumn;

import java.util.Map;
import java.util.Optional;

import static ru.timofeev.home.matchingexcel.dto.NeedColumn.DESCRIPTION;
import static ru.timofeev.home.matchingexcel.dto.NeedColumn.G_31_7;

public class DescriptionHelper {

    public static String subStringDescription(Row row, Map<NeedColumn, Integer> columnToIndex) {
        var description = row.getCell(columnToIndex.get(DESCRIPTION)).getStringCellValue().trim();
        var quantity = extractQuantity(row, columnToIndex);
        if (description.endsWith(" 0")) {
            return substringWithZero(description);
        } else {
            return subStringWithQuantity(description, quantity);
        }
    }

    private static Optional<String> extractQuantity(Row row, Map<NeedColumn, Integer> columnToIndex) {
        var cell = row.getCell(columnToIndex.get(G_31_7));
        if (cell == null || cell.getCellType() != CellType.NUMERIC) {
            return Optional.empty();
        } else {
            return Optional.of(String.valueOf(Double.valueOf(cell.getNumericCellValue()).intValue()));
        }
    }

    static String substringWithZero(String description) {
        var index = description.length() - 2;
        return description.substring(0, index).trim();
    }

    static String subStringWithQuantity(String description, Optional<String> quantity) {
        if (quantity.isEmpty()) {
            return description;
        }
        if (description.endsWith(" " + quantity.get())) {
            var index = description.length() - quantity.get().length();
            return description.substring(0, index).trim();
        } else {
            return description;
        }
    }
}
