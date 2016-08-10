package com.yanxinwei.exceloperator.common;

import android.content.Context;

import com.yanxinwei.exceloperator.targetmodel.model.ExtraDes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by yanxinwei on 16/7/15.
 */
public class Utils {

    private static final int LABEL_LENGTH = 4;

    private static ExtraDes sExtraDes;

    public static String[] getExtraDes(String extra) {
        int length = extra.length();
        int splitIndex = 0;
        for (int i = 0; i < length; i++) {
            if (extra.charAt(i) > 177) {
                splitIndex = i;
                break;
            }
        }
        String area = extra.substring(0, splitIndex);
        String des = extra.substring(splitIndex, length);
        return new String[]{area, des};
    }

    public static String getLabelNumber(String preNumber) {
        int number = Integer.parseInt(preNumber);
        number++;
        StringBuilder labelNumber = new StringBuilder(number + "");
        int difference = LABEL_LENGTH - labelNumber.length();
        for (int i = 0; i < difference; i++) {
            labelNumber.insert(0, "0");
        }
        return labelNumber.toString();
    }

    public static String getExtraPrefix(String extra) {
        if (null == extra || extra.length() == 0) return "";
        int length = extra.length();
        int splitIndex = 0;
        for (int i = 0; i < length; i++) {
            if (extra.charAt(i) < 65 || extra.charAt(i) > 122) {
                splitIndex = i;
                break;
            }
        }
        return extra.substring(0, splitIndex);
    }

    public static synchronized ExtraDes getExtraDes(Context context) {
        if (sExtraDes == null) {
            try {
                StringBuilder sb = new StringBuilder();
                BufferedReader bf = new BufferedReader(new FileReader(AppConstants.EXTRAS_PATH));
                String line;
                while ((line = bf.readLine()) != null) {
                    sb.append(line);
                }
                bf.close();
                sExtraDes = GsonUtil.jsonToBean(sb.toString(), ExtraDes.class);
            } catch (IOException e) {
                T.showShort(context, "加载附加描述失败,请检查在sd卡根目录是否存在extras.json文件");
            }
        }
        return sExtraDes;
    }

}
