package com.project.my.studystarteacher.newteacher.utils;

import android.content.Context;
import android.widget.Toast;

import com.project.my.studystarteacher.newteacher.MiceApplication;


/**
 * Created by ZhouQiang on 2016/2/5.
 */
public class ToastUtility {
    private static Toast mToast;
    private static Context mContext;


    public static void showToast(Context context ,String text) {
        mContext = context;
        if (mToast == null) {
            mToast = Toast.makeText(MiceApplication.mContext, text, Toast.LENGTH_SHORT);
            mToast.show();
        }else{
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.show();
        }
    }

    public static void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
