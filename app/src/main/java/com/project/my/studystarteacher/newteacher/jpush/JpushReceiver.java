package com.project.my.studystarteacher.newteacher.jpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.project.my.studystarteacher.newteacher.common.UserSingleton;
import com.zhouqiang.framework.util.Logger;


import cn.jpush.android.api.JPushInterface;


/**
 * ZhouQiang
 * 自定义接收器
 * <p>
 * 如果不定义这个 Receiver，则： 1) 默认用户会打开主界面 2) 接收不到自定义消息
 */
public class JpushReceiver extends BroadcastReceiver {
    private static final String TAG = "JPush";
    private Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.mContext = context;
        Bundle bundle = intent.getExtras();
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle
                    .getString(JPushInterface.EXTRA_REGISTRATION_ID);
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent
                .getAction())) {
            String content = bundle.getString(JPushInterface.EXTRA_MESSAGE);
            String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
            System.out.println("收到了自定义消息内容是:" + content);
            Log.d(TAG, "收到了自定义消息extra是:" + extra);

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent
                .getAction())) {

            if (UserSingleton.getInstance().getSysUser() != null) {
            }

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent
                .getAction())) {
            receivingNotification(context, bundle);
            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
            String contents = bundle.getString(JPushInterface.EXTRA_MESSAGE);
            Logger.d(":::" + extras);
            try {
                System.out.println("打开了通知" + extras);
//                if (UserSingleton.getInstance().isLogin()) {

                //	resultAnalysis(Integer.parseInt(js.getCategory()), js.getContent(),mContext);
//                } else {
//                    Intent intent1 = new Intent(mContext, HomeActivity.class);
//                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    mContext.startActivity(intent1);
//                }
            } catch (Exception e1) {
                Logger.d("jp:" + e1.getMessage());
                e1.printStackTrace();
            }
        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent
                .getAction())) {
            Log.d(TAG,
                    "[MyReceiver] 用户收到到RICH PUSH CALLBACK: "
                            + bundle.getString(JPushInterface.EXTRA_EXTRA));
            // 在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity，
            // 打开一个网页等..

        } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent
                .getAction())) {
            boolean connected = intent.getBooleanExtra(
                    JPushInterface.EXTRA_CONNECTION_CHANGE, false);
            Log.w(TAG, "[MyReceiver]" + intent.getAction()
                    + " connected state change to " + connected);
        } else {
            Log.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
        }
    }

    private void receivingNotification(Context context, Bundle bundle) {
        String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        Logger.d(" title : " + title);
        String message = bundle.getString(JPushInterface.EXTRA_ALERT);
        Logger.d("message : " + message);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        Logger.d("extras : " + extras);
//        Intent intent = new Intent(mContext, ContentBasicActivity.class);
//        intent.putExtra(ContentBasicActivity.TITLE, title);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.putExtra(ContentBasicActivity.CONTENT, message);
//        mContext.startActivity(intent);
    }


    /**
     * 解析推送数据
     *
     * @param type    1:系统公告 2:申请代理人通过 3:申请代理人拒绝 4:申请的客户通过 5:申请的客户拒绝
     * @param context
     */
    // 用户类型
//	private RoleTypeEnum roleTypeEnum;
    private void resultAnalysis(int type, String content, Context context) {
        switch (type) {
        }
    }
}
