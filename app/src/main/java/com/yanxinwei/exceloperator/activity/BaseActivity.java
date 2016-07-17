package com.yanxinwei.exceloperator.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by yanxinwei on 16/7/14.
 */
public class BaseActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showDialog(String message) {
        if (!isFinishing()) {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(this, null, message);
            } else {
                if (!mProgressDialog.isShowing()) {
                    mProgressDialog.show();
                }
            }
        }
    }

    public void hideDialog() {
        if (!isFinishing() && mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }
}
