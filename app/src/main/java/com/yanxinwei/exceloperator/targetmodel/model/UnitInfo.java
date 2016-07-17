package com.yanxinwei.exceloperator.targetmodel.model;

/**
 * Created by yanxinwei on 16/7/11.
 */
public class UnitInfo {

    private String unitType;
    private String unitSubType;

    public UnitInfo(String unitType, String unitSubType) {
        this.unitType = unitType;
        this.unitSubType = unitSubType;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getUnitSubType() {
        return unitSubType;
    }

    public void setUnitSubType(String unitSubType) {
        this.unitSubType = unitSubType;
    }
}
