package com.zhouqiang.framework.view;

import android.content.Context;

/**
 * 等待对话框
 */
public class WaitingDialogControll {
    /** 等待对话框 **/
    private static WaitingDialog waitingDialog;

	/**
     * 显示等待对话框
     */
    public static void showWaitingDialog(Context context) {

        if (waitingDialog == null) {
            waitingDialog = new WaitingDialog(context);
            waitingDialog.show();
        }
    }

    /**
     * 隐藏等待对话框
     */
    public static void dismissWaitingDialog() {

    	if (waitingDialog != null) {
            waitingDialog.dismiss();
            waitingDialog = null;	
    	}
    }
}
