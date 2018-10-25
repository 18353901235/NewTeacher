package com.project.my.studystarteacher.newteacher.net;


import com.zhouqiang.framework.net.SanmiNetTask;

import java.util.HashMap;

/**
 * 网络请求任务
 */
public class DemoNetTask<T> extends SanmiNetTask {
    /**
     * 实例化网络请求任务
     *
     * @param information 网络请求信息
     * @param params      任务参数集(参数名,参数值)
     */
    public DemoNetTask(DemoHttpInformation information,
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
    public DemoNetTask(DemoHttpInformation information,
                       HashMap<String, String> params, HashMap<String, String> files, Class<T> clazz) {
        super(information, params, files, clazz);
//        //加密逻辑
//        HashMap<String, String> encodeHashMap = null;
//        if (params != null) {
//            Logger.d("请求数据：" + params.toString());
//            encodeHashMap = new HashMap<>();
//            //  encodeHashMap.put("token", params.get("token"));
//            if (UserSingleton.isLogin()) {
//                params.put("token", URLEncoder.encode(SharedPreferencesUtil.get(MiceApplication.getInstance(), ProjectConstant.TOKEN)));
//            }
//
//            //----------------------------删除城市ID
////            if (ProjectConstant.ISREMOVECITYID && information != DemoHttpInformation.UPDATE && information != DemoHttpInformation.SELECTSCENICLIST) {
////                if (params.containsKey("provinceId") || params.containsKey("cityId")) {
////                    params.remove("provinceId");
////                    params.remove("cityId");
////                }
////            }
//            Logger.d("最终发送数据：：" + params.toString());
//            String paramsJson = JsonUtil.BeanToJson(params);
//            //   if (information == DemoHttpInformation.UPDATEICON || information == DemoHttpInformation.INSERTCOMMENTS) {
//            if (null == files) {
//                encodeHashMap.put("data", URLEncoder.encode(RSAUtils.encryptByPrivateKey(paramsJson)));
//            } else {
//                encodeHashMap.put("data", RSAUtils.encryptByPrivateKey(paramsJson));
//            }
////            } else {
////                encodeHashMap.put("data", URLEncoder.encode(RSAUtils.encryptByPrivateKey(paramsJson)));
////            }
//            if (information != DemoHttpInformation.JINGQUDAOHANG) {
//                setParams(encodeHashMap);
//            }
//
//        }

    }


    @Override
    public DemoHttpInformation getHttpInformation() {
        return (DemoHttpInformation) super.getHttpInformation();
    }

}
