package com.yanxinwei.exceloperator.targetmodel;

/**
 * 把excel解析成目标对像,目标对象需要实现的接口
 * Created by yanxinwei on 16/6/1.
 */
public interface ParserTarget {

    //在excel sheet 的index 0开始
    int sheetAtIndex();

    //从第几行开始解析
    int startRow();

}
