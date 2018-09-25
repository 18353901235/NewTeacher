package com.zhouqiang.framework;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.zhouqiang.framework.image.ImageCache;
import com.zhouqiang.framework.image.ImageWorker;
import com.zhouqiang.framework.util.BaseUtil;
import com.zhouqiang.framework.util.Logger;
import com.zhouqiang.framework.util.ToastUtil;

import java.util.List;

/**
 * 该框架对Fragment的拓展
 */
public abstract class SanmiFragment extends Fragment {

    /**
     * 打印TAG，类名
     */
    private String TAG = getClass().getSimpleName();
    /**
     * 下载图片使用
     */
    public ImageWorker imageWorker;
    /**
     * 获取传参使用
     */
    protected Intent mIntent;
    /**
     * 根view
     */
    protected View rootView;

    /**
     * 根view id
     */
    private int rootViewId;

    private static Fragment currForResultFragment;


    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        if (currForResultFragment == null)
            currForResultFragment = this;
        if (getParentFragment() != null)
            getParentFragment().startActivityForResult(intent, requestCode);
        else
            super.startActivityForResult(intent, requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (null == currForResultFragment || currForResultFragment.equals(this))
            super.onActivityResult(requestCode, resultCode, data);
        else {
            currForResultFragment.onActivityResult(requestCode, resultCode,
                    data);
        }
        currForResultFragment = null;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        List<Fragment> fragments = getChildFragmentManager().getFragments();
        if (fragments != null)
            for (Fragment fragment : fragments) {
                fragment.onHiddenChanged(hidden);
            }
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (imageWorker != null)
            imageWorker.clearTasks();// 取消图片下载任务
        recyclePics();// 回收图片
        ToastUtil.cancelAllToast();
    }


    // 初始化
    protected abstract void init();

    /**
     * 设置根view
     *
     * @param layoutResID
     */
    public void setContentView(int layoutResID) {
        rootViewId = layoutResID;
    }

    /**
     * 设置根view
     *
     * @param v
     */
    public void setContentView(View v) {
        rootView = v;
    }

//
//    /**
//     * 初始化三部曲之：查找控件
//     */
//    protected abstract void findView();
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
        Logger.v( msg);
    }

    /**
     * 打印d级别信息
     *
     * @param msg
     */
    protected void log_d(String msg) {
        Logger.d( msg);
    }

    /**
     * 打印i级别信息
     *
     * @param msg
     */
    protected void log_i(String msg) {
        Logger.i( msg);
    }

    /**
     * 打印w级别信息
     *
     * @param msg
     */
    protected void log_w(String msg) {
        Logger.w( msg);
    }

    /**
     * 打印e级别信息
     *
     * @param msg
     */
    protected void log_e(String msg) {
        Logger.e( msg);
    }

    /**
     * 打印
     *
     * @param msg
     */
    protected void println(Object msg) {
        Logger.println(msg);
    }


    // 回收图片
    private void recyclePics() {
        ImageCache.get().reMoveCacheInMemByObj(this);
        ImageCache.get().recyclePics();
    }

    public View findViewById(int id) {
        View view = null;
        if (rootView != null)
            view = rootView.findViewById(id);
        return view;
    }

    /**
     * 显示或更换Fragment
     *
     * @param fragmentClass   Fragment.class
     * @param containerViewId Fragment显示的空间ID
     * @param replace         是否替换
     */
    public void toogleFragment(Class<? extends Fragment> fragmentClass,
                               int containerViewId, boolean replace) {
        FragmentManager manager = getChildFragmentManager();
        String tag = fragmentClass.getName();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = manager.findFragmentByTag(tag);

        if (fragment == null) {
            try {
                fragment = fragmentClass.newInstance();
                if (replace)
                    transaction.replace(containerViewId, fragment, tag);
                else
                    // 替换时保留Fragment,以便复用
                    transaction.add(containerViewId, fragment, tag);
            } catch (Exception e) {
                // ignore
            }
        } else {
            // nothing
        }
        // 遍历存在的Fragment,隐藏其他Fragment
        List<Fragment> fragments = manager.getFragments();
        if (fragments != null)
            for (Fragment fm : fragments)
                if (!fm.equals(fragment))
                    transaction.hide(fm);

        transaction.show(fragment);
        transaction.commit();
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
}