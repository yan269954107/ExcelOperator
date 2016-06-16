package com.yanxinwei.exceloperator;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yanxinwei.exceloperator.excelparser.ExcelParser;
import com.yanxinwei.exceloperator.excelparser.ParserResult;
import com.yanxinwei.exceloperator.targetmodel.HandWriter;

import org.apache.poi.ss.formula.functions.T;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test(View view) {
        AssetManager manager = getAssets();
        try {
            InputStream is = manager.open("target.xlsx");
            ExcelParser parser = new ExcelParser();
            HandWriter handWriter = new HandWriter();
            parser.loadExcel(is, handWriter, new ParserResult() {
                @Override
                public void onSucceed(ArrayList<T> result) {

                }

                @Override
                public void onError(int code, Exception e) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
