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

public class FillableFileHandler {

    private final String fillableFileName;
    private final String fillableSheet;
    private final Map<String, SourceCells> descriptionToColumn;

    public FillableFileHandler(String fillableFileName, String fillableSheet, Map<String, SourceCells> descriptionToColumn) {
        this.fillableFileName = fillableFileName;
        this.fillableSheet = fillableSheet;
        this.descriptionToColumn = descriptionToColumn;
    }

    public void activate() throws Exception {
        try (var fis = new FileInputStream(fillableFileName);
             var workbook = new XSSFWorkbook(fis)) {
            handleWorkbook(workbook);
        }
    }

    private void handleWorkbook(Workbook workbook) {
        var sheet = workbook.getSheet(fillableSheet);
        handleSheet(sheet);
    }

    private void handleSheet(Sheet sheet) {
        int startHeaderCell = 0;
        Map<NeedColumn, Integer> columnToIndex = new HashMap<>();
        for (var row : sheet) {
            if (row.getRowNum() == 0) {
                columnToIndex = fillColumnIndex(row);
                startHeaderCell = row.getLastCellNum() + 1;
                fillHeaderRow(row, startHeaderCell);
            } else {
                fillValueRow(row, startHeaderCell, columnToIndex);
            }
        }
    }

    private Map<NeedColumn, Integer> fillColumnIndex(Row row) {
        Map<NeedColumn, Integer> columnToIndex = new HashMap<>();
        for (var cell : row) {
            var value = cell.getStringCellValue();
            var index = cell.getColumnIndex();
            if (value.equals("G31_1")) {
                columnToIndex.put(DESCRIPTION, index);
            } else if (value.contains(G_31_7.getName())) {
                columnToIndex.put(G_31_7, index);
            }
        }
        return columnToIndex;
    }

    private void fillHeaderRow(Row row, int startHeaderCell) {
        int i = startHeaderCell;
        createHeader(row, BRAND.getName(), i);
        createHeader(row, SIZE.getName(), ++i);
        createHeader(row, RAD_BIAS_SOLID.getName(),++i);
        createHeader(row, PLY_RATING.getName(),++i);
        createHeader(row, TYRE_TYPE.getName(), ++i);
        createHeader(row, TRA_CODE.getName(), ++i);
        createHeader(row, PATTERN.getName(), ++i);
        createHeader(row, STAR_RATING.getName(), ++i);
        createHeader(row, CATEGORY.getName(), ++i);
        createHeader(row, MACHINE.getName(), ++i);
        createHeader(row, LOAD_INDEX.getName(), ++i);
        createHeader(row, SPEED_INDEX.getName(), ++i);
        createHeader(row, COMPOUND.getName(), ++i);
        createHeader(row, CASING.getName(), ++i);
        createHeader(row, TYPE_OF_USE.getName(), ++i);
        createHeader(row, ADD_MARKS.getName(), ++i);
    }

    private void createHeader(Row row, String value, int column) {
        var cell = row.createCell(column, CellType.STRING);
        cell.setCellValue(value);
    }

    private void fillValueRow(Row row, int startHeaderCell, Map<NeedColumn, Integer> columnToIndex) {
        var description = DescriptionHelper.subStringDescription(row, columnToIndex);
        var sourceCells = descriptionToColumn.get(description);
        if (sourceCells == null) {
            fillNA(row, startHeaderCell);
        } else {
            fill(row, sourceCells, startHeaderCell);
        }
    }

    private void fillNA(Row row, int startHeaderCell) {
        var cell = row.createCell(startHeaderCell, CellType.STRING);
        cell.setCellValue("N/A");
    }

    private void fill(Row row, SourceCells sourceCells, int startHeaderCell) {
        var i = startHeaderCell;
        createValueCell(row, sourceCells.getBrand(), i++);
        createValueCell(row, sourceCells.getSize(), i++);
        createValueCell(row, sourceCells.getRadBiasSolid(), i++);
        createValueCell(row, sourceCells.getPlyRating(), i++);
        createValueCell(row, sourceCells.getTyreType(), i++);
        createValueCell(row, sourceCells.getTraCode(), i++);
        createValueCell(row, sourceCells.getPattern(), i++);
        createValueCell(row, sourceCells.getStarRating(), i++);
        createValueCell(row, sourceCells.getCategory(), i++);
        createValueCell(row, sourceCells.getMachine(), i++);
        createValueCell(row, sourceCells.getLoadIndex(), i++);
        createValueCell(row, sourceCells.getSpeedIndex(), i++);
        createValueCell(row, sourceCells.getCompound(), i++);
        createValueCell(row, sourceCells.getCasing(), i++);
        createValueCell(row, sourceCells.getTypeOfUse(), i++);
        createValueCell(row, sourceCells.getAddMarks(), i);
    }

    private void createValueCell(Row row, CellContext cellContext, int i) {
        var cell = row.createCell(i, cellContext.getType());
        if (cellContext.getType() == CellType.STRING) {
            cell.setCellValue((String) cellContext.getValue());
        } else if (cellContext.getType() == CellType.NUMERIC) {
            cell.setCellValue((Double) cellContext.getValue());
        } else if (cellContext.getType() == CellType.BOOLEAN) {
            cell.setCellValue((Boolean) cellContext.getValue());
        }
    }
}
