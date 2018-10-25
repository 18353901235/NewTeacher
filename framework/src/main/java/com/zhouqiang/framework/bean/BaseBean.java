package com.zhouqiang.framework.bean;


import com.zhouqiang.framework.BaseObject;

import java.io.Serializable;

/**
 * 服务端返回数据基类
 */

public class BaseBean extends BaseObject implements Serializable {
    private String status;// 服务器处理状态 ： 0：出错 1:成功
    private String msg;// 服务器返回的描述信息 : 错误信息：status：0 此字段有效
    private String errorCode;// 服务器返回的错误编码 : 错误信息：status：0 此字段有效
    private String token;
    private Object info;
    private Object data;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setInfo(Object info) {
        this.data = info;
    }

    public Object getInfo() {
        return data;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", token='" + token + '\'' +
                ", info=" + info +
                "} ";
    }
}
