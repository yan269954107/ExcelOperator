package com.generator.targetmodel.model;

import com.generator.targetmodel.GeneratorTarget;

/**
 * Created by yanxinwei on 16/6/16.
 */
public class HandExcel implements GeneratorTarget {

    private static final String packageName = "package com.yanxinwei.exceloperator.targetmodel;";
    private static final String name = "Hand";
    private static final String targetPath = "app/src/main/java/com/yanxinwei/exceloperator/targetmodel";

    @Override
    public int targetRow() {
        return 0;
    }

    @Override
    public String targetPackageName() {
        return packageName;
    }

    @Override
    public String targetName() {
        return name;
    }

    @Override
    public String targetPath() {
        return targetPath;
    }

    @Override
    public int sheetAtIndex() {
        return 0;
    }

    @Override
    public String getExcelPath() {
        return "target.xlsx";
    }
}
