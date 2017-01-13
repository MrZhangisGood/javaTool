package com.new18.core.util;


import java.text.DecimalFormat;

/**
 * @Description: 简单的数据转换
 * @author zhanglm@joyplus.com.cn
 * @date 2013-8-19 下午1:22:52
 * @version 1.0
 */
public class CTool {

    private static DecimalFormat fitNum = new DecimalFormat("#.####");
    private static DecimalFormat twoNum = new DecimalFormat("00");
    private static DecimalFormat price = new DecimalFormat("#.0000");

    /**
     * @Description:将Object数据转成Double
     * @return
     */
	public static Double toDou(Object obj) {
		if(obj == null){
			return 0.0;
		}else{
			return VTool.isNumber(obj.toString()) || VTool.isENumber(obj.toString()) ? Double.parseDouble(obj.toString()) : 0.0;
		}
	}

    /**
     * @Description:将Object数据转成String
     * @return
     */
    public static String toStr(Object obj) {
        if(obj == null || "".equals(String.valueOf(obj))){
            return null;
        }
        return String.valueOf(obj);
    }

    /**
     * @Description:将Object数据转成String
     * @return
     */
    public static String toStr(Object obj, String def) {
        if(obj == null || "".equals(String.valueOf(obj))){
            return def;
        }
        return String.valueOf(obj);
    }
    
	/**
     * @Description:将Object数据转成Integer
     * @return
     */
    public static Integer toInt(Object obj) {
        if (obj == null) {
            return 0;
        }else if(obj instanceof Integer){
            return (Integer)obj;
        }
        String str = (String) obj;
        return VTool.isInteger(str) ? Integer.valueOf(str) : null;
    }

	/**
     * @Description:将Object数据转成Integer
     * @return
     */
    public static Integer toInt(Object obj, int def) {
        if (obj == null) {
            return def;
        }
        return toInt(obj);
    }

    /**
     * @Description:转化成金额形式
     * @return
     */
    public static String formatPrice(Object obj) {
        return price.format(obj);
    }

	/**
     * @Description:将Object数据转成Integer
     * @return
     */
    public static String format2Num(Integer obj) {
        return twoNum.format(obj);
    }

	/**
     * @Description:转成适合的数字
     * @return
     */
    public static String format2Fit(Double obj) {
        return fitNum.format(obj);
    }
	
}
