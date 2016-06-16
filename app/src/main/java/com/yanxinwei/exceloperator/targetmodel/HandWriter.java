package com.yanxinwei.exceloperator.targetmodel;

/**
 * Created by yanxinwei on 16/6/14.
 */
public class HandWriter implements ParserTarget{

    private String a_position1;
    private String b_position2;
    private String c_position3;
    private String d_label;

    public String getA_position1() {
        return a_position1;
    }

    public void setA_position1(String a_position1) {
        this.a_position1 = a_position1;
    }

    public String getB_position2() {
        return b_position2;
    }

    public void setB_position2(String b_position2) {
        this.b_position2 = b_position2;
    }

    public String getC_position3() {
        return c_position3;
    }

    public void setC_position3(String c_position3) {
        this.c_position3 = c_position3;
    }

    public String getD_label() {
        return d_label;
    }

    public void setD_label(String d_label) {
        this.d_label = d_label;
    }

    @Override
    public int sheetAtIndex() {
        return 0;
    }

    @Override
    public int startRow() {
        return 1;
    }
}
