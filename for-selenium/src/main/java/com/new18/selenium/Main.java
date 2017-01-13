package com.new18.selenium;

import com.new18.core.util.CTool;
import com.new18.core.util.PropsUtil;
import com.new18.selenium.service.ChangeChartConUseTime;

public class Main extends RuntimeException {

    //线程数量
    public static int runThread = 0;

    public static void main(String[] args) {
        try {
            runThread = CTool.toInt(PropsUtil.getProp("server.run.thread"));

            for(int i = 0; i<runThread; i++){
                //网站压力类
                new Thread(new ChangeChartConUseTime(runThread, i)).start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
