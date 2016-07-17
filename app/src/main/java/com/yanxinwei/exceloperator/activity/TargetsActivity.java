package com.yanxinwei.exceloperator.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.generator.excelparser.ExcelParser;
import com.generator.excelparser.ParserResult;
import com.generator.targetmodel.ParserTarget;
import com.yanxinwei.exceloperator.MyApplication;
import com.yanxinwei.exceloperator.R;
import com.yanxinwei.exceloperator.adapter.TargetsAdapter;
import com.yanxinwei.exceloperator.common.T;
import com.yanxinwei.exceloperator.targetmodel.Hand;
import com.yanxinwei.exceloperator.targetmodel.HandVo;
import com.yanxinwei.exceloperator.targetmodel.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

public class TargetsActivity extends AppCompatActivity implements View.OnClickListener{

    private ProgressDialog mProgressDialog;
    private ListView mListView;
    private TargetsAdapter mAdapter;

//    private ArrayList<ParserTarget> mTargets;
    private HashMap<String, Hand> mTargets;
    private ArrayList<HandVo> mHandVos;

    private String path;

    private FloatingActionButton mFabAdd;

    public static void startActivity(Context context, String path) {
        Intent intent = new Intent(context, TargetsActivity.class);
        intent.putExtra("path", path);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_targets);

        path = getIntent().getStringExtra("path");

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTarget();
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.list_targets);
        TextView mEmptyView = (TextView) findViewById(R.id.empty_view);
        mListView.setEmptyView(mEmptyView);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HandVo handVo = mHandVos.get(position);
                HandWriteActivity.startActivity(TargetsActivity.this, handVo, position, false);
            }
        });

        mFabAdd = (FloatingActionButton) findViewById(R.id.fab_add);
        mFabAdd.setOnClickListener(this);
    }

    private void loadTarget() {
        if (TextUtils.isEmpty(path)) {
            T.showShort(this, "未找到文件,请退出后重试");
            finish();
        } else {
            Hand hand = new Hand();
            hand.setPath(path);
            mProgressDialog = ProgressDialog.show(this, null, "正在导入任务", true, false);
            ExcelParser.getInstance().loadExcel(new ParserResult<ParserTarget>() {
                @Override
                public void onSucceed(ArrayList<ParserTarget> result) {
                    transformToMap(result);
                    MyApplication.getInstance().setTargets(mTargets);
                    mHandVos = Mapper.transformToVos(result);
                    MyApplication.getInstance().setHandVos(mHandVos);
                    mAdapter = new TargetsAdapter(TargetsActivity.this, mHandVos);
                    mListView.setAdapter(mAdapter);
                    mProgressDialog.dismiss();
                    if (mHandVos.size() > 0) {
                        mFabAdd.hide();
                    } else {
                        mFabAdd.show();
                    }
                }

                @Override
                public void onError(int code, Exception e) {
                    e.printStackTrace();
                    T.showShort(TargetsActivity.this, "导入任务失败,请退出后重试");
                    mProgressDialog.dismiss();
                }
            }, hand);
        }
    }

    private void transformToMap(ArrayList<ParserTarget> targets) {
        mTargets = new HashMap<>();
        for (ParserTarget target : targets) {
            Hand hand = (Hand) target;
            mTargets.put(hand.get_Ad_biaoqian()+hand.get_Ae_kuozhanhao(), hand);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_add:
                HandVo first = Mapper.getFirstHandVo(path);
                MyApplication.getInstance().getHandVos().add(first);
                HandWriteActivity.startActivity(this, first, 0, true);
                break;
        }
    }
}
