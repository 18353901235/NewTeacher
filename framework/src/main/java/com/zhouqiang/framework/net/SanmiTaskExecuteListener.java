package com.zhouqiang.framework.net;

import android.content.Context;

import com.zhouqiang.framework.BaseObject;
import com.zhouqiang.framework.SanmiActivity;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.util.ToastUtil;


public abstract class SanmiTaskExecuteListener extends BaseObject implements
        NetWorker.OnTaskExecuteListener {
    public Context Context;

    public SanmiTaskExecuteListener(Context context) {
        Context = context;
    }

    private boolean isActivityDestroyed() {
        return Context != null && Context instanceof SanmiActivity && ((SanmiActivity) Context).isDestroyed();
    }

    @Override
    public void onPreExecute(NetWorker netWorker, NetTask task) {
        if (isActivityDestroyed()) return;
        onPreExecute((SanmiNetWorker) netWorker, (SanmiNetTask) task);
    }

    @Override
    public void onPostExecute(NetWorker netWorker, NetTask task) {
        if (isActivityDestroyed()) return;
        onPostExecute((SanmiNetWorker) netWorker, (SanmiNetTask) task);
    }

    @Override
    public void onExecuteFailed(NetWorker netWorker, NetTask netTask,
                                int failedType) {
        if (isActivityDestroyed()) return;
        onFailed((SanmiNetWorker) netWorker,
                (SanmiNetTask) netTask, null, failedType);

    }

    @Override
    public void onExecuteSuccess(NetWorker worker, NetTask task,
                                 Object result) {
        if (isActivityDestroyed()) return;
        BaseBean baseResult = (BaseBean) result;
        SanmiNetTask netTask = (SanmiNetTask) task;
        SanmiNetWorker netWorker = (SanmiNetWorker) worker;
        if ("1".equals(baseResult.getStatus())) {// 服务器处理成功
            onSuccess(netWorker, netTask, baseResult);
        } else {// 服务器处理失败
            onFailed(netWorker, netTask, baseResult, 0);
        }
    }

    /**
     * Runs on the UI thread before the task run.
     *
     * @param netWorker
     * @param netTask
     */
    public abstract void onPreExecute(SanmiNetWorker netWorker,
                                      SanmiNetTask netTask);

    /**
     * Runs on the UI thread after the task run.
     *
     * @param netWorker
     * @param netTask
     */
    public abstract void onPostExecute(SanmiNetWorker netWorker,
                                       SanmiNetTask netTask);

    /**
     * 服务器处理成功
     *
     * @param netWorker
     * @param netTask
     * @param baseBean
     */
    public abstract void onSuccess(SanmiNetWorker netWorker,
                                   SanmiNetTask netTask, BaseBean baseBean);

    /**
     * Runs on the UI thread when the task run failed.
     *
     * @param netWorker
     * @param netTask
     * @param baseBean
     * @param failedType the type of cause the task failed.
     *                   <p>
     *                   See {@link NetWorker#FAILED_DATAPARSE
     *                   NetWorker.FAILED_DATAPARSE},
     *                   {@link NetWorker#FAILED_HTTP NetWorker.FAILED_HTTP},
     *                   {@link NetWorker#FAILED_NONETWORK
     *                   NetWorker.FAILED_NONETWORK}
     *                   </p>
     */
    public void onFailed(SanmiNetWorker netWorker,
                         SanmiNetTask netTask, BaseBean baseBean, int failedType) {
        if (baseBean != null) {//服务器处理失败
            ToastUtil.showShortToast(Context, baseBean.getMsg());
        } else {//请求失败
            switch (failedType) {
                case NetWorker.FAILED_DATAPARSE:
                    ToastUtil.showShortToast(Context, "数据异常");
                    break;
                case NetWorker.FAILED_HTTP:
                    ToastUtil.showShortToast(Context, "请求超时");
                    break;
                case NetWorker.FAILED_NONETWORK:
                    ToastUtil.showShortToast(Context, "没有网络");
                    break;
            }
        }
    }
}
