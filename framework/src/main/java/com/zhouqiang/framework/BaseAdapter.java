package com.zhouqiang.framework;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.zhouqiang.framework.util.BaseUtil;
import com.zhouqiang.framework.util.Logger;

/**
 * 集成了一些工具方法
 */
public abstract class BaseAdapter extends android.widget.BaseAdapter {
	/**
	 * 打印TAG，类名
	 */
	private String TAG;
	protected Context mContext;
	protected Fragment mFragment;

	public BaseAdapter() {
		TAG = getLogTag();
	}

	public BaseAdapter(Context mContext) {
		this.mContext = mContext;
	}

	public BaseAdapter(Fragment mFragment) {
		this.mFragment = mFragment;
		this.mContext = mFragment.getActivity();
	}

	/**
	 * 获取打印TAG，即类名
	 * 
	 * @return
	 */
	private String getLogTag() {
		return getClass().getSimpleName();
	}

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
