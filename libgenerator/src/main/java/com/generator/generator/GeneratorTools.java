package com.generator.generator;

import com.generator.common.StrUtils;
import com.generator.excelparser.ExcelParser;
import com.generator.targetmodel.GeneratorTarget;
import com.sun.codemodel.JBlock;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JFieldRef;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by yanxinwei on 16/6/22.
 */
public class GeneratorTools {

    public static void createJavaFile(GeneratorTarget target) throws JClassAlreadyExistsException, IOException {
        File file = new File(target.targetPath());
        JCodeModel jcm = new JCodeModel();
        JDefinedClass jdc = jcm._class(target.targetName());

        ExcelParser excelParser = ExcelParser.getInstance();
        String[] fields = excelParser.getTargetFieldNames(target);

        String[] convertedFields = PinyinUtils.convertToPinyin(fields);
        for (String str : convertedFields) {
            System.out.println(str);
            generatorFields(jdc, str);
        }

        jcm.build(file);
        insertPackageName(file, target.targetPackageName(), target.targetName());
    }

    private static void insertPackageName(File dir, String packageName, String fileName) throws IOException {
        String javaPath = dir.getAbsolutePath().concat("/").concat(fileName).concat(".java");
        File targetFile = new File(javaPath);
        BufferedReader br = new BufferedReader(new FileReader(targetFile));
        String result = packageName + "\r\n";
        String line;
        while ((line = br.readLine()) != null) {
            result = result + line;
        }
        targetFile.delete();
        FileOutputStream fos = new FileOutputStream(targetFile);
        fos.write(result.getBytes());
        fos.flush();
    }

    private static void generatorFields(JDefinedClass jdc, String fieldName) {
        jdc.field(JMod.PRIVATE, String.class, StrUtils.getFieldName(fieldName));
        definedSetGetMethod(jdc, fieldName);
    }

    private static void definedSetGetMethod(JDefinedClass jdc, String fieldName) {
        //定义set方法
        JMethod setA_abcMethod = jdc.method(JMod.PUBLIC, void.class, StrUtils.getSetMethodName(fieldName));
        setA_abcMethod.param(String.class, fieldName);
        JBlock setA_abcBody = setA_abcMethod.body();
        JFieldRef fieldA_abc = JExpr.ref(StrUtils.getFieldName(fieldName));
        setA_abcBody.assign(fieldA_abc, JExpr.ref(fieldName));
        definedGetMethod(jdc, fieldA_abc, fieldName);
    }



    private static void definedGetMethod(JDefinedClass jdc, JFieldRef fieldA_abc, String fieldName) {
        //定义get方法
        JMethod getA_abcMethod = jdc.method(JMod.PUBLIC, String.class, StrUtils.getGetMethodName(fieldName));
        JBlock getA_abcBody = getA_abcMethod.body();
        getA_abcBody._return(fieldA_abc);
    }


}
