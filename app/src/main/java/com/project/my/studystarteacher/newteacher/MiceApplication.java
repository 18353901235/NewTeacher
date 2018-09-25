package com.project.my.studystarteacher.newteacher;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.project.my.studystarteacher.newteacher.utils.LogUtils;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.zhouqiang.framework.BaseConfig;
import com.zhouqiang.framework.util.FileUtil;

import org.xutils.x;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;


/**
 * Created by sufen.liu on 2016/10/17.
 */
public class MiceApplication extends MultiDexApplication {
    private static final int SET_ALIAS_AND_TAGS = 0;
    private static final int SET_ADD_TAGS = 1;
    public static Context mContext;
    public static boolean havaAliasFlag = false;
    private static final String TAG = "JPush";
    private static MiceApplication instance;
    public static boolean isHOMEup = false;

    public static MiceApplication getInstance() {
        return instance;
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SET_ALIAS_AND_TAGS:
                    HashMap<String, String> map = (HashMap<String, String>) msg.obj;
                    String alias = map.get("alias");
                    JPushInterface.setAlias(getApplicationContext(), alias, new TagAliasCallback() {
                        @Override
                        public void gotResult(int i, String s, Set<String> set) {
                            String logs;
                            switch (i) {
                                case 0:
                                    logs = "Set tag and alias success";
                                    havaAliasFlag = true;
                                    Log.d(TAG, logs);
                                    break;
                                case 6002:
                                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                                    Log.d(TAG, logs);
                                    break;

                                default:
                                    logs = "Failed with errorCode = " + i;
                                    Log.e(TAG, logs);
                            }
                        }
                    });
                    break;
                case SET_ADD_TAGS:
                    HashSet<String> strings = (HashSet<String>) msg.obj;
                    JPushInterface.setTags(getApplicationContext(), strings, new TagAliasCallback() {
                        @Override
                        public void gotResult(int i, String s, Set<String> set) {
                            String logs;
                            switch (i) {
                                case 0:
                                    logs = "Set tag success";
                                    havaAliasFlag = true;
                                    Log.d(TAG, logs);
                                    break;
                                case 6002:
                                    logs = "Failed to set tags due to timeout. Try again after 60s.";
                                    Log.d(TAG, logs);
                                    break;

                                default:
                                    logs = "Failed with errorCode = " + i;
                                    Log.e(TAG, logs);
                            }
                        }
                    });
                    break;
                default:
                    break;
            }
        }
    };

    {
        //微信
        PlatformConfig.setWeixin("wxe40e4f716c4146ad", "735ba86051385fb2f396a2c659b967d7");
        //     腾讯
        PlatformConfig.setQQZone("1105675922", "ggvdqfH7SqsCHT4Q");
        PlatformConfig.setSinaWeibo("1918316740", "b8347bdf8f71f9c75c07ece175b4a72b", "http://sns.whalecloud.com");
        //    PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
//90ccaad3fc0332b4b0ab756b69912536
        // "62e9cdd7b6e06f791d2b0c2f45329e36b2f503d0"
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        UMShareAPI.get(this);
        UMConfigure.init(this, "5b029b8ba40fa355b40000de", "Umeng", UMConfigure.DEVICE_TYPE_PHONE,
                "669c30a9584623e70e8cd01b0381dcb4");
        UMConfigure.setLogEnabled(false);
        instance = this;
        mContext = this;
        //   initUser();
        BaseConfig.LOG = isDebug();

        //EMClient.getInstance().setDebugMode(true);
        //init极光推送
        JPushInterface.init(this);
        JPushInterface.setDebugMode(true);
        x.Ext.init(this);//Xutils初始化
        x.Ext.setDebug(BuildConfig.DEBUG);
        initImageLoader(this);
//        MiceApplication.getInstance().setAlias("al");
//        MiceApplication.getInstance().AddTags("test1");
//        MiceApplication.getInstance().AddTags("test2");
//        MiceApplication.getInstance().AddTags("test3");

    }

    private boolean isDebug() {
        if (LogUtils.isApkDebugable(this)) {
            return BaseConfig.LOG;
        }
        return false;
    }

    /**
     * 极光推送设置设备别名和标签
     *
     * @param alias 设备别名
     */
    public void setAlias(String alias) {
        Message msg = Message.obtain();
        msg.what = SET_ALIAS_AND_TAGS;
        HashMap<String, String> map = new HashMap<>();
        map.put("alias", alias);
        msg.obj = map;
        mHandler.sendMessage(msg);
    }

    HashSet<String> map;

    public void AddTags(String tags) {
        Message msg = Message.obtain();
        msg.what = SET_ADD_TAGS;
        //if (map == null)
        map = new HashSet<>();
        map.add(tags);
        msg.obj = map;
        mHandler.sendMessage(msg);
    }

    public void AddTags(String tags, String tags2) {
        Message msg = Message.obtain();
        msg.what = SET_ADD_TAGS;
        map = new HashSet<>();
        map.add(tags);
        map.add(tags2);
        msg.obj = map;
        mHandler.sendMessage(msg);
    }

    /**
     * ImageLoader 初始化
     *
     * @param context
     */
    public static void initImageLoader(Context context) {
        File fche = new File(FileUtil.getFileDir(context));
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context)
                .threadPoolSize(5)
                .threadPriority(Thread.MIN_PRIORITY + 3)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCacheSize(1024 * 1024 * 1024 * 5) // 你可以传入自己的内存缓存
                .discCache(new UnlimitedDiscCache(fche)) // 你可以传入自己的磁盘缓存
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .build();

        ImageLoader.getInstance().init(config);
    }
 /*   private void getCityData(String time) {
        MiceNetWorker netWorker = new MiceNetWorker(mContext);
        netWorker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {

            }
        });
        netWorker.getCityData(time);
    }*/
}
