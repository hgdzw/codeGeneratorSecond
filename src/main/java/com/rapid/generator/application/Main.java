package com.rapid.generator.application;

import com.rapid.generator.invoker.base.*;
import com.rapid.generator.invoker.*;

import java.util.ArrayList;

/**
 * Author GreedyStar
 * Date   2018/9/5
 */
public class Main {

    public static void main(String[] args) {

        //one2many();
        single();
    }
    public static void single() {
        ArrayList<String> tableList = new ArrayList<>();
        tableList.add("diy_individual_center");
//        tableList.add("carbon_code");
        for (String tableName : tableList) {
            AnaTask invoker = new SingleInvoker.Builder()
                    .setTableName(tableName)
                    //.setClassName("Global_Dict")
                    .build();
            invoker.execute();
        }
    }

}
