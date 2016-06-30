package com.generator.targetmodel;

/**
 * Created by yanxinwei on 16/6/16.
 */
public interface Target {

    //在excel sheet 的index 0开始
    int sheetAtIndex();

    //目标excel的路径
    String getPath();

}
