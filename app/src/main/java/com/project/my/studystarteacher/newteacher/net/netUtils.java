package com.project.my.studystarteacher.newteacher.net;

import android.content.Context;

import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;

/**
 * Created by hasee on 2018/5/17.
 */

public class netUtils {
    public static void NetMoban(final Context mContext, final netCallBlack netCallBlack) {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                netCallBlack.getData(baseBean);
            }
            @Override
            public void onFailed(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean, int failedType) {
            }
        });
      //  Worker.selectDefaultWords();
    }

//    public static void NetCity(final Context mContext, final getString netCallBlack) {
//        MiceNetWorker Worker = new MiceNetWorker(mContext);
//        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
//            @Override
//            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
//                super.onSuccess(netWorker, netTask, baseBean);
//                netCallBlack.getData((String) baseBean.getInfo());
//                SharedPreferencesUtil.save(mContext, "city_list", (String) baseBean.getInfo());
//            }
//        });
//        if (SharedPreferencesUtil.get(mContext, "city_list") != null) {
//            String city_list = SharedPreferencesUtil.get(mContext, "city_list");
//            netCallBlack.getData(city_list);
//        } else {
//            Worker.selectCity();
//        }
//
//    }

}
