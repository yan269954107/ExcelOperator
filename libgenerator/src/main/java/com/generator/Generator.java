package com.generator;


import com.generator.generator.GeneratorTools;
import com.generator.targetmodel.model.HandExcel;
import com.sun.codemodel.JClassAlreadyExistsException;

import java.io.IOException;

public class Generator {

    public static void main(String[] args) throws IOException, JClassAlreadyExistsException {
//        try {
//
//            File dir = new File("app/src/main/java/com/yanxinwei/exceloperator/targetmodel");
//            System.out.println("dir:" + dir.getAbsolutePath());
//            JCodeModel jcm = new JCodeModel();
//            JDefinedClass jdc = jcm._class("Target");
//            //定义成员变量
//            jdc.field(JMod.PRIVATE, jcm.INT, "mA_abc");
//            //定义set方法
//            JMethod setA_abcMethod = jdc.method(JMod.PUBLIC, jcm.VOID, "setA_abc");
//            setA_abcMethod.param(jcm.INT, "a_abc");
//            JBlock setA_abcBody = setA_abcMethod.body();
//            JFieldRef fieldA_abc = JExpr.ref("mA_abc");
//            setA_abcBody.assign(fieldA_abc, JExpr.ref("a_abc"));
//            //定义get方法
//            JMethod getA_abcMethod = jdc.method(JMod.PUBLIC, jcm.INT, "getA_abc");
//            JBlock getA_abcBody = getA_abcMethod.body();
//            getA_abcBody._return(fieldA_abc);
//
//            jcm.build(dir);
//
//            String packageName = "package com.yanxinwei.exceloperator.targetmodel;";
//            String javaPath = dir.getAbsolutePath().concat("/Target.java");
//            File targetFile = new File(javaPath);
//            BufferedReader br = new BufferedReader(new FileReader(targetFile));
//            String result = packageName + "\r\n";
//            String line;
//            while ((line = br.readLine()) != null) {
//                result = result + line;
//            }
//            targetFile.delete();
//            FileOutputStream fos = new FileOutputStream(targetFile);
//            fos.write(result.getBytes());
//            fos.flush();
//        } catch (JClassAlreadyExistsException e) {
//            e.printStackTrace();
//        }

        HandExcel handExcel = new HandExcel();
        GeneratorTools.createJavaFile(handExcel);
//        for (int i = 0; i < 100; i++) {
//            System.out.println(GeneratorTools.getPrefix());
//        }

    }

}
