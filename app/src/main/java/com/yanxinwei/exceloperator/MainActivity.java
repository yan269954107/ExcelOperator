package com.yanxinwei.exceloperator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test(View view) {
//        AssetManager manager = getAssets();
//        try {
//            HandWriter handWriter = new HandWriter();
//            InputStream is = manager.open(handWriter.getPath());
//            ExcelParser parser = new ExcelParser();
//            parser.loadExcel(is, handWriter, new ParserResult() {
//                @Override
//                public void onSucceed(ArrayList<T> result) {
//
//                }
//
//                @Override
//                public void onError(int code, Exception e) {
//
//                }
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
