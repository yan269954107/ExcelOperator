package com.generator.excelparser;

/**
 * Created by yanxinwei on 16/6/28.
 */
public interface Result {

    void onSucceed();

    void onError(int code, Exception e);

}
