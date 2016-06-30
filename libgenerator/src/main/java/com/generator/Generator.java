package com.generator;


import com.generator.generator.GeneratorTools;
import com.generator.targetmodel.model.HandExcel;
import com.sun.codemodel.JClassAlreadyExistsException;

import java.io.IOException;

public class Generator {

    public static void main(String[] args) throws IOException, JClassAlreadyExistsException {
        HandExcel handExcel = new HandExcel();
        GeneratorTools.createJavaFile(handExcel);
    }

}
