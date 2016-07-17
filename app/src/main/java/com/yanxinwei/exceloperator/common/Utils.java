package com.yanxinwei.exceloperator.common;

/**
 * Created by yanxinwei on 16/7/15.
 */
public class Utils {

    private static final int LABEL_LENGTH = 4;

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

}
