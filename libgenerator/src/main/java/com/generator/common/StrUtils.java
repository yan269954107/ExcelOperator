package com.generator.common;

import com.generator.generator.PinyinUtils;

/**
 * Created by yanxinwei on 16/6/28.
 */
public class StrUtils {

    private static final String SET_PREFIX = "set";
    private static final String GET_PREFIX = "get_";

    private static String PREFIX_CURRENT = "aa_";

    public static String getFieldName(String fieldName) {
        return "m" + fieldName;
    }

    public static String getSetMethodName(String fieldName) {
        return SET_PREFIX + PinyinUtils.toUpperCaseFirstOne(fieldName);
    }

    public static String getGetMethodName(String fieldName) {
        return GET_PREFIX + PinyinUtils.toUpperCaseFirstOne(fieldName);
    }

    public static String getPrefix() {
        String result = PREFIX_CURRENT;
        char first = PREFIX_CURRENT.charAt(0);
        char second = PREFIX_CURRENT.charAt(1);
        second ++;
        if (second > 'z') {
            first ++;
            second = 'a';
        }
        PREFIX_CURRENT = String.valueOf(new char[]{first, second, '_'});
        return result;
    }

}
