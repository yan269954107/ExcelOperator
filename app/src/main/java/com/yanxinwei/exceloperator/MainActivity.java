package com.yanxinwei.exceloperator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.generator.excelparser.ExcelParser;
import com.yanxinwei.exceloperator.activity.TargetsActivity;
import com.yanxinwei.exceloperator.common.AppConstants;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private ArrayList<String> mFileNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExcelParser.getInstance().init();

        mListView = (ListView) findViewById(R.id.list_targets);
        TextView emptyView = (TextView) findViewById(R.id.empty_view);
        mListView.setEmptyView(emptyView);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String path = AppConstants.TARGETS_PATH.concat("/").concat(mFileNames.get(position));
                TargetsActivity.startActivity(MainActivity.this, path);
            }
        });

        loadFileList();

    }

    @Override
    protected void onDestroy() {
        ExcelParser.getInstance().recycle();
        super.onDestroy();
    }

    private void loadFileList() {
        File file = new File(AppConstants.TARGETS_PATH);
        if (file.exists()) {
            mFileNames.clear();
            for (File f : file.listFiles()) {
                if (f.getName().endsWith(".xlsx")) {
                    mFileNames.add(f.getName());
                }
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mFileNames);
        mListView.setAdapter(adapter);
    }

}
