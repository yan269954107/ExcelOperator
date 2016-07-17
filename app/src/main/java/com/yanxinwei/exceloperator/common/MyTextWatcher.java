package com.yanxinwei.exceloperator.common;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by yanxinwei on 16/7/16.
 */
public abstract class MyTextWatcher implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
