package com.yanxinwei.exceloperator.targetmodel;

import java.io.Serializable;

/**
 * Created by yanxinwei on 16/7/6.
 */
public class Extension implements Serializable{

    private int count = 1;
    private String symbol = "";
    private int size;
    private int row = -1;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public String toString() {
        return "Extension{" +
                "count=" + count +
                ", symbol='" + symbol + '\'' +
                ", size=" + size +
                ", row=" + row +
                '}';
    }
}
