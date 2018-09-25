package com.zhouqiang.framework.net;

/**
 * 请求信息抽象接口
 */
public interface SanmiHttpInfomation {

	/**
	 * @return 对应NetTask的id
	 */
	int getId();

	/**
	 * @return 请求地址
	 */
	String getUrlPath();

	/**
	 * @return 请求描述
	 */
	String getDescription();

	/**
	 * @return 是否是根路径
	 */
	boolean isRootPath();
}
