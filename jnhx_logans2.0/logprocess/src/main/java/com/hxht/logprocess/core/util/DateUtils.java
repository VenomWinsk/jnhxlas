package com.hxht.logprocess.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期计算工具类
 *
 * Created by Administrator on 2017/5/16.
 */
public class DateUtils {

    private static final String PATTERN_YYYY_MM_DD="yyyy_MM_dd";
    private static final String PATTERN_YYYY="yyyy";
    private static final String PATTERN_YYYY_MM="yyyy_MM";
    private static final String PATTERN_W="w";
    /**
     * 计算两个日期之间相差的天数
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException
    {
        SimpleDateFormat sdf=new SimpleDateFormat(PATTERN_YYYY_MM_DD);
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }





    /**
     * date2比date1多的天数
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1,Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)   //同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2-day1) ;
        }
        else    //不同年
        {
            System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2-day1;
        }
    }










    /**
     * 字符串的日期格式的计算
     * @param smdate
     * @param bdate
     * @return
     * @throws ParseException
     */
    public static int daysBetween(String smdate,String bdate) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat(PATTERN_YYYY_MM_DD);
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 解析时间
     * @param time
     * @return 年
     */
    public static String dateFormatY(long time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERN_YYYY);
        String format = simpleDateFormat.format(time);
        return format;
    }

    /**
     * 解析时间
     * @param time
     * @return 年_月
     */
    public static String dateFormatYM(long time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERN_YYYY_MM);
        String format = simpleDateFormat.format(time);
        return format;
    }

    /**
     * 解析时间
     * @param time
     * @return 年_月_日
     */
    public static String dateFormatYMD(long time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERN_YYYY_MM_DD);
        String format = simpleDateFormat.format(time);
        return format;
    }

    /**
     * 解析时间
     * @param time
     * @return 时间在本年中的周序号
     */
    private static int week_of_year(long time){
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(new Date(time));
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 解析时间
     * @param time
     * @return 年_周w
     */
    public static String year_week(long time){
        return dateFormatY(time)+"_"+week_of_year(time)+PATTERN_W;
    }

    /**
     * 格式化日期转时间戳
     */
    public static Long timeToLong(String time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date ad = sdf.parse(time);
            return ad.getTime();
        } catch (ParseException e) {
            return (long)0;
        }

    }
}
