package com.yanxinwei.exceloperator.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.yanxinwei.exceloperator.R;
import com.yanxinwei.exceloperator.common.AppConstants;
import com.yanxinwei.exceloperator.common.DialogUtils;
import com.yanxinwei.exceloperator.targetmodel.Extension;
import com.yanxinwei.exceloperator.targetmodel.Mapper;

import java.util.ArrayList;

public class ExtensionActivity extends AppCompatActivity {

    private static final int CONFIRM_ID = 0X01;
    private static final int ADD_ID = 0X02;

    private String mExtensions;
    private int mSize;

    private LinearLayout mLlContainer;
    private ArrayList<Extension> mExtensionList;

    public static void startActivity(Activity context, String extension, int size, int requestCode) {
        Intent intent = new Intent(context, ExtensionActivity.class);
        intent.putExtra("extension", extension);
        intent.putExtra("size", size);
        context.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extension);

        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mExtensions = getIntent().getStringExtra("extension");
        mSize = getIntent().getIntExtra("size", 0);

        mLlContainer = (LinearLayout) findViewById(R.id.ll_container);

        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuItem mAddItem = menu.add(0, ADD_ID, 0, getString(R.string.add));
        mAddItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        MenuItem mConfirmItem = menu.add(0, CONFIRM_ID, 0, getString(R.string.confirm));
        mConfirmItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case CONFIRM_ID:
                for (int i = 0; i < mExtensionList.size(); i++) {
                    if (TextUtils.isEmpty(mExtensionList.get(i).getSymbol())) {
                        mExtensionList.remove(i);
                        i--;
                    }
                }
                Intent intent = new Intent(this, HandWriteActivity.class);
                intent.putExtra(HandWriteActivity.EXTENSION_LIST, mExtensionList);
                setResult(RESULT_OK, intent);
                finish();
                return true;
            case ADD_ID:
                Extension extension = new Extension();
                extension.setSize(mSize);
                extension.setSymbol("");
                extension.setCount(1);
                createExtension(extension);
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initView() {

        mExtensionList = new ArrayList<>();
        mLlContainer.removeAllViews();
        if (!TextUtils.isEmpty(mExtensions)) {
            String[] extensions = mExtensions.split("\\+");
            for (String ex : extensions) {
                final Extension extension = Mapper.parseExtension(ex, mSize);
                createExtension(extension);
            }
        }
    }

    private void createExtension(final Extension extension) {
        final View view = LayoutInflater.from(this).inflate(R.layout.item_extension, null);
        EditText edtCount = (EditText) view.findViewById(R.id.edt_count);
        final EditText edtSymbol = (EditText) view.findViewById(R.id.edt_symbol);
        EditText edtSize = (EditText) view.findViewById(R.id.edt_size);
        ImageButton btnCancel = (ImageButton) view.findViewById(R.id.btn_cancel);

        edtCount.setText(extension.getCount() + "");
        edtCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    int count = Integer.parseInt(s.toString());
                    extension.setCount(count);
                }
            }
        });
        edtSymbol.setText(extension.getSymbol());
        edtSymbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExtensionsDialog(edtSymbol, extension);
            }
        });
        edtSize.setText(extension.getSize() + "");
        edtSize.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    int size = Integer.parseInt(s.toString());
                    extension.setSize(size);
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLlContainer.removeView(view);
                mExtensionList.remove(extension);
            }
        });

        view.setTag(extension);
        mExtensionList.add(extension);
        mLlContainer.addView(view);
    }

    private void showExtensionsDialog(final EditText editText, final Extension extension) {
        DialogUtils.showDialogList(AppConstants.STR_EXTENSION_SYMBOL, AppConstants.STR_EXTENSION_FULL,
                "请选择扩展", this, new DialogUtils.DialogListOnItem() {
                    @Override
                    public void onItem(String data) {
                        editText.setText(data);
                        extension.setSymbol(data);
                    }
                });
    }

}
