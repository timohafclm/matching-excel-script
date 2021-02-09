package ru.timofeev.home.matchingexcel.dto;

import lombok.Builder;
import lombok.Data;
import org.apache.poi.ss.usermodel.CellType;

@Data
@Builder
public class CellContext {

    private Object value;
    private CellType type;
}
