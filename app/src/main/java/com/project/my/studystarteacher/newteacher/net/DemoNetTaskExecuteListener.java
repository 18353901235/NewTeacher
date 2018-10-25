package com.project.my.studystarteacher.newteacher.net;

import android.content.Context;

import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.net.SanmiTaskExecuteListener;
import com.zhouqiang.framework.view.WaitingDialogControll;

/**
 * 网络任务执行监听
 */
public abstract class DemoNetTaskExecuteListener extends
        SanmiTaskExecuteListener {

    public DemoNetTaskExecuteListener(Context context) {
        super(context);
    }

    @Override
    public void onPreExecute(SanmiNetWorker netWorker, SanmiNetTask netTask) {
        if (!netTask.isShow()) {
            return;//不显示等待框
        }
        // 显示等待对话框
        /**/

        WaitingDialogControll.showWaitingDialog(Context);
    }

    @Override
    public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
       // Logger.d("訪問鏈接："+netTask.getHttpInformation().getDescription());
    }

    @Override
    public void onPostExecute(SanmiNetWorker netWorker, SanmiNetTask netTask) {
        WaitingDialogControll.dismissWaitingDialog();
    }


}
