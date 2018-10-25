package com.zhouqiang.framework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.TextView;

import com.zhouqiang.framework.image.ImageCache;
import com.zhouqiang.framework.image.ImageWorker;
import com.zhouqiang.framework.util.BaseUtil;
import com.zhouqiang.framework.util.Logger;
import com.zhouqiang.framework.util.ToastUtil;

/**
 * 该框架对Activity的拓展
 */
public abstract class SanmiActivity extends AppCompatActivity {
    /**
     * 打印TAG，类名
     */
    private String TAG = getClass().getSimpleName();
    /**
     * 是否已被销毁
     */
    protected boolean isDestroyed = false;
    /**
     * 上下文对象，等同于this
     */
    protected Activity mContext;
    /**
     * 下载图片使用
     */
    public ImageWorker imageWorker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseActivityManager.add(this);
        mContext = this;
        imageWorker = new ImageWorker(mContext);
        init(savedInstanceState);
    }


    @Override
    protected void onDestroy() {
        destroy();
        super.onDestroy();
        recyclePics();// 回收图片
    }

    private void destroy() {
        isDestroyed = true;
        BaseActivityManager.remove(this);
        if (imageWorker != null)
            imageWorker.clearTasks();// 取消图片下载任务
        ToastUtil.cancelAllToast();
    }

    @Override
    public void finish() {
        destroy();
        super.finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (onKeyBack())
                    return true;
                else
                    return super.onKeyDown(keyCode, event);
            case KeyEvent.KEYCODE_MENU:
                if (onKeyMenu())
                    return true;
                else
                    return super.onKeyDown(keyCode, event);
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    // 初始化三部曲
    private void init(Bundle savedInstanceState) {
//        getExras();
//        findView();
//        setListener();
    }


    /**
     * 返回键拦截,若需要拦截返回true否则false
     */
    protected abstract boolean onKeyBack();

    /**
     * 菜单键拦截,若需要拦截返回true否则false
     */
    protected abstract boolean onKeyMenu();

//    /**
//     * 初始化三部曲之：查找控件
//     */
//    protected abstract void findView();
//
//    /**
//     * 初始化三部曲之：获取传参
//     */
//    protected abstract void getExras();
//
//    /**
//     * 初始化三部曲之：设置监听
//     */
//    protected abstract void setListener();

    /**
     * 打印v级别信息
     *
     * @param msg
     */
    protected void log_v(String msg) {
        Logger.v(msg);
    }

    /**
     * 打印d级别信息
     *
     * @param msg
     */
    protected void log_d(String msg) {
        Logger.d(msg);
    }

    /**
     * 打印i级别信息
     *
     * @param msg
     */
    protected void log_i(String msg) {
        Logger.i(msg);
    }

    /**
     * 打印w级别信息
     *
     * @param msg
     */
    protected void log_w(String msg) {
        Logger.w(msg);
    }

    /**
     * 打印e级别信息
     *
     * @param msg
     */
    protected void log_e(String msg) {
        Logger.e(msg);
    }

    /**
     * 打印
     *
     * @param msg
     */
    protected void println(Object msg) {
        Logger.println(msg);
    }

    /**
     * 是否已被销毁
     */
    public boolean isDestroyed() {
        return isDestroyed;
    }


    // 回收图片
    private void recyclePics() {
        ImageCache.getInstance(this).reMoveCacheInMemByObj(this);
        ImageCache.getInstance(this).recyclePics();
    }

    /**
     * 关闭Activity
     *
     * @param enterAnim 进入Activity的动画,若没有传0即可
     * @param exitAnim  退出Activity的动画,若没有传0即可
     */
    public void finish(int enterAnim, int exitAnim) {
        finish();
        overridePendingTransition(enterAnim, exitAnim);
    }

    /**
     * @param enterAnim 进入Activity的动画,若没有传0即可
     * @param exitAnim  退出Activity的动画,若没有传0即可
     */
    public void startActivityForResult(Intent intent, int requestCode,
                                       int enterAnim, int exitAnim) {
        startActivityForResult(intent, requestCode);
        if (getParent() != null)
            getParent().overridePendingTransition(enterAnim, exitAnim);
        else
            overridePendingTransition(enterAnim, exitAnim);
    }

    /**
     * @param enterAnim 进入Activity的动画,若没有传0即可
     * @param exitAnim  退出Activity的动画,若没有传0即可
     */
    public void startActivity(Intent intent, int enterAnim, int exitAnim) {
        startActivity(intent);
        if (getParent() != null)
            getParent().overridePendingTransition(enterAnim, exitAnim);
        else
            overridePendingTransition(enterAnim, exitAnim);
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return true如果该字符串为null或者"",否则false
     */
    protected boolean isNull(String str) {
        return BaseUtil.isNull(str);
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return true如果该字符串为null或者"",否则false
     */
    protected boolean isNull(TextView str, String errMsg) {
        String trim = str.getText().toString().trim();
        boolean aNull = BaseUtil.isNull(trim);
        if (aNull) {
            str.setError(errMsg);
            str.requestFocus();
        }
        return aNull;
    }
}
