package com.yanxinwei.exceloperator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.generator.excelparser.ExcelParser;
import com.yanxinwei.exceloperator.activity.TargetsActivity;
import com.yanxinwei.exceloperator.common.AppConstants;
import com.yanxinwei.exceloperator.common.DialogUtils;
import com.yanxinwei.exceloperator.common.SPUtils;
import com.yanxinwei.exceloperator.common.T;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int WRITER_ID = 0xff;

    private ListView mListView;
    private ArrayList<String> mFileNames = new ArrayList<>();

    private String mWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExcelParser.getInstance().init();

        mListView = (ListView) findViewById(R.id.list_targets);
        TextView emptyView = (TextView) findViewById(R.id.empty_view);
        mListView.setEmptyView(emptyView);

        mWriter = (String) SPUtils.get(this, AppConstants.SHAREPRE_USER_NAME, "");

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (TextUtils.isEmpty(mWriter)) {
                    T.showShort(MainActivity.this, "请先填写描述人");
                    return;
                }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuItem mAddItem = menu.add(0, WRITER_ID, 0, getString(R.string.writer));
        mAddItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case WRITER_ID:
                DialogUtils.showInputDialog(this, "请输入描述人", mWriter, new DialogUtils.DialogListOnItem() {
                    @Override
                    public void onItem(String data) {
                        mWriter = data;
                        SPUtils.put(MainActivity.this, AppConstants.SHAREPRE_USER_NAME, data);
                    }
                });
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
