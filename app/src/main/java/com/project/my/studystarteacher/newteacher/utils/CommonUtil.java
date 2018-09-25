package com.project.my.studystarteacher.newteacher.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v4.app.ActivityCompat;

import java.io.Closeable;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * 共通工具类
 *
 * @author ZhouQiang
 */
public class CommonUtil {
    /*
     * 测试网络连接
     */
    @SuppressLint("MissingPermission")
    public static boolean isInternetAvailable(Context context) {
        try {
            ConnectivityManager manger = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            @SuppressLint("MissingPermission") NetworkInfo info = null;
            if (manger != null) {
                info = manger.getActiveNetworkInfo();
            }
            return (info != null && info.isConnected());
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 将ip的整数形式转换成ip形式
     *
     * @param ipInt
     * @return
     */
    public static String int2ip(int ipInt) {
        StringBuilder sb = new StringBuilder();
        sb.append(ipInt & 0xFF).append(".");
        sb.append((ipInt >> 8) & 0xFF).append(".");
        sb.append((ipInt >> 16) & 0xFF).append(".");
        sb.append((ipInt >> 24) & 0xFF);
        return sb.toString();
    }

    /**
     * 获取当前ip地址
     *
     * @param context
     * @return
     */
    public static String getLocalIpAddress(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context
                    .getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int i = wifiInfo.getIpAddress();
            return int2ip(i);
        } catch (Exception ex) {
            return " 获取IP出错鸟!!!!请保证是WIFI,或者请重新打开网络!\n" + ex.getMessage();
        }
        // return null;
    }


    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return true如果该字符串为null或者"",否则false
     */
    public static final boolean isNull(String str) {
        return str == null || "".equals(str.trim());
    }


    /**
     * 获取屏幕密度规格
     *
     * @param context
     * @return
     */
    public static final String getDpi(Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        String dpi = null;
        if (density == 0.75f)
            dpi = "ldpi";
        else if (density == 1.0f)
            dpi = "mdpi";
        else if (density == 1.5f)
            dpi = "hdpi";
        else if (density == 2f)
            dpi = "xhdpi";
        else if (density == 3f)
            dpi = "xxhdpi";
        return dpi;
    }

    /**
     * 程序是否在前台运行
     *
     * @return
     */
    public static boolean isAppOnForeground(Context context) {
        // Returns a list of application processes that are running on the
        // device
        ActivityManager activityManager = (ActivityManager) context
                .getApplicationContext().getSystemService(
                        Context.ACTIVITY_SERVICE);
        String packageName = context.getApplicationContext().getPackageName();
        List<RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        if (appProcesses == null)
            return false;

        for (RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }

        return false;
    }

    /**
     * 获取APP版本
     *
     * @param context 环境
     * @return String
     */
    public static final int getAppVersionCode(Context context)
            throws NameNotFoundException {
        PackageManager pm = context.getPackageManager();
        PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
        return pi.versionCode;
    }

    /**
     * 获取APP版本
     *
     * @param context 环境
     * @return String
     */
    public static final String getAppVersionName(Context context)
            throws NameNotFoundException {
        PackageManager pm = context.getPackageManager();
        PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
        return pi.versionName;
    }

    /**
     * 用当前时间给文件命名
     *
     * @return String yyyyMMdd_HHmmss
     */
    public static final String getFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault());
        return dateFormat.format(date);// + ".jpg";
    }

    /**
     * 格式化金额。
     *
     * @param money 金额
     * @return 格式化的金额
     */
    public static String formatMoneyString(String money) {

        String moneyFormatted = money;

        if (money != null && !"".equals(money.trim())) {
            Double moneyDouble = Double.parseDouble(money);
            moneyFormatted = new DecimalFormat("￥#0.00").format(moneyDouble);
        }

        return moneyFormatted;
    }

    /**
     * 格式化金额。
     *
     * @param money 金额
     * @return 格式化的金额
     */
    public static String formatMoneyStringInt(String money) {

        String moneyFormatted = money;

        if (money != null && !"".equals(money.trim())) {
            Double moneyDouble = Double.parseDouble(money);
            moneyFormatted = new DecimalFormat("￥#0").format(moneyDouble);
        }

        return moneyFormatted;
    }

    /**
     * 格式化金额。
     *
     * @param money 金额
     * @return 格式化的金额
     */
    public static String formatMoneyStringToDec2(String money) {

        String moneyFormatted = money;

        if (money != null && !"".equals(money.trim())) {
            Double moneyDouble = Double.parseDouble(money);
            moneyFormatted = new DecimalFormat("#0.00").format(moneyDouble);
        }

        return moneyFormatted;
    }

    /**
     * 格式化金额。
     *
     * @param money 金额
     * @return 格式化的金额
     */
    public static String formatMoneyToDec2(double money) {

        String moneyFormatted = new DecimalFormat("#0.00").format(money);
        return moneyFormatted;
    }


    /**
     * 格式化金额。
     *
     * @param money 金额
     * @return 格式化的金额
     */
    public static String formatMoney(double money) {

        String moneyFormatted = new DecimalFormat("￥#0.00").format(money);
        return moneyFormatted;
    }

    /**
     * 格式化数量。
     *
     * @param count 数量
     * @return 格式化的数量
     */
    public static String formatCount(int count) {

        String countFormatted = new DecimalFormat("#,##0").format(count);
        return countFormatted;
    }

    /**
     * 将字符串解析成数量。
     *
     * @param countStr 数量字符串
     * @return 数量
     */
    public static int formatCountString(String countStr) {

        int count = Integer.parseInt(countStr.replaceAll(",", ""));
        return count;
    }

    /**
     * 转换时间显示形式
     *
     * @param time   时间字符串yyyy-MM-dd HH:mm:ss
     * @param format 格式
     * @return String
     */
    public static String transTime(String time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.getDefault());
        try {
            Date date1 = sdf.parse(time);
            SimpleDateFormat dateFormat = new SimpleDateFormat(format,
                    Locale.getDefault());// "yyyy年MM月dd HH:mm"
            return dateFormat.format(date1);
        } catch (Exception e) {
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
     * 是否是开始时间的7点
     *
     * @param time 时间字符串
     * @return String
     */
    public static boolean Is7Time(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.getDefault());
        String currentz = getCurrentTime("yyyy-MM-dd HH:mm:ss");
        String dian7 = TimeUtil.TransTime(time, "yyyy-MM-dd")
                + " 7:00:00";
        Date now = null;
        Date d7 = null;
        try {
            now = sdf.parse(currentz); // 将当前时间转化为日期
            d7 = sdf.parse(dian7);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (now.getTime() >= d7.getTime())
            return true;
        return false;
    }

    /**
     * 转换时间显示形式(与当前系统时间比较),在发表话题、帖子和评论时使用
     *
     * @param time 时间字符串
     * @return String
     */
    public static String transTime(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.getDefault());
        String current = getCurrentTime("yyyy-MM-dd HH:mm:ss");
        String dian24 = transTime(current, "yyyy-MM-dd")
                + " 24:00:00";
        String dian00 = transTime(current, "yyyy-MM-dd")
                + " 00:00:00";
        Date now = null;
        Date date = null;
        Date d24 = null;
        Date d00 = null;
        try {
            now = sdf.parse(current); // 将当前时间转化为日期
            date = sdf.parse(time); // 将传入的时间参数转化为日期
            d24 = sdf.parse(dian24);
            d00 = sdf.parse(dian00);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long diff = now.getTime() - date.getTime(); // 获取二者之间的时间差值
        long min = diff / (60 * 1000);
        if (min <= 5)
            return "刚刚";
        if (min < 60)
            return min + "分钟前";

        if (now.getTime() <= d24.getTime() && date.getTime() >= d00.getTime())
            return "今天" + transTime(time, "HH:mm");

        int sendYear = Integer.valueOf(transTime(time, "yyyy"));
        int nowYear = Integer.valueOf(transTime(current, "yyyy"));
        if (sendYear < nowYear)
            return transTime(time, "yyyy-MM-dd HH:mm");
        else
            return transTime(time, "MM-dd HH:mm");
    }

    /**
     * 转换时间显示形式(与当前系统时间比较),在显示即时聊天的时间时使用
     *
     * @param time 时间字符串
     * @return String
     */
    public static String transTimeChat(String time) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                    Locale.getDefault());
            String current = getCurrentTime("yyyy-MM-dd HH:mm:ss");
            String dian24 = transTime(current, "yyyy-MM-dd")
                    + " 24:00:00";
            String dian00 = transTime(current, "yyyy-MM-dd")
                    + " 00:00:00";
            Date now = null;
            Date date = null;
            Date d24 = null;
            Date d00 = null;
            try {
                now = sdf.parse(current); // 将当前时间转化为日期
                date = sdf.parse(time); // 将传入的时间参数转化为日期
                d24 = sdf.parse(dian24);
                d00 = sdf.parse(dian00);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long diff = now.getTime() - date.getTime(); // 获取二者之间的时间差值
            long min = diff / (60 * 1000);
            if (min <= 5)
                return "刚刚";
            if (min < 60)
                return min + "分钟前";

            if (now.getTime() <= d24.getTime()
                    && date.getTime() >= d00.getTime())
                return "今天" + transTime(time, "HH:mm");

            int sendYear = Integer
                    .valueOf(transTime(time, "yyyy"));
            int nowYear = Integer.valueOf(transTime(current,
                    "yyyy"));
            if (sendYear < nowYear)
                return transTime(time, "yyyy-MM-dd HH:mm");
            else
                return transTime(time, "MM-dd HH:mm");
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 隐藏手机号和邮箱显示
     *
     * @param old     需要隐藏的手机号或邮箱
     * @param keytype 1手机2邮箱
     * @return
     */
    public static String hide(String old, String keytype) {
        try {
            if ("1".equals(keytype))
                return old.substring(0, 3) + "*****" + old.substring(8, 11);
            else {
                StringBuilder sb = new StringBuilder();
                String[] s = old.split("@");
                int l = s[0].length();
                int z = l / 3;
                sb.append(s[0].substring(0, z));
                int y = l % 3;
                for (int i = 0; i < z + y; i++)
                    sb.append("*");
                sb.append(s[0].substring(z * 2 + y, l));
                sb.append("@");
                if (s[1] == null) {

                }
                sb.append(s[1]);
                return sb.toString();
            }
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 方法说明：获取资源图片
     *
     * @param context 资源
     * @param id      资源图片id
     * @return
     */
    public static Drawable getResImage(Activity context, int id) {
        Drawable image = context.getResources().getDrawable(id);
        return image;
    }

    /**
     * 方法说明：打电话
     *
     * @param phone
     * @param context
     */
    @SuppressLint("MissingPermission")
    public static void makePhone(String phone, Context context) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        context.startActivity(intent);
    }

    /*
     * 此方法是用来检测电话号码是否合法 如果合法，返回true 如果不合法，返回false
     */
    public static boolean isPhoneNumber(String PhoneNumber) {
        /*Pattern p = Pattern
                .compile("^((13[0-9])|(14[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$");
		Matcher m = p.matcher(PhoneNumber);
		return m.matches();*/
          /*   boolean isPhoneNO = phoneNO
                .matches("^((13[0-9])|(15[^4,\\D])|(18[0,3,5-9])|(17[7-8]))\\d{8}$");*/
        boolean isPhoneNO = PhoneNumber.matches("^1\\d{10}$");
        return isPhoneNO;
    }

    /**
     * @param phoneNO 手机号
     * @return 是否为手机号
     */
    public static boolean isPhoneNO(String phoneNO) {
     /*   boolean isPhoneNO = phoneNO
                .matches("^((13[0-9])|(15[^4,\\D])|(18[0,3,5-9])|(17[7-8]))\\d{8}$");*/
        boolean isPhoneNO = phoneNO
                .matches("^1\\d{10}$");
        return isPhoneNO;
    }

    /*
     * 此方法是用来检测邮箱号是否合法 如果合法，返回true 如果不合法，返回false
     */
    public static boolean isEmailNumber(String emailNumber) {
        Pattern p = Pattern
                .compile("^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$");
        Matcher m = p.matcher(emailNumber);
        return m.matches();
    }

    /**
     * 校验密码
     *
     * @param password
     * @return 校验通过返回true，否则返回false
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,16}$";

    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }

    /**
     * 关闭closeable， 忽略任何检查的异常。 <br />
     * <p>
     * 不能用于 Android 中关闭Cursor、 CursorWrapper，虽然实现了closeable接口。具体原因不明 <br />
     *
     * @param closeables
     */
    public static void closeQuitely(Closeable... closeables) {
        if (closeables == null) {
            return;
        }
        for (Closeable c : closeables) {
            if (c != null) {
                try {
                    c.close();
                } catch (RuntimeException rethrown) {
                    throw rethrown;
                } catch (Exception e) {
                    // 忽略
                }
            }
        }
    }
}
