package com.yanxinwei.exceloperator.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yanxinwei.exceloperator.R;

/**
 * Created by yanxinwei on 16/6/20.
 */
public class ParamsBlock extends LinearLayout {

    private TextView mTxtTitle;
    private EditText mEdtValue;
    private EditText mEdtValueRelation;

    private OnClickListener mClickListener;

    public ParamsBlock(Context context) {
        this(context, null);
    }

    public ParamsBlock(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        initView();
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.ParamsBlock);
        String title = array.getString(R.styleable.ParamsBlock_pb_text);
        mTxtTitle.setText(title);
        boolean hasRelation = array.getBoolean(R.styleable.ParamsBlock_pb_has_relation, false);
        if (hasRelation) mEdtValueRelation.setVisibility(VISIBLE);
        boolean focusable = array.getBoolean(R.styleable.ParamsBlock_pb_focusable, true);
        mEdtValue.setFocusable(focusable);
        if (!focusable) {
            mEdtValue.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mClickListener != null) {
                        mClickListener.onClick(ParamsBlock.this);
                    }
                }
            });
        }
        int inputType = array.getInt(R.styleable.ParamsBlock_android_inputType, EditorInfo.TYPE_NULL);
        mEdtValue.setInputType(inputType);
    }

    public ParamsBlock(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.params_block_layout, this);
        mTxtTitle = (TextView) findViewById(R.id.txt_title);
        mEdtValue = (EditText) findViewById(R.id.edt_value);
        mEdtValueRelation = (EditText) findViewById(R.id.edt_value_relation);
    }

    public void setValue(String value) {
        mEdtValue.setText(value);
    }

    public String getValue() {
        return mEdtValue.getText().toString().trim();
    }

    public void setRelativeValue(String relativeValue) {
        mEdtValueRelation.setText(relativeValue);
    }

    public String getRelativeValue() {
        return mEdtValueRelation.getText().toString().trim();
    }

    public void setTextWatcher(TextWatcher textWatcher) {
        mEdtValue.addTextChangedListener(textWatcher);
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        super.setOnClickListener(l);
        mClickListener = l;
    }
}
