package com.generator.excelparser;

import org.apache.poi.ss.usermodel.Cell;

/**
 * Created by yanxinwei on 16/6/1.
 */
public interface ParserInterface {

    void onStartBefore();

    void onRowStart();

    void onCellValue(int index, Cell cell);

    void onRowEnd();

    void onComplete();

}
