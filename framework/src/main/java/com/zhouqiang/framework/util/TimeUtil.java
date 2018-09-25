package com.zhouqiang.framework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.R.attr.format;

/**
 * 时间相关工具类
 */
public class TimeUtil {


    /**
     * 转换时间显示形式
     *
     * @param time   时间字符串yyyy-MM-dd HH:mm:ss
     * @param format 格式
     * @return String
     */
    public static date getYMD(long time) {
        Date date = new Date(time);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = (cal.get(Calendar.MONTH) + 1);
        int day = cal.get(Calendar.DATE);
        int hour = cal.get(Calendar.HOUR);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        Logger.d("getYMD:" + year + "::" + month + "::" + day);
        TimeUtil.date date1 = new date(year, month, day, hour, minute, second);
        return date1;
    }


    public static long getLongTime(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.getDefault());
        try {
            Date date1 = sdf.parse(time);
            return date1.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 转换时间显示形式
     *
     * @param time   时间字符串yyyy-MM-dd HH:mm:ss
     * @param format 格式
     * @return String
     */
    public static String TransTimeNoTime(String time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.getDefault());
        try {
            Date date1 = sdf.parse(time);
            SimpleDateFormat dateFormat = new SimpleDateFormat(format,
                    Locale.getDefault());// "yyyy年MM月dd HH:mm"
            return dateFormat.format(date1);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 转换时间显示形式
     *
     * @param time   时间字符串yyyy-MM-dd HH:mm:ss
     * @param format 格式
     * @return String
     */
    public static String TransTime(String time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.getDefault());
        try {
            Date date1 = sdf.parse(time);
            SimpleDateFormat dateFormat = new SimpleDateFormat(format,
                    Locale.getDefault());// "yyyy年MM月dd HH:mm"
            return dateFormat.format(date1);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 转换时间显示形式
     *
     * @param time   时间字符串yyyy-MM-dd HH:mm:ss
     * @param format 格式
     * @return String
     */
    public static String TransTime(Long time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format,
                Locale.getDefault());
        try {
            Date d1 = new Date(time);
            String format1 = sdf.format(d1);
            return format1;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取系统当前时间
     *
     * @param format 时间格式yyyy-MM-dd HH:mm:ss
     * @return String
     */
    public static String getCurrentTime(String format) {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(format,
                Locale.getDefault());
        return dateFormat.format(date);
    }

    /**
     * 获取系统当前时间
     * 时间格式yyyy-MM-dd HH:mm:ss
     *
     * @return String
     */
    public static String getCurrentTime() {

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm",
                Locale.getDefault());
        return dateFormat.format(date);
    }

    /**
     * 获取系统当前时间
     *
     * @return String
     */
    public static String getCurrentYMDTime() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",
                Locale.getDefault());
        return dateFormat.format(date);
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     */
    public static int daysBetween(Date smdate, Date bdate) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 字符串的日期格式的计算
     */
    public static int daysBetween(String smdate, String bdate) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }


    public static class date {
        public date(int year, int month, int day, int hour, int second, int minute) {
            this.year = year;
            this.second = second;
            this.minute = minute;
            this.hour = hour;
            this.day = day;
            this.month = month;
        }

        private int year;
        private int month;
        private int day;
        private int hour;
        private int minute;
        private int second;

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getHour() {
            return hour;
        }

        public void setHour(int hour) {
            this.hour = hour;
        }

        public int getMinute() {
            return minute;
        }

        public void setMinute(int minute) {
            this.minute = minute;
        }

        public int getSecond() {
            return second;
        }

        public void setSecond(int second) {
            this.second = second;
        }
    }
}
