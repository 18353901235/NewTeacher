package com.zhouqiang.framework.net.okHttpUtils;

import com.zhouqiang.framework.net.SanmiHttpInfomation;

import java.util.Map;

/**
 * Created by hasee on 2018/5/13.
 */

public class okUtils {
    public static void sentPost(SanmiHttpInfomation url, Map<String, String> map, TaskCallback Callback) {
        okHttpUtils.getInstance().postOkhttp(url.getUrlPath(), map, Callback);
    }

    public static void sentPost(String url, Map<String, String> map, TaskCallback Callback) {
        okHttpUtils.getInstance().postOkhttp(url, map, Callback);
    }
}
