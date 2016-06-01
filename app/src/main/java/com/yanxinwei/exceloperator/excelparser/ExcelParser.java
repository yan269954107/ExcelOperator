package com.yanxinwei.exceloperator.excelparser;

import com.yanxinwei.exceloperator.common.AppConstants;
import com.yanxinwei.exceloperator.targetmodel.ParserTarget;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 这里用的是poi做解析,这里就跟poi耦合在一起了,
 * 不再在poi之上再封装自己的接口了,
 * 因为目前来看Java解析xlsx格式的文件也就这个库能做
 * Created by yanxinwei on 16/6/1.
 */
public class ExcelParser {

    public void loadExcel(String path, ParserResult result, ParserTarget target) {
        try {
            Sheet targetSheet = getTargetSheet(path, target.sheetAtIndex());

        } catch (IOException e) {
            result.onError(AppConstants.ERROR_FILE_NOT_FIND, e);
        }
    }

    private Sheet getTargetSheet(String path, int index) throws IOException {
        InputStream is = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(index);
        return sheet;
    }
}
