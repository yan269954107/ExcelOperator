package com.yanxinwei.exceloperator.excelparser;

import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;

/**
 * Created by yanxinwei on 16/6/1.
 */
public interface ParserResult {

    void onSucceed(ArrayList<T> result);

    void onError(int code, Exception e);

}
