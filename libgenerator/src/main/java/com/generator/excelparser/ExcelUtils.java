package com.generator.excelparser;

import android.text.TextUtils;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yanxinwei on 16/6/22.
 */
public class ExcelUtils {

    public static final SimpleDateFormat date1 = new SimpleDateFormat("yyyy/M/d h:mm");
    public static final SimpleDateFormat date2 = new SimpleDateFormat("yyyy/M/d");

    public static Object convertCellValue(Cell cell) {
        try {
            if (null == cell) {
                return "";
            } else {
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_BLANK:
                        return "";
                    case Cell.CELL_TYPE_BOOLEAN:
                        return cell.getBooleanCellValue();
                    case Cell.CELL_TYPE_ERROR:
                        return "";
                    case Cell.CELL_TYPE_NUMERIC:
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {
                            if (cell.getCellStyle().getDataFormat() == 14) {
                                return date2.format(DateUtil.getJavaDate(cell.getNumericCellValue()));
                            } else if (cell.getCellStyle().getDataFormat() == 22) {
                                return date1.format(DateUtil.getJavaDate(cell.getNumericCellValue()));
                            } else {
                                return date1.format(DateUtil.getJavaDate(cell.getNumericCellValue()));
                            }
                        }
                        // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                        else if (cell.getCellStyle().getDataFormat() == 58) {
                            return date1.format(DateUtil.getJavaDate(cell.getNumericCellValue()));
                        } else {
                            return cell.getNumericCellValue();
                        }

                    case Cell.CELL_TYPE_STRING:
                        return cell.getStringCellValue();
                    default:
                        return "";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String convertCellValueToString(Cell cell) {
        Object o = convertCellValue(cell);
        return String.valueOf(o);
    }

    public static int convertCellValueToInt(Cell cell) {
        String value = convertCellValueToString(cell);
        if (TextUtils.isEmpty(value)) {
            return 0;
        } else {
            return (int) Double.valueOf(convertCellValueToString(cell)).doubleValue();
        }
    }

    public static Double convertCellValueToDouble(Cell cell) {
        String value = convertCellValueToString(cell);
        if (TextUtils.isEmpty(value)) {
            return 0.0;
        } else {
            return Double.valueOf(convertCellValueToString(cell));
        }
    }

    public static void convertCellValue(Cell cell, Class clazz, Method method, Object obj) throws InvocationTargetException, IllegalAccessException {
        if (clazz == String.class) {
            method.invoke(obj, convertCellValueToString(cell));
        } else if (clazz == int.class || clazz == Integer.class) {
            method.invoke(obj, convertCellValueToInt(cell));
        } else if (clazz == double.class || clazz == Double.class) {
            method.invoke(obj, convertCellValueToDouble(cell));
        }
    }

    public static Cell getCell(Row row, int cellNumber) {
        Cell cell = row.getCell(cellNumber);
        if (null == cell) {
            cell = row.createCell(cellNumber);
        }
        return cell;
    }

    public static void setCellDateValue(Row row, int cellNumber, String value, CellStyle cellStyle) {
        Cell cell = getCell(row, cellNumber);
        Date date;
        try {
            date = ExcelParser.S_DATE_MINUTE.parse(value);
            cell.setCellValue(date);
            cell.setCellStyle(cellStyle);
        } catch (ParseException e) {
            e.printStackTrace();
            cell.setCellValue(value);
        }
    }

    public static void setCellValue(Row row, int cellNumber, Object value) {
        Cell cell = getCell(row, cellNumber);
        if (value.getClass() == String.class) {
            cell.setCellValue((String) value);
        } else if (value.getClass() == Integer.class || value.getClass() == int.class) {
            cell.setCellValue(((Integer)value).doubleValue());
        } else if (value.getClass() == Double.class || value.getClass() == double.class) {
            cell.setCellValue((Double) value);
        }

    }
}
