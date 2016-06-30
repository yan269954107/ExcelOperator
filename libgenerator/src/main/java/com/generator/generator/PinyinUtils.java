package com.generator.generator;

import com.generator.common.StrUtils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Created by yanxinwei on 16/6/26.
 */
public class PinyinUtils {

    private static final HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();

    static {
        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    }

    public static String[] convertToPinyin(String[] fields) {
        String[] results = new String[fields.length];
        int i = 0;
        for (String field : fields) {
            results[i] = getParamsName(field);
            i++;
        }
        return results;
    }

    private static String getParamsName(String params) {
        try {
            StringBuilder sb = new StringBuilder(StrUtils.getPrefix());
            for (int i = 0; i < params.length(); i++) {
                char c = params.charAt(i);
                if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] array = PinyinHelper.toHanyuPinyinStringArray(c, outputFormat);
                    if (array != null && array.length > 0 && array[0] != null) {
                        sb.append(array[0]);
                    }
                } else {
                    if (!Character.toString(c).matches("[A-Za-z0-9_]+")) {
                        return sb.toString();
                    }
                    sb.append(c);
                }
            }
            return sb.toString();
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
        }
        return null;
    }

    public static String toUpperCaseFirstOne(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return sb.toString();
    }

}
