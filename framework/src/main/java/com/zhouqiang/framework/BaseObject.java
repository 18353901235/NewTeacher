package com.zhouqiang.framework;

import com.zhouqiang.framework.util.BaseUtil;
import com.zhouqiang.framework.util.Logger;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * 相当于Object，集成了log_v(msg)等打印方法以及println(Object)。
 */
public class BaseObject {
	/**
	 * 打印TAG，类名
	 */
	private String TAG = getClass().getSimpleName();

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
	 * 解析时，判断是否为空
	 * 
	 * @param jsonObject
	 * @param s
	 * @return
	 * @throws JSONException
	 */
	protected String get(JSONObject jsonObject, String s) throws JSONException {
		if (!jsonObject.isNull(s)) {
			return jsonObject.getString(s);
		}
		return null;
	}

	/**
	 * 解析时，判断是否为空
	 * 
	 * @param jsonObject
	 * @param s
	 * @return 若为空返回0
	 * @throws JSONException
	 */
	protected int getInt(JSONObject jsonObject, String s) throws JSONException {
		if (!jsonObject.isNull(s)) {
			return jsonObject.getInt(s);
		}
		return 0;
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
