package com.generator;

import com.generator.excelparser.ExcelParser;
import com.generator.excelparser.ParserResult;
import com.generator.targetmodel.ParserTarget;
import com.generator.targetmodel.model.Hand;

import java.util.ArrayList;

/**
 * Created by yanxinwei on 16/6/28.
 */
public class TestParser {

    public static void main(String[] args) {
        Hand hand = new Hand();
        hand.setPath("target.xlsx");
        final ExcelParser excelParser = ExcelParser.getInstance();
        excelParser.init();
        excelParser.loadExcel(new ParserResult<ParserTarget>() {
            @Override
            public void onSucceed(ArrayList<ParserTarget> result) {
                for (ParserTarget target : result) {
                    Hand h = (Hand) target;
                    System.out.println(h);
                    System.out.println("---------------------------------------");
                    h.setBc_tianjiariqi("2016/6/28 10:10");
                    excelParser.saveToFile(h, null);
                    break;
                }
            }

            @Override
            public void onError(int code, Exception e) {
                e.printStackTrace();
            }
        }, hand);
    }

}
