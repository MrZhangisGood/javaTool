package com.new18.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期工具类
 * <p>包含日期的格式化、日期和String 的相互转换以及时间计算等操作API</p>
 */
public class DateUtils {

    public static String FULL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static String DATE_CN = "yyyy年MM月dd日 HH时mm分";
    /**
     * @Description: 将带格式的日期转化成yyyyMMdd格式字符串
     * @param date 日期字符串
     * @return yyyyMMdd格式日期[yyyyMMdd]
     */
    public static String dateActionToDao(String date) {
        return formatDate(parseDate(date));
    }
    /**
     * @Description: 数字转化成日期字符串
     * @author linyj@joyplus.com.cn
     * @param dateNum 日期8位数字
     * @return 日期字符串yyyy/MM/dd
     */
    public static String convertDateNumber(int dateNum) {
        String dateStr = String.valueOf(dateNum);
        StringBuffer dateBuf = new StringBuffer();
        if (dateStr.length() == 8) {
            dateBuf.append(dateStr.substring(0, 4));
            dateBuf.append("/");
            dateBuf.append(dateStr.substring(4, 6));
            dateBuf.append("/");
            dateBuf.append(dateStr.substring(6));
            dateStr = dateBuf.toString();
        }
        return dateStr;
    }
    /**
     * @Description: 8位数字日期转换成yyyy/MM/dd格式字符串
     * @author linyj@joyplus.com.cn
     * @param date 8位数字
     * @return yyyy/MM/dd格式字符串
     */
    public static String formatDate(int date) {
        return formatDate(date,"yyyy/MM/dd");
    }

    /**
     * @Description: 8位数字日期转换成指定格式字符串
     * @author linyj@joyplus.com.cn
     * @param date 8位数字
     * @param format 日期格式
     * @return 指定格式日期字符串
     */
    public static String formatDate(int date,String format) {
        return formatDate(String.valueOf(date),format);
    }

    /**
     * @Description: 任意格式日期转换成指定格式字符串
     * @author linyj@joyplus.com.cn
     * @param date 任意格式日期字符串
     * @param format 日期格式
     * @return 指定格式日期字符串
     */
    public static String formatDate(String date,String format) {
        try {
            Date datetag = parseDate(date);//DateUtils.parseDate(date, new String[] {"yyyyMMdd" });
            SimpleDateFormat formater = new SimpleDateFormat(format);
            return formater.format(datetag);
        } catch(Exception e) {
            return String.valueOf(date);
        }
    }

    /**
     * @Description: Date转化成yyyyMMdd格式字符串
     * @author linyj@joyplus.com.cn
     * @param date 日期
     * @return yyyyMMdd格式日期字符串
     */
    public static String formatDate(Date date) {
        return formatDate(date,"yyyy-MM-dd");
    }

    /**
     * @Description: Date转化成字符串
     * @author linyj@joyplus.com.cn
     * @param date 日期
     * @param format 转化格式
     * @return 指定格式日期字符串
     */
    public static String formatDate(Date date,String format) {
        if(date == null){
            return "";
        }
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * @Description: 字符串转换成DATE
     * @author linyj@joyplus.com.cn
     * @param dateStr 日期字符串
     * @param format 日期字符串格式
     * @return 日期
     */
    public static Date parseDate(String dateStr, String format) {
        try {
            return new SimpleDateFormat(format).parse(dateStr);
        } catch (Exception e) {

        }
        return null;
    }
    /**
     * @Description: 字符串转换成DATE
     * @author linyj@joyplus.com.cn
     * @param date 待转换的日期字符串
     * @return 日期
     * @throws ParseException
     */
    public static Date parseDate(String date) {
        if(date==null) return null;
        try {
            String parse = date;
            parse = parse.replaceFirst("^[0-9]{4}([^0-9]?)", "yyyy$1");
            parse = parse.replaceFirst("^[0-9]{2}([^0-9]?)", "yy$1");
            parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1MM$2");
            parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}( ?)", "$1dd$2");
            parse = parse.replaceFirst("( )[0-9]{1,2}([^0-9]?)", "$1HH$2");
            parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1mm$2");
            parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1ss$2");
            DateFormat format = new SimpleDateFormat(parse);
            return format.parse(date);
        } catch(ParseException e) {
            return null;
        }
    }

