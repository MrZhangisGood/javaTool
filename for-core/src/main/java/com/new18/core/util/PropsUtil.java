package com.new18.core.util;

import java.io.IOException;
import java.util.Properties;

/**
 * 获取系统配置的工具类
 * @author zhanglinmin@new18.cn
 * @return
 */
public class PropsUtil {

    private static Properties props = null;

    static {
        try {
            props = new Properties();
            props.load(PropsUtil.class.getClassLoader().getResourceAsStream("server.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProp(String key){
        if(props != null){
            return props.getProperty(key);
        }
        return null;
    }
}
