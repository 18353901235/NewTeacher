package com.project.my.studystarteacher.newteacher.utils;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.NumberKeyListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhouqiang.framework.util.Logger;
import com.zhouqiang.framework.util.ToastUtil;
import com.zhouqiang.framework.util.WindowSize;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * 共通工具类
 *
 * @author ZhouQiang
 */
public class Utility {

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        // listAdapter.getCount()返回数据项的数目
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    public static int getheight(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        return totalHeight;
    }

    public static void setListViewHeightBasedOnChildren(GridView listView) {
        // 获取listview的adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        // 固定列宽，有多少列
        int col = 3;// listView.getNumColumns();
        int totalHeight = 0;
        // i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，
        // listAdapter.getCount()小于等于8时计算两次高度相加
        for (int i = 0; i < listAdapter.getCount(); i += col) {
            // 获取listview的每一个item
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            // 获取item的高度和
            totalHeight += listItem.getMeasuredHeight();
        }

        // 获取listview的布局参数
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        // 设置高度
        params.height = totalHeight;
        // 设置margin
        ((ViewGroup.MarginLayoutParams) params).setMargins(10, 10, 10, 10);
        // 设置参数
        listView.setLayoutParams(params);
    }

    /**
     * 判断某activity是否处于栈顶
     *
     * @return true在栈顶 false不在栈顶
     */
    public static boolean isActivityTop(Class cls, Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        String name = manager.getRunningTasks(1).get(0).topActivity.getClassName();
        Logger.d("name:" + name);
        return name.equals(cls.getName());
    }

    public static void setInputTypeDecimal(EditText mEdit) {
        mEdit.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);

