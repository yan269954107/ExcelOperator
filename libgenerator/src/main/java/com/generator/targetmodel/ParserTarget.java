package com.generator.targetmodel;

/**
 * 把excel解析成目标对像,目标对象需要实现的接口
 * Created by yanxinwei on 16/6/1.
 */
public abstract class ParserTarget implements Target{

    protected String mExcelPath;

    protected int mRow;

    //从第几行开始解析
    public int startRow() {
        return 0;
    }

    //设置目标excel的路径
    public void setPath(String excelPath) {
        mExcelPath = excelPath;
    }

    @Override
    public String getPath() {
        return mExcelPath;
    }

    public int getRow() {
        return mRow;
    }

    public void setRow(int row) {
        mRow = row;
    }
}
