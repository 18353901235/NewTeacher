package com.zhouqiang.framework.util;

import android.text.TextUtils;
import android.util.Log;

import com.zhouqiang.framework.BaseConfig;

import java.util.ArrayList;

/**
 * 打印精确行数的log扩展
 */
public class Logger {
    private static final String ERROR = "The log message is null.";
    private static final int LENGTH_PER = 4000;
    public static String MYLOG = "zq:";
    public static void setMYLOG(String MYLOG) {
        Logger.MYLOG = MYLOG;
    }
    private enum Level {
        V, D, I, W, E
    }

    //MainActivity.CountDown(Line:91): //此处为获取调用行数与类名//排队序列 前两位已被占用
    private static String generateTag() {
        StackTraceElement caller = Thread.currentThread().getStackTrace()[4];
        String tag = " %s.%s(Line:%d)"; // 占位符
        String callerClazzName = caller.getClassName(); // 获取到类名
        callerClazzName = callerClazzName.substring(callerClazzName
                .lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(),
                caller.getLineNumber()); // 替换
        tag = TextUtils.isEmpty(MYLOG) ? tag : MYLOG + ":"
                + tag;
        return tag;
    }

    public static void println(Object msg) {
        if (BaseConfig.LOG)
            System.out.println(generateTag() + ":" + msg);
    }

    public static void v(String msg) {
        log(generateTag(), msg, Level.V);
    }

    public static void d(String msg) {
        log(generateTag(), msg, Level.D);
    }

    public static void i(String msg) {
        log(generateTag(), msg, Level.I);
    }

    public static void w(String msg) {
        log(generateTag(), msg, Level.W);
    }

    public static void e(String msg) {
        log(generateTag(), msg, Level.E);
    }
    public static void e(String Tag,String msg) {
        log(Tag, msg, Level.E);
    }
    private static ArrayList<String> split(String msg) {
        ArrayList<String> strings = null;
        if (msg != null) {
            int length = msg.length();
            int count = length / LENGTH_PER;
            int remain = length % LENGTH_PER;
            strings = new ArrayList<String>();
            if (count == 0)
                strings.add(msg);
            else {
                for (int i = 0; i < count; i++) {
                    int start = i * LENGTH_PER;
                    int end = start + LENGTH_PER - 1;
                    strings.add(msg.substring(start, end));
                }
                if (remain != 0) {
                    int start = count * LENGTH_PER;
                    int end = length - 1;
                    strings.add(msg.substring(start, end));
                }
            }
        }
        return strings;
    }

    private static void log(String tag, String log, Level level) {
        if (!BaseConfig.LOG)
            return;

        // 将字符串分段打印,解决logcat打印不全的问题
        ArrayList<String> strings = split(log);
        switch (level) {
            case V:
                if (strings == null)
                    Log.v(tag, ERROR);
                else
                    for (String string : strings)
                        Log.v(tag, string);
                break;
            case D:
                if (strings == null)
                    Log.d(tag, ERROR);
                else
                    for (String string : strings)
                        Log.d(tag, string);
                break;
            case I:
                if (strings == null)
                    Log.i(tag, ERROR);
                else
                    for (String string : strings)
                        Log.i(tag, string);
                break;
            case W:
                if (strings == null)
                    Log.w(tag, ERROR);
                else
                    for (String string : strings)
                        Log.w(tag, string);
                break;
            case E:
                if (strings == null)
                    Log.e(tag, ERROR);
                else
                    for (String string : strings)
                        Log.e(tag, string);
                break;
        }
    }
}
