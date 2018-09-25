package com.zhouqiang.framework;

import android.app.Application;
import android.content.Context;

import com.zhouqiang.framework.util.Logger;


/**
 * 该项目自定义Application
 */
public class SanmiApplication extends Application {
    private static final String TAG = "SanmiApplication";

    private static SanmiApplication application;

    public static SanmiApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        application = this;
        BaseConfig.TIMEOUT_CONNECT_HTTP = SanmiConfig.TIMEOUT_HTTP;
        BaseConfig.TRYTIMES_HTTP = SanmiConfig.TRYTIMES_HTTP;
        super.onCreate();
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
    @Override
    public void onLowMemory() {
        Logger.i( "onLowMemory");
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        Logger.i( "onTerminate");
        super.onTerminate();
    }

}