    /**
     * @Description: 日期增加天数
     * @author linyj@joyplus.com.cn
     * @param date 日期
     * @param days 增加的天数
     * @return 增加天数后的日期 date为null时，返回null
     */
    public static Date dateAddDay(Date date,int days) {
        if(date==null) return null;
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(GregorianCalendar.DATE, days);
        return calendar.getTime();
    }

    public static void main(String[] args) {
        System.out.println(parseDate("11/06/2014","dd/MM/yyyy"));
    }
    /**
     * @Description: 日期时间作截至日期查询时转换成23:59:59格式
     * @author linyj@joyplus.com.cn
     * @param dateStr
     * @param format
     * @return
     */
    public static Date formatFromDate(String dateStr,String format){
        Date date = parseDate(dateStr, format);
        if(date==null) return null;
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(GregorianCalendar.HOUR_OF_DAY, 23);
        calendar.add(GregorianCalendar.MINUTE, 59);
        calendar.add(GregorianCalendar.SECOND, 59);
        return calendar.getTime();
    }
    public static Date formatFromDate(Date date){
        if(date==null) return null;
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(GregorianCalendar.HOUR_OF_DAY, 23);
        calendar.add(GregorianCalendar.MINUTE, 59);
        calendar.add(GregorianCalendar.SECOND, 59);
        return calendar.getTime();
    }
    /**
     * @Description: 日期增加小时数
     * @author linyj@joyplus.com.cn
     * @param date 日期
     * @param hours 增加的小时数
     * @return 增加小时数后的日期 date为null时，返回null
     */
    public static Date dateAddHour(Date date,int hours) {
        if(date==null) return null;
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(GregorianCalendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }


    /**
     * @Description: 日期增加分钟数
     * @author linyj@joyplus.com.cn
     * @param date 日期
     * @param minutes 增加的分钟数
     * @return 增加分钟数后的日期 date为null时，返回null
     */
    public static Date dateAddMinute(Date date,int minutes) {
        if(date==null) return null;
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(GregorianCalendar.MINUTE, minutes);
        return calendar.getTime();
    }

    /**
     * @Description: 日期增加秒数
     * @author linyj@joyplus.com.cn
     * @param date 日期
     * @param seconds 增加的秒数
     * @return 增加秒数后的日期 date为null时，返回null
     */
    public static Date dateAddSecond(Date date,int seconds) {
        if(date==null) return null;
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(GregorianCalendar.SECOND, seconds);
        return calendar.getTime();
    }

    /**
     * @Description: 获取日期在星期中的第几天 从1-7对应 日-六
     * @author linyj@joyplus.com.cn
     * @param date	代表日期的字符串
     * @return	1-7对应 日-六   -1非法日期
     */
    public static int dayOfWeek(String date) {
        return dayOfWeek(parseDate(date));
    }

    /**
     * @Description: 获取日期在星期中的第几天 从1-7对应 日-六
     * @author linyj@joyplus.com.cn
     * @param date	日期
     * @return	1-7对应 日-六
     */
    public static int dayOfWeek(Date date) {
        //获得日历对象
        Calendar c = Calendar.getInstance();
        //设置参数日期到日历对象中
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * @Description: 取得日期的天数
     * @author linyj@joyplus.com.cn
     * @param strDate	字符串格式日期
     * @return	当月天数，当字符串格式不合法时返回-1
     */
    public static int getDay(String strDate) {
        return getDay(parseDate(strDate));
    }
    /**
     * @Description: 取得日期的天数
     * @author linyj@joyplus.com.cn
     * @param date	Date日期
     * @return	当月天数
     */
    public static int getDay(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * @Description: 取得日期的小时数数
     * @author linyj@joyplus.com.cn
     * @param strDate	字符串格式日期
     * @return	当时小时数，当字符串格式不合法时返回-1
     */
    public static int getHour(String strDate) {
        return getHour(parseDate(strDate));
    }

    /***
     * @Description: 取得日期的小时数数
     * @author linyj@joyplus.com.cn
     * @param date	Date日期
     * @return	当时小时数
     */
    public static int getHour(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * @Description: 取得日期的月数
     * @author linyj@joyplus.com.cn
     * @param strDate	字符串格式日期
     * @return	当月月数 0-11，当字符串格式不合法时返回-1
     */
    public int getMonth(String strDate) {
        return getMonth(parseDate(strDate));
    }
    /***
     * @Description: 取得日期的月数
     * @author linyj@joyplus.com.cn
     * @param date	Date日期
     * @return	当月月数  0-11
     */
    public int getMonth(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH);
    }

    /**
     *
     * @Description: 获取两个日期之间的月份数
     * @author wuq@joyplus.com.cn
     * @param date1
     * @param date2
     * @return
     */
    public static int getMonthsBetween(Date date1, Date date2){
        int iMonth = 0;
        try{
            Calendar objCalendarDate1 = Calendar.getInstance();
            objCalendarDate1.setTime(date1);
            Calendar objCalendarDate2 = Calendar.getInstance();
            objCalendarDate2.setTime(date2);
            if (objCalendarDate2.equals(objCalendarDate1))
                return 0;
            if (objCalendarDate1.after(objCalendarDate2)){
                Calendar temp = objCalendarDate1;
                objCalendarDate1 = objCalendarDate2;
                objCalendarDate2 = temp;
            }
            if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1.get(Calendar.YEAR))
                iMonth = (objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1.get(Calendar.YEAR)) * 12 + objCalendarDate2.get(Calendar.MONTH) - objCalendarDate1.get(Calendar.MONTH);
            else
                iMonth = objCalendarDate2.get(Calendar.MONTH) - objCalendarDate1.get(Calendar.MONTH);
        } catch (Exception e){
            e.printStackTrace();
        }
        return iMonth;
    }

    /**
     * @Description: 计算两个日期相隔的天数
     * @author linyj@joyplus.com.cn
     * @param	firstDate 日期1
     * @param	secondDate 日期2
     * @return	两个日期相隔的天数
     */
    public static int daysBetweenTwoDate(Date firstDate,Date secondDate) {
        if (firstDate.after(secondDate)) {
            Date swap = firstDate;
            firstDate = secondDate;
            secondDate = swap;
        }
        return (int)((secondDate.getTime()-firstDate.getTime())/(24*60*60*1000));
    }

    /**
     * 计算从dateFrom到dateTo有多少天，如果dataFrom在dateTo之后则返回负数
     * @param dateFrom
     * @param dateTo
     * @return 两个日期之间的差
     */
    public static int dateDiff(Date dateFrom, Date dateTo){
        long dateDiffs = ( dateTo.getTime() - dateFrom.getTime() ) / (24*60*60*1000);
        int num = Integer.parseInt(Long.toString(dateDiffs));
        //如果两个日期包含时间则可能出现误差,所以需要修正
        Date temp = new Date(dateFrom.getTime() + 24*60*60*1000* Long.valueOf(num));
        if (!isSameDay(temp, dateTo)) {
            if (dateTo.compareTo(dateFrom) > 0)
                num++;
            if (dateTo.compareTo(dateFrom) < 0)
                num--;
        }
        return num;
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2)
    {
        if ((cal1 == null) || (cal2 == null)) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return (cal1.get(0) == cal2.get(0)) &&
                (cal1.get(1) == cal2.get(1)) &&
                (cal1.get(6) == cal2.get(6));
    }

    public static boolean isSameDay(Date date1, Date date2)
    {
        if ((date1 == null) || (date2 == null)) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameDay(cal1, cal2);
    }

    /**
     * @Description: 计算两个日期相隔的小时数
     * @author linyj@joyplus.com.cn
     * @param	firstDate 日期1
     * @param	secondDate 日期2
     * @return	两个日期相隔的小时数
     */
    public static int hoursBetweenTwoDate(Date firstDate,Date secondDate) {
        return (int)((secondDate.getTime()-firstDate.getTime())/(60*60*1000));
    }

    /**
     * @Description: 计算两个日期相隔的分钟数
     * @author linyj@joyplus.com.cn
     * @param	firstDate 日期1
     * @param	secondDate 日期2
     * @return	两个日期相隔的分钟数
     */
    public static int minutesBetweenTwoDate(Date firstDate,Date secondDate) {
        return (int)((secondDate.getTime()-firstDate.getTime())/(60*1000));
    }


    /**
     * @Description: 计算两个日期相隔的秒数
     * @author linyj@joyplus.com.cn
     * @param	firstDate  日期1
     * @param	secondDate 日期2
     * @return	两个日期相隔的秒数
     */
    public static int secondsBetweenTwoDate(Date firstDate,Date secondDate) {
        return (int)((secondDate.getTime()-firstDate.getTime())/(1000));
    }

    /**
     * @Description: 判断4位日期时间的先后
     * @author linyj@joyplus.com.cn
     * @param sdate	开始日期
     * @param edate	结束日期
     * @return	sdate在前返回true
     */
    public static boolean compareDate(Integer sdate,Integer edate) {
        int smonth = sdate/100;
        int sday = sdate%100;
        int emonth = edate/100;
        int eday = edate%100;
        return smonth==emonth ? sday<eday : smonth<emonth;
    }
    /**
     * @Description: 判断4位日期是否合法(MMdd)
     * @author linyj@joyplus.com.cn
     * @param date
     * @return 判断结果
     */
    public static boolean isCurrentDate(Integer date) {
        if(date<101 || date>1231) return false;
        int month = date/100;
        int day = date%100;
        if(month==1 || month==3 ||month==5 || month==7 || month==8 ||month==10 ||month==12) {
            return day<=31 && day>0;
        }else if(month==2) {
            return day<=29 && day>0;
        }else if(month==4 ||month==6 || month==9 ||month==11) {
            return day<=30 && day>0;
        }
        return false;
    }
    /**
     * @Description: 检查日期格式是否合法
     * @author linyj@joyplus.com.cn
     * @param src	日期字符串
     * @return true：合法 false：不合法
     */
    public static boolean isDates(String src) {
        if(VTool.isEmpty(src)) {
            return true;
        }
        return src.contains("yyyy")&&src.contains("MM")&&src.contains("dd");
    }
    /**
     * @Description: 检查日期格式是否是全日期格式
     * @author linyj@joyplus.com.cn
     * @param src	日期字符串
     * @return true：是 false：不是
     */
    public static boolean isFullDate(String src) {
        if(VTool.isEmpty(src)) {
            return true;
        }
        return src.contains("yyyy")&&src.contains("MM")
                &&src.contains("dd")&&src.contains("HH")
                &&src.contains("mm")&&src.contains("ss");
    }
    /**
     * @Description: 检查日期格式是否是年月日期格式
     * @author linyj@joyplus.com.cn
     * @param src	日期字符串
     * @return true：是 false：不是
     */
    public static Boolean isMdDate(String src) {
        if(VTool.isEmpty(src)) {
            return true;
        }
        return src.contains("MM")&&src.contains("dd");
    }

    /**
     * @Description: 检查时间格式是否合法
     * @author linyj@joyplus.com.cn
     * @param src	时间格式字符串
     * @return true：合法 false：不合法
     * @throws ParseException
     */
    public static Boolean isTimes(String src) {
        if(VTool.isEmpty(src)) {
            return true;
        }
        return src.contains("HH")&&src.contains("mm");
    }
    /**
     * @Description: 检查时间格式是否合法
     * @author linyj@joyplus.com.cn
     * @param src	时间格式字符串
     * @return true：合法 false：不合法
     */
    public static Boolean isFullTime(String src) {
        if(VTool.isEmpty(src)) {
            return true;
        }
        return src.contains("HH")&&src.contains("mm")&&src.contains("ss");
    }
    /**
     * @Description: 日期对象清空非日期数据
     * @author linyj@joyplus.com.cn
     * @param date
     * @return
     */
    public static Date clearTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        clearTime(cal);
        return cal.getTime();
    }
    public static void clearTime(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
    }
    /**
     *
     * @Description:
     * @param date
     * @param parseString
     * @param formatString
     * @return
     */
    public static String formatDate(String date ,String parseString,String formatString){
        SimpleDateFormat dateFormat =new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dateFormats =new SimpleDateFormat("yyyy-MM-dd");
        String formatDate ="";
        try {
            Date parseDate = dateFormat.parse(date);
            formatDate = dateFormats.format(parseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return formatDate;
    }
    /**
     * Description: 
     * <br/>整合李风云的校验最晚报价时间方法
     * <p>@author linyj@joyplus.com.cn Joyplus</p>  
     * @param date
     * @param hour
     * @return
     */
    public static Date validDeadTime(String reallyTime){
    	//验证reallyTime
    	if(VTool.isEmpty(reallyTime)){
    		return null;
    	}
        //最晚报价时间
        Date time = parseDate(reallyTime, FULL_DATE_FORMAT);
        if(time == null || new Date().getTime() > time.getTime()){
            return null;
        }
        return time;
    }
}
