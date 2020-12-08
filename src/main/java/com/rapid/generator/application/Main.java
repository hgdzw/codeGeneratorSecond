package com.rapid.generator.application;

import com.rapid.generator.invoker.base.*;
import com.rapid.generator.invoker.*;

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
        AnaTask invoker = new SingleInvoker.Builder()
                .setTableName("video_base")
                //.setClassName("Global_Dict")
                .build();
        invoker.execute();
    }

}
