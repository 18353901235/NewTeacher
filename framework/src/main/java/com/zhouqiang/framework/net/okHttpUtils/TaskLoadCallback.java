package com.zhouqiang.framework.net.okHttpUtils;

import com.zhouqiang.framework.exception.HttpException;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by hasee on 2018/5/11.
 */

public interface TaskLoadCallback {
    void pre();

    void onFailure(IOException e) throws HttpException, IOException;

    void onEnd();

    void onSucceed(Response response);

    void getLoad(String s);

}
