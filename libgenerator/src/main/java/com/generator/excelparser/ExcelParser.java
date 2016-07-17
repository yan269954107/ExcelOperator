package com.generator.excelparser;

import android.os.Handler;
import android.os.HandlerThread;

import com.generator.common.Constants;
import com.generator.common.Handlers;
import com.generator.common.StrUtils;
import com.generator.targetmodel.GeneratorTarget;
import com.generator.targetmodel.ParserTarget;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 这里用的是poi做解析,这里就跟poi耦合在一起了,
 * 不再在poi之上再封装自己的接口了,
 * 因为目前来看Java解析xlsx格式的文件也就这个库能做
 * Created by yanxinwei on 16/6/1.
 */
public class ExcelParser {

    private HandlerThread mHandlerThread;
    private Handler mHandler;

    private InputStream mInputStream;
    private XSSFWorkbook mWorkbook;

    private static ExcelParser sExcelParser = null;

    private XSSFCellStyle dateCellStyle;
    private static final String DATE_FORMAT = "yyyy/M/d h:mm";
    public static final SimpleDateFormat S_DATE_MINUTE = new SimpleDateFormat("yyyy/M/d h:mm");

    private ExcelParser() {
    }

    public static ExcelParser getInstance() {
        if (sExcelParser == null) {
            synchronized (ExcelParser.class) {
                if (sExcelParser == null) {
                    sExcelParser = new ExcelParser();
                }
            }
        }
        return sExcelParser;
    }

    public void init() {
        if (mHandlerThread == null) {
            mHandlerThread = new HandlerThread("parser thread");
            mHandlerThread.start();
            mHandler = new Handler(mHandlerThread.getLooper());
        }
    }

    public void recycle() {
        if (mHandlerThread != null) {
            mHandlerThread.quit();
        }
    }

