package com.zhouqiang.framework.net;

import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.exception.DataParseException;
import com.zhouqiang.framework.util.JsonUtil;
import com.zhouqiang.framework.util.JsonUtility;

import java.util.HashMap;


/**
 * 网络请求任务
 */
public class SanmiNetTask<T> extends NetTask {
    private SanmiHttpInfomation requestInformation;
    private Class<T> clazz;
    private boolean isShow = true;//等待框是否显示

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    /**
     * 实例化网络请求任务
     *
     * @param information 网络请求信息
     * @param params      任务参数集(参数名,参数值)
     */
    public SanmiNetTask(SanmiHttpInfomation information,
                        HashMap<String, String> params, Class<T> clazz) {
        this(information, params, null, clazz);
    }

    /**
     * 实例化网络请求任务
     *
     * @param information 网络请求信息
     * @param params      任务参数集(参数名,参数值)
     * @param files       任务文件集(参数名,文件的本地路径)
     */
    public SanmiNetTask(SanmiHttpInfomation information,
                        HashMap<String, String> params, HashMap<String,
            String> files, Class<T> clazz) {
        super(information.getId(), information.getUrlPath(), params, files,
                information.getDescription());
        this.requestInformation = information;
       /* if (clazz==null){
            this.clazz=String.class;
        }*/
        this.clazz = clazz;
    }

    /**
     * @return 网络请求信息
     */
    public SanmiHttpInfomation getHttpInformation() {
        return requestInformation;
    }

    @Override
    public BaseBean parse(String json) throws DataParseException {
        try {
            BaseBean baseBean = JsonUtil.fromBean(json, BaseBean.class);
            Object info = baseBean.getInfo();
            if (info != null) {
                String strInfo = info.toString().trim();
                if (clazz == null) {
                    info = strInfo;
                } else if (strInfo.startsWith("[")) {//info为数组类型
                    info = JsonUtil.fromList(strInfo, clazz);
                    // info= JsonUtility.fromList(strInfo, new TypeToken<T>(){});
                } else if (strInfo.startsWith("{")) {//info为对象类型
                    // info = JsonUtil.fromBean(strInfo, clazz);
                    info = JsonUtility.fromJson(strInfo, clazz);//使用Gson
                } else {
                    //nothing
                }
                baseBean.setInfo(info);
            }
            return baseBean;
        } catch (Exception e) {
            BaseBean baseBean = new BaseBean();
            baseBean.setMsg("解析出错");
            baseBean.setInfo(json);
            baseBean.setStatus("0");
            return baseBean;
            //     throw new DataParseException(e);
        }
    }
}
