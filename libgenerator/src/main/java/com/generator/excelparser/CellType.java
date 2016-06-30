package com.generator.excelparser;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yanxinwei on 16/6/28.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CellType {

    public static final int NORMAL = 1;
    public static final int DATE = 2;


    public int type() default NORMAL;

}
