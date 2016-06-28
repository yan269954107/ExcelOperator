package com.generator.excelparser;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

import java.text.SimpleDateFormat;

/**
 * Created by yanxinwei on 16/6/22.
 */
public class ExcelUtils {

    public static final SimpleDateFormat date1 = new SimpleDateFormat("yyyy/M/d h:mm");
    public static final SimpleDateFormat date2 = new SimpleDateFormat("yyyy/M/d");

    public static Object convertCellValue(Cell cell){
        try {
            if (null == cell){
                return "";
            }else {
                switch (cell.getCellType()){
                    case Cell.CELL_TYPE_BLANK:
                        return "";
                    case Cell.CELL_TYPE_BOOLEAN:
                        return cell.getBooleanCellValue();
                    case Cell.CELL_TYPE_ERROR:
                        return "";
                    case Cell.CELL_TYPE_NUMERIC:
                        if(HSSFDateUtil.isCellDateFormatted(cell)){
                            if(cell.getCellStyle().getDataFormat() == 14){
                                return date2.format(DateUtil.getJavaDate(cell.getNumericCellValue()));
                            }else if(cell.getCellStyle().getDataFormat() == 22){
                                return date1.format(DateUtil.getJavaDate(cell.getNumericCellValue()));
                            }else {
                                return date1.format(DateUtil.getJavaDate(cell.getNumericCellValue()));
                            }
                        }
                        // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                        else if(cell.getCellStyle().getDataFormat() == 58){
                            return date1.format(DateUtil.getJavaDate(cell.getNumericCellValue()));
                        }else {
                            return cell.getNumericCellValue();
                        }

                    case Cell.CELL_TYPE_STRING:
                        return cell.getStringCellValue();
                    default:
                        return "";
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    public static String convertCellValueToString(Cell cell) {
        Object o = convertCellValue(cell);
        return String.valueOf(o);
    }
}