        mEdit.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (source.equals(".") && dest.toString().length() == 0) {
                    return "0.";
                }
                if (dest.toString().contains(".")) {
                    int index = dest.toString().indexOf(".");
                    int mlength = dest.toString().substring(index).length();
                    if (mlength == 3) {
                        return "";
                    }
                }
                return null;
            }
        }});

        setPricePoint(mEdit);
    }

    public static void setPricePoint(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    editText.setText(s);
                    editText.setSelection(2);
                }

                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        editText.setText(s.subSequence(0, 1));
                        editText.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }

        });

    }

    public static void setInputTypeNumbAdd26(EditText tv) {
        tv.setKeyListener(new NumberKeyListener() {
            @Override
            protected char[] getAcceptedChars() {
                char[] mychar = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'A', 'B', 'S', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'X', 'Y', 'Z'};//这里是可以输入的字符
                return mychar;
            }

            @Override
            public int getInputType() {
                return InputType.TYPE_CLASS_TEXT;
            }
        });
    }

    public static String getContent(TextView tv) {
        return tv.getText().toString().trim();
    }

    public static String getTime(int time) {
        // 小时时间
        String timeStr = "";
        int h = time / 3600;
        int m = (time - h * 3600) / 60;
        int s = time - h * 3600 - m * 60;
        if (h > 0) {
            timeStr = h + "时";
        }

        if (m > 0) {
            timeStr += m + "分";
        }
        if (s > 0) {
            timeStr += s + "秒";
        }

        return timeStr;
    }

    /**
     * 验证用户名中文
     *
     * @param account
     * @return
     */
    public static boolean checkAccountMark(String account) {
        return verifyNickname(account);
    }

    private static boolean verifyNickname(String account) {
        int len = 0;
        char[] nickchar = account.toCharArray();
        for (int i = 0; i < nickchar.length; i++) {
            if (!isChinese(nickchar[i])) {
                return false;
            }
        }
        return true;
    }

    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
            /*  || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS*/
               /* || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
          *//*      || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION*//*
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS*/) {
            return true;
        }
        return false;
    }

    public static boolean isInternetAvailable(Context context) {
        try {
            ConnectivityManager manger = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manger.getActiveNetworkInfo();
            return (info != null && info.isConnected());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * MD5加密。
     *
     * @param str 字符串
     * @return MD5加密后字符串
     */
    public static String encryptMd5(String str) {
        try {
            // 加密
            MessageDigest md5Digest = MessageDigest.getInstance("MD5");
            md5Digest.update(str.getBytes("utf-8"));
            byte[] bytes = md5Digest.digest();
            // 转换成16进制
            String hexString = toHexString(bytes);
            return hexString;
        } catch (Exception e) {
            return null;
        }
    }

    /***
     * 打电话
     *
     * @param phone
     */
    public static void makePhone(Context context, String phone) {
        Intent intent = new Intent(Intent.ACTION_CALL,
                Uri.parse("tel:" + phone));
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager
                .PERMISSION_GRANTED) {
            ToastUtil.showLongToast(context, "请打开权限");
            return;
        }
        context.startActivity(intent);
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
            moneyFormatted = new DecimalFormat("¥#0.00").format(moneyDouble);
        }

        return moneyFormatted;
    }

    /**
     * 获取资源图片
     *
     * @param id 资源图片id
     * @return
     */
    public static Drawable getResImage(Context context, int id) {
        Drawable image = context.getResources().getDrawable(id);
        return image;
    }

    /**
     * 设置可用属性。
     *
     * @param v       View
     * @param enabled 可用属性
     */
    public static void setEnabled(View v, boolean enabled) {

        if (v.isEnabled() != enabled) {
            v.setEnabled(enabled);
        }
    }

    /**
     * 格式化日期。
     *
     * @param format 格式
     * @param date   日期
     * @return 显示用格式的日期
     */
    public static String formatDate(String format, Date date) {
        String formattedDate = new SimpleDateFormat(format).format(date);
        return formattedDate;
    }

    /**
     * 格式化时间
     *
     * @param year
     * @param month
     * @param date
     * @param hour
     * @param minute
     * @return
     */
    public static String formatDateTimeDisp(int year, int month, int date,
                                            int hour, int minute) {

        String formattedDateTime = formatString("%04d-%02d-%02d %02d:%02d",
                year, month, date, hour, minute);
        return formattedDateTime;
    }

    /**
     * 把long 转换成 日期 再转换成String类型
     */
    public static String formatLongTime(Long millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        Date date = new Date(millSec);
        return sdf.format(date);
    }

    /***
     * 格式化日期
     *
     * @param year  年
     * @param month 月
     * @param day   日
     * @return
     */
    public static String formatDate(int year, int month, int day) {
        String formatedDate = formatString("%04d-%02d-%02d", year, month, day);
        return formatedDate;
    }

    /**
     * 格式化字符串
     *
     * @param format 格式
     * @param args   内容
     * @return 格式化的字符串
     */
    private static String formatString(String format, Object... args) {

        String formattedString = String.format(format, args);
        return formattedString;
    }

    /**
     * 解析日期。
     *
     * @param format  格式
     * @param dateStr 日期字符串
     * @return 日期
     */
    public static Date parseDate(String format, String dateStr) {

        Date date;
        try {
            date = new SimpleDateFormat(format).parse(dateStr);
        } catch (ParseException e) {
            date = null;
        }

        return date;
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
     * 解析数量。
     *
     * @param countStr 数量字符串
     * @return 数量
     */
    public static int parseCount(String countStr) {

        int count = Integer.parseInt(countStr.replaceAll(",", ""));
        return count;
    }

    /**
     * 格式化金额。
     *
     * @param money 金额
     * @return 格式化的金额
     */
    public static String formatMoney(BigDecimal money) {
        String moneyFormatted = "";
        try {
            moneyFormatted = new DecimalFormat("￥#0.00").format(money);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return moneyFormatted;
    }

    /***
     * 格式化金額
     *
     * @param money
     * @return
     */
    public static String formatMoneyNoSymbol(BigDecimal money) {
        String moneyFormatted = new DecimalFormat("#0.00").format(money);
        return moneyFormatted;
    }

    /***
     * 格式化金額
     *
     * @param money
     * @return
     */
    public static String formatMoneyString2(BigDecimal money) {
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
     * 解析金额。
     *
     * @param moneyStr 金额字符串
     * @return 金额
     */
    public static double parseMoney(String moneyStr) {

        double money = Double.parseDouble(moneyStr.substring(1));
        return money;
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

    public static boolean isPhoneNOAndTel(String phoneNO) {
     /*   boolean isPhoneNO = phoneNO
                .matches("^((13[0-9])|(15[^4,\\D])|(18[0,3,5-9])|(17[7-8]))\\d{8}$");*/
        boolean isPhoneNO = phoneNO
                .matches("^(0\\d{2,3}-\\d{7,8}(-\\d{3,5}){0,1})|(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})$");
        return isPhoneNO;
    }

    /**
     * 将字符串转换成16进制字符串。
     *
     * @param bytes 加密后bytes数组
     * @return 16进制字符串
     */
    private static String toHexString(byte[] bytes) {

        StringBuilder hexBuilder = new StringBuilder();
        for (byte b : bytes) {
            // 转换为16进制
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                hexBuilder.append("0");
            }
            hexBuilder.append(hex);
        }

        // 转换为大写
        String hexString = hexBuilder.toString().toUpperCase();
        return hexString;
    }

    /**
     * 校验密码安全等级。
     *
     * @return 0：密码为空，1：密码长度小于6,2：输入了不符合规则的字符,3：密码安全等级为低,4:正常密码
     */
    public static int checkPassLevel(String pass) {

        // 空
        if (pass.isEmpty()) {
            return 0;
        }

        // 不足6位
        if (pass.length() < 6) {
            return 1;
        }

        // 格式
        String reg = "[\\da-zA-Z@_]+";
        if (!pass.matches(reg)) {
            return 2;
        }

        // 只包含一种
        String regLowNumber = "[\\d]+";
        String regLowChar = "[a-zA-Z]+";
        String regLow = "[@_]+";
        if (pass.matches(regLowNumber) || pass.matches(regLowChar)
                || pass.matches(regLow)) {
            return 3;
        }

        return 4;
    }

    /**
     * 验证车牌号码是否正确
     *
     * @return 1：输入为空 2:车牌号必须为6位 3：车牌首位必须是大写字母 4：第2～6位必须是大写英数字 0：正常值
     */
    public static int checkCarNumber(String carNumber) {
        // 车牌号码可包含的字符
        String regChar = "[A-Z]+";
        String regRight = "[\\dA-Z]+";

        if (carNumber == null || "".equals(carNumber)) {
            return 1;
        }
        if (6 != carNumber.length()) {
            return 2;
        }
        if (!carNumber.substring(0, 1).matches(regChar)) {
            return 3;
        }
        if (!carNumber.substring(1, 6).matches(regRight)) {
            return 4;
        }

        return 0;
    }

    /***
     * 获取GridView 每一项宽度
     *
     * @param context  关联
     * @param margId   左右间距
     * @param margItem 项目间距
     * @param Column   列数
     * @return 列宽
     */
    public static int getItemWidth(Context context, int margId, int margItem,
                                   int Column) {
        // 获取左右边距
        int marg;
        if (margId == -1) {
            marg = 0;
        } else {
            marg = context.getResources().getDimensionPixelSize(margId);
        }
        int margm;
        // 获取项目间距离
        if (margItem == -1) {
            margm = 0;
        } else {
            margm = context.getResources().getDimensionPixelSize(margItem);
        }
        // 获取屏幕宽度
        int wWidth = WindowSize.getWidth(context);
        // 获取项目总宽度
        int aWidth = wWidth - marg * 2 - margm * (Column - 1);
        // 获取每一项宽度
        int cWidth = aWidth / Column;
        return cWidth;

    }

    /***
     * 获取屏幕中的大小
     *
     * @param context 关联
     * @param sizeId  大小的id
     * @return 大小
     */
    public static int getPixelSize(Context context, int sizeId) {
        int size = 0;
        size = context.getResources().getDimensionPixelSize(sizeId);
        return size;
    }

    /***
     * 将sp转换为像素
     *
     * @param context 关联
     * @param spId    spid
     * @return
     */
    public static int getSpAsPx(Context context, int spId) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (context.getResources().getDimension(spId) / fontScale + 0.5f);
    }

    /***
     * 将dp转换为px
     *
     * @param context 关联
     * @param dpId    dpid
     * @return
     */
    public static int getDpAsPx(Context context, int dpId) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (context.getResources().getDimension(dpId) * scale + 0.5f);
    }

    /***
     * 获取版本号
     *
     * @return
     */
    public static String getPackageVersion(Context context) {
        String versionNo = null;
        try {
            PackageManager pManager = context.getPackageManager();
            PackageInfo pInfo = pManager.getPackageInfo(
                    context.getPackageName(), 0);
            versionNo = pInfo.versionName;
        } catch (Exception e) {
            Log.e("VersionUpdate", "getPackageVersion fill");
        }
        return versionNo;
    }


    /***
     * 给TextView 设置下划线
     *
     * @param textView
     */
    public static void setTextViewUnderLine(TextView textView) {
        textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); // 下划线
        textView.getPaint().setAntiAlias(true);// 抗锯齿
    }

    public static void setTextViewMiddleLine(TextView textView) {
        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); // 中间横线
        textView.getPaint().setAntiAlias(true);// 抗锯齿
    }

    /***
     * 设置EditText最大字符数量
     *
     * @param edInput 输入框
     * @param num     输入数目
     */
    public static void setInputCount(EditText edInput, int num) {
        edInput.setFilters(new InputFilter[]{new InputFilter.LengthFilter(num)});
    }

    public static void setInputCount(TextView vInput, int num) {
        vInput.setFilters(new InputFilter[]{new InputFilter.LengthFilter(num)});
    }

    public static void setEditCusor(final EditText edInput) {
        edInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    edInput.setSelection(edInput.getText().toString().length());
                }
            }
        });
    }

    /***
     * 获取版本名称
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        PackageInfo pi = null;
        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return pi.versionName;
    }

    /**
     * 规则1：至少包含大小写字母及数字中的一种
     * 是否包含
     *
     * @param str
     * @return
     */
    public static boolean isLetterOrDigit(String str) {
        boolean isLetterOrDigit = false;//定义一个boolean值，用来表示是否包含字母或数字
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLetterOrDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isLetterOrDigit = true;
            }
        }
        String regex = "^[a-zA-Z0-9]+$";
        boolean isRight = isLetterOrDigit && str.matches(regex);
        return isRight;
    }

    /**
     * 规则2：至少包含大小写字母及数字中的两种
     * 是否包含
     *
     * @param str
     * @return
     */
    public static boolean isLetterDigit(String str) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            } else if (Character.isLetter(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
                isLetter = true;
            }
        }
        String regex = "^[a-zA-Z0-9]+$";
        boolean isRight = isDigit && isLetter && str.matches(regex);
        return isRight;
    }

    /**
     * 规则3：必须同时包含大小写字母及数字
     * 是否包含
     *
     * @param str
     * @return
     */
    public static boolean isContainAll(String str) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLowerCase = false;//定义一个boolean值，用来表示是否包含字母
        boolean isUpperCase = false;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            } else if (Character.isLowerCase(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
                isLowerCase = true;
            } else if (Character.isUpperCase(str.charAt(i))) {
                isUpperCase = true;
            }
        }
        String regex = "^[a-zA-Z0-9]+$";
        boolean isRight = isDigit && isLowerCase && isUpperCase && str.matches(regex);
        return isRight;
    }

    /**
     * 判断EditText输入的数字、中文还是字母方法
     */
    public static void whatIsInput(Context context, EditText edInput) {
        String txt = edInput.getText().toString();

        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(txt);
        if (m.matches()) {
            Toast.makeText(context, "输入的是数字", Toast.LENGTH_SHORT).show();
        }
        p = Pattern.compile("[a-zA-Z]");
        m = p.matcher(txt);
        if (m.matches()) {
            Toast.makeText(context, "输入的是字母", Toast.LENGTH_SHORT).show();
        }
        p = Pattern.compile("[\u4e00-\u9fa5]");
        m = p.matcher(txt);
        if (m.matches()) {
            Toast.makeText(context, "输入的是汉字", Toast.LENGTH_SHORT).show();
        }
    }

    //imageView.setImageBitmap(ImageUtil.createVideoThumbnail(urlPath,MediaStore.Images.Thumbnails.MINI_KIND));
    public static Bitmap createVideoThumbnail(String filePath, int kind) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            if (filePath.startsWith("http://")
                    || filePath.startsWith("https://")
                    || filePath.startsWith("widevine://")) {
                retriever.setDataSource(filePath, new Hashtable<String, String>());
            } else {
                retriever.setDataSource(filePath);
            }
            bitmap = retriever.getFrameAtTime(0, MediaMetadataRetriever.OPTION_CLOSEST_SYNC); //retriever.getFrameAtTime(-1);
        } catch (IllegalArgumentException ex) {
            // Assume this is a corrupt video file
            ex.printStackTrace();
        } catch (RuntimeException ex) {
            // Assume this is a corrupt video file.
            ex.printStackTrace();
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException ex) {
                // Ignore failures while cleaning up.
                ex.printStackTrace();
            }
        }

        if (bitmap == null) {
            return null;
        }

        if (kind == MediaStore.Images.Thumbnails.MINI_KIND) {//压缩图片 开始处
            // Scale down the bitmap if it's too large.
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int max = Math.max(width, height);
            if (max > 512) {
                float scale = 512f / max;
                int w = Math.round(scale * width);
                int h = Math.round(scale * height);
                bitmap = Bitmap.createScaledBitmap(bitmap, w, h, true);
            }//压缩图片 结束处
        } else if (kind == MediaStore.Images.Thumbnails.MICRO_KIND) {
            bitmap = ThumbnailUtils.extractThumbnail(bitmap,
                    96,
                    96,
                    ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        }
        return bitmap;
    }
}
