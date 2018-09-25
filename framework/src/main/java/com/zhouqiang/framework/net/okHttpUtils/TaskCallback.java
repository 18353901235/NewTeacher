package com.zhouqiang.framework.net.okHttpUtils;

import com.zhouqiang.framework.exception.HttpException;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by hasee on 2018/5/11.
 */

public class TaskCallback implements TaskLoadCallback {

    @Override
    public void pre() {

    }

    @Override
    public void onFailure(IOException e) throws HttpException, IOException {

    }

    @Override
    public void onEnd() {

    }

    @Override
    public void onSucceed(Response response) {

    }

    @Override
    public void getLoad(String s) {

    }
}
