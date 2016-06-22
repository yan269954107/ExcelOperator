package com.generator.targetmodel;

/**
 * 生成目标类的解析对象
 * Created by yanxinwei on 16/6/16.
 */
public interface GeneratorTarget extends Target{

    //以第几行为目标生成对象
    int targetRow();

    //生成目标文件的包名
    String targetPackageName();

    //生成目标文件的类名
    String targetName();

    //生成的目标类的路径
    String targetPath();

}
