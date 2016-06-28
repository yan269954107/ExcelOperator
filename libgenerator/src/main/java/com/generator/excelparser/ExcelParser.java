package com.generator.excelparser;

import com.generator.common.Constants;
import com.generator.targetmodel.GeneratorTarget;
import com.generator.targetmodel.ParserTarget;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

/**
 * 这里用的是poi做解析,这里就跟poi耦合在一起了,
 * 不再在poi之上再封装自己的接口了,
 * 因为目前来看Java解析xlsx格式的文件也就这个库能做
 * Created by yanxinwei on 16/6/1.
 */
public class ExcelParser {

    public void loadExcel(InputStream is, ParserTarget target, ParserResult result) {
        try {
            Sheet sheet = getTargetSheet(is, target.sheetAtIndex());
            Row row;
            Cell cell;
            int rowCount = sheet.getPhysicalNumberOfRows();
            for (int r = target.startRow(); r < rowCount; r++){
                row = sheet.getRow(r);
                Class clazz = target.getClass();
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    System.out.println("@@@@ fieldName : "+field.getName());
                }
                break;
            }
        } catch (IOException e) {
            result.onError(Constants.ERROR_FILE_NOT_FIND, e);
        }
    }

    private Sheet getTargetSheet(InputStream inputStream, int index) throws IOException {
        InputStream is = inputStream;
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(index);
        return sheet;
    }

    public void loadExcel(String path, ParserResult result, ParserTarget target) {
        try {
            Sheet sheet = getTargetSheet(path, target.sheetAtIndex());
            Row row;
            Cell cell;
            int rowCount = sheet.getPhysicalNumberOfRows();
            for (int r = target.startRow(); r < rowCount; r++){
                row = sheet.getRow(r);
                Class clazz = target.getClass();

            }
        } catch (IOException e) {
            result.onError(Constants.ERROR_FILE_NOT_FIND, e);
        }
    }

    private Sheet getTargetSheet(String path, int index) throws IOException {
        InputStream is = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(index);
        return sheet;
    }

    private Row getTargetRow(String path, int sheetIndex, int targetRow) throws IOException {
        Sheet sheet = getTargetSheet(path, sheetIndex);
        return sheet.getRow(targetRow);
    }

    public String[] getTargetFieldNames(GeneratorTarget target) {
        String[] result = null;
        try {
            Row row = getTargetRow(target.getExcelPath(), target.sheetAtIndex(), target.targetRow());
            int cellCount = row.getPhysicalNumberOfCells();
            result = new String[cellCount];
            Cell cell;
            for (int i = 0; i < cellCount; i++) {
                cell = row.getCell(i);
                System.out.println(ExcelUtils.convertCellValueToString(cell));
                result[i] = ExcelUtils.convertCellValueToString(cell);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
