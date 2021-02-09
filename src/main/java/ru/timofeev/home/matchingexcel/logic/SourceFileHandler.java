package ru.timofeev.home.matchingexcel.logic;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.timofeev.home.matchingexcel.dto.CellContext;
import ru.timofeev.home.matchingexcel.dto.NeedColumn;
import ru.timofeev.home.matchingexcel.dto.SourceCells;
import ru.timofeev.home.matchingexcel.helper.DescriptionHelper;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import static ru.timofeev.home.matchingexcel.dto.NeedColumn.*;

public class SourceFileHandler {

    private final String sourceFileName;
    private final String sourceSheet;

    public SourceFileHandler(String sourceFileName, String sourceSheet) {
        this.sourceFileName = sourceFileName;
        this.sourceSheet = sourceSheet;
    }

    public Map<String, SourceCells> activate() throws Exception {
        try (var fis = new FileInputStream(sourceFileName);
             var workbook = new XSSFWorkbook(fis)) {
            return handleSourceFile(workbook);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private Map<String, SourceCells> handleSourceFile(Workbook workbook) {
        var sheet = workbook.getSheet(sourceSheet);
        return handle(sheet);
    }

    private Map<String, SourceCells> handle(Sheet sheet) {
        Map<NeedColumn, Integer> columnToIndex = new HashMap<>();
        Map<String, SourceCells> resultMap = new HashMap<>();
        int count = 0;
        for (var row : sheet) {
            if (count == 0) {
                columnToIndex = fillColumnIndex(row);
            } else {
                var entry = handleRow(row, columnToIndex);
                resultMap.put(entry.getKey(), entry.getValue());
            }
            count++;
        }
        return resultMap;
    }

    private Map.Entry<String, SourceCells> handleRow(Row row, Map<NeedColumn, Integer> columnToIndex) {
        var description = DescriptionHelper.subStringDescription(row, columnToIndex);
        return buildColumns(row, columnToIndex, description);
    }

    private Map.Entry<String, SourceCells> buildColumns(Row row, Map<NeedColumn, Integer> columnToIndex, String description) {
        var columns = SourceCells.builder()
                .description(description)
                .brand(buildCellContext(row.getCell(columnToIndex.get(BRAND))))
                .size(buildCellContext(row.getCell(columnToIndex.get(SIZE))))
                .radBiasSolid(buildCellContext(row.getCell(columnToIndex.get(RAD_BIAS_SOLID))))
                .plyRating(buildCellContext(row.getCell(columnToIndex.get(PLY_RATING))))
                .tyreType(buildCellContext(row.getCell(columnToIndex.get(TYRE_TYPE))))
                .traCode(buildCellContext(row.getCell(columnToIndex.get(TRA_CODE))))
                .pattern(buildCellContext(row.getCell(columnToIndex.get(PATTERN))))
                .starRating(buildCellContext(row.getCell(columnToIndex.get(STAR_RATING))))
                .category(buildCellContext(row.getCell(columnToIndex.get(CATEGORY))))
                .machine(buildCellContext(row.getCell(columnToIndex.get(MACHINE))))
                .loadIndex(buildCellContext(row.getCell(columnToIndex.get(LOAD_INDEX))))
                .speedIndex(buildCellContext(row.getCell(columnToIndex.get(SPEED_INDEX))))
                .compound(buildCellContext(row.getCell(columnToIndex.get(COMPOUND))))
                .casing(buildCellContext(row.getCell(columnToIndex.get(CASING))))
                .typeOfUse(buildCellContext(row.getCell(columnToIndex.get(TYPE_OF_USE))))
                .addMarks(buildCellContext(row.getCell(columnToIndex.get(ADD_MARKS))))
                .build();
        return Map.entry(description, columns);
    }

    private CellContext buildCellContext(Cell cell) {
        if (cell == null) {
            return null;
        }
        if (cell.getCellType() == CellType.STRING) {
            return CellContext.builder().value(cell.getStringCellValue()).type(CellType.STRING).build();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return CellContext.builder().value(cell.getNumericCellValue()).type(CellType.NUMERIC).build();
        } else if (cell.getCellType() == CellType.BOOLEAN) {
            return CellContext.builder().value(cell.getBooleanCellValue()).type(CellType.BOOLEAN).build();
        }
        return CellContext.builder().value(null).type(CellType._NONE).build();
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
