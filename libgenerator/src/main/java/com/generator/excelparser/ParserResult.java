package com.generator.excelparser;

import com.generator.targetmodel.ParserTarget;

import java.util.ArrayList;

/**
 * Created by yanxinwei on 16/6/1.
 */
public interface ParserResult<T extends ParserTarget> {

    void onSucceed(ArrayList<T> result);

    void onError(int code, Exception e);

}
