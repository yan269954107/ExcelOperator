package com.yanxinwei.exceloperator;

import android.support.multidex.MultiDexApplication;

import com.yanxinwei.exceloperator.targetmodel.Hand;
import com.yanxinwei.exceloperator.targetmodel.HandVo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yanxinwei on 16/7/3.
 */
public class MyApplication extends MultiDexApplication {

    private static MyApplication sInstance;

    private HashMap<String, Hand> mTargets;

    private ArrayList<HandVo> mHandVos;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static MyApplication getInstance() {
        return sInstance;
    }

    public HashMap<String, Hand> getTargets() {
        return mTargets;
    }

    public void setTargets(HashMap<String, Hand> targets) {
        mTargets = targets;
    }

    public ArrayList<HandVo> getHandVos() {
        return mHandVos;
    }

    public void setHandVos(ArrayList<HandVo> handVos) {
        mHandVos = handVos;
    }

    public void shiftRow(int index, int offset) {
        for (int i = index; i < mHandVos.size(); i++) {
            mHandVos.get(i).addRow(offset);
            for (HandVo extension : mHandVos.get(i).getExtensions()) {
                extension.addRow(offset);
            }
        }
    }
}