    public void loadExcel(InputStream is, ParserTarget target, ParserResult result) {
        try {
            Sheet sheet = getTargetSheet(is, target.sheetAtIndex());
            Row row;
            Cell cell;
            int rowCount = sheet.getPhysicalNumberOfRows();
            for (int r = target.startRow(); r < rowCount; r++) {
                row = sheet.getRow(r);
                Class clazz = target.getClass();
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    System.out.println("@@@@ fieldName : " + field.getName());
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

    public void loadExcel(final ParserResult<ParserTarget> result, final ParserTarget target) {
        checkNull();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    Sheet sheet = getTargetSheet(target.getPath(), target.sheetAtIndex());
                    Row row;
                    int rowCount = sheet.getPhysicalNumberOfRows();
                    final ArrayList<ParserTarget> datas = new ArrayList<>(rowCount);
                    for (int r = target.startRow(); r < rowCount; r++) {
                        row = sheet.getRow(r);
                        Class clazz = target.getClass();
                        Field[] fields = clazz.getDeclaredFields();
                        Constructor<?> con = clazz.getConstructor();
                        ParserTarget obj = (ParserTarget) con.newInstance();
                        for (int c = 0; c < fields.length; c++) {
                            Method method = clazz.getMethod(StrUtils.getSetMethodName(fields[c].getName().substring(1)),
                                    fields[c].getType());
                            ExcelUtils.convertCellValue(row.getCell(c), fields[c].getType(), method, obj);
                        }
                        obj.setRow(r);
                        obj.setPath(target.getPath());
                        datas.add(obj);
                    }
                    Handlers.postMain(new Runnable() {
                        @Override
                        public void run() {
                            result.onSucceed(datas);
                        }
                    });
                } catch (IOException | NoSuchMethodException | IllegalAccessException | InstantiationException
                        | InvocationTargetException e) {
                    Handlers.postMain(new Runnable() {
                        @Override
                        public void run() {
                            result.onError(Constants.ERROR_LOAD_FAILED, e);
                        }
                    });
                } finally {
                    closeInputStream();
                }
            }
        });

    }

    public void saveToFile(final ParserTarget target, final Result result) {
        checkNull();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    Sheet sheet = getTargetSheet(target.getPath(), target.sheetAtIndex());
                    CreationHelper helper = mWorkbook.getCreationHelper();
                    if (dateCellStyle == null) {
                        dateCellStyle = mWorkbook.createCellStyle();
                        dateCellStyle.setDataFormat(helper.createDataFormat().getFormat(DATE_FORMAT));
                    }

                    fillData(sheet, target);
                    writeToFile(target.getPath());
                    result.onSucceed();
                } catch (IOException | IllegalAccessException e) {
                    result.onError(Constants.ERROR_SAVE_FAILED, e);
                    e.printStackTrace();
                }
            }
        });
    }

    private void fillData(Sheet sheet, ParserTarget target) throws IllegalAccessException {
        Row row = sheet.getRow(target.getRow());
        if (row == null) {
            row = sheet.createRow(target.getRow());
        }

        Class clazz = target.getClass();
        Field[] fields = clazz.getDeclaredFields();
        int i = 0;
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(CellType.class)) {
                System.out.println(field.getName() + " : " + field.get(target) + "   class:" + field.get(target).getClass());
                ExcelUtils.setCellDateValue(row, i, (String) field.get(target), dateCellStyle);
            } else {
                System.out.println(field.getName() + " : " + field.get(target) + "   class:" + field.get(target).getClass());
                ExcelUtils.setCellValue(row, i, field.get(target));
            }
            i++;
        }
    }

    public String[] getTargetFieldNames(GeneratorTarget target) {
        String[] result = null;
        try {
            Row row = getTargetRow(target.getPath(), target.sheetAtIndex(), target.targetRow());
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
        } finally {
            closeInputStream();
        }
        return result;
    }

    public void removeRow(final String path, final int index, final int startRow, final int endRow, final Result result) {
        checkNull();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    Sheet sheet = getTargetSheet(path, index);

                    int lastRowNum = sheet.getLastRowNum();
                    int removeRow = endRow - startRow + 1;
                    if (endRow > 0 && endRow < lastRowNum)
                        sheet.shiftRows(endRow + 1, lastRowNum, -removeRow);
                    if (endRow == lastRowNum) {
                        for (int i = startRow; i <= endRow; i++) {
                            Row removingRow = sheet.getRow(i);
                            if (removingRow != null)
                                sheet.removeRow(removingRow);
                        }
                    }
                    writeToFile(path);
                    result.onSucceed();
                } catch (IOException e) {
                    result.onError(Constants.ERROR_REMOVE_FAILED, e);
                }
            }
        });
    }

    /**
     * 批量插入excel
     * @param targets
     * @throws IOException
     */
    public void insertRow(final List<ParserTarget> targets, final Result result) {

        checkNull();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (null == targets || targets.size() == 0) {
                    result.onSucceed();
                    return;
                }

                try {
                    final ParserTarget startTarget = targets.get(0);
                    ParserTarget endTarget = targets.get(targets.size() - 1);
                    int startRow = startTarget.getRow();
                    int rows = endTarget.getRow() - startTarget.getRow() + 1;

                    Sheet sheet = getTargetSheet(startTarget.getPath(), startTarget.sheetAtIndex());

                    if (startRow < sheet.getLastRowNum()) {
                        sheet.shiftRows(startRow, sheet.getLastRowNum(), rows, true, false);
                    }

                    for (ParserTarget target : targets) {
                        fillData(sheet, target);
                    }

                    writeToFile(startTarget.getPath());
                    result.onSucceed();
                } catch (IOException | IllegalAccessException e) {
                    result.onError(Constants.ERROR_INSERT_FAILED, e);
                }

            }
        });

    }

    private Sheet getTargetSheet(String path, int index) throws IOException {
        mInputStream = new FileInputStream(path);
        mWorkbook = new XSSFWorkbook(mInputStream);
        Sheet sheet = mWorkbook.getSheetAt(index);
        return sheet;
    }

    private void writeToFile(String path) throws IOException {
        FileOutputStream os = new FileOutputStream(path);
        mWorkbook.write(os);

        closeInputStream();
        os.close();
    }

    private Row getTargetRow(String path, int sheetIndex, int targetRow) throws IOException {
        Sheet sheet = getTargetSheet(path, sheetIndex);
        return sheet.getRow(targetRow);
    }

    private void closeInputStream() {
        try {
            mInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkNull() {
        if (mHandlerThread == null || !mHandlerThread.isAlive()) {
            mHandlerThread = new HandlerThread("parser thread");
            mHandlerThread.start();
            mHandler = new Handler(mHandlerThread.getLooper());
        }
    }

}
