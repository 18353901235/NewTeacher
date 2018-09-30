package com.project.my.studystarteacher.newteacher.utils;

import android.app.Activity;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.project.my.studystarteacher.newteacher.R;
import com.zhouqiang.framework.util.Logger;
import com.zhouqiang.framework.util.WindowSize;

/**
 * Created by 全栈工程师 on 2018/5/9.
 */

public class PopWindowUtils {

    public static final int defaultBotom = -100;

    /**
     * @param activity      Activity
     * @param attachOnView  显示在这个View的下方
     * @param popView       被显示在PopupWindow上的View
     * @param popShowHeight 被显示在PopupWindow上的View的高度，可以传默认值defaultBotom
     * @param popShowWidth  被显示在PopupWindow上的View的宽度，一般是传attachOnView的getWidth()
     * @return PopupWindow
     */
    public static PopupWindow show(Activity activity, View attachOnView, View popView, final int popShowHeight, final int popShowWidth) {
        if (popView != null && popView.getParent() != null) {
            ((ViewGroup) popView.getParent()).removeAllViews();
        }

        PopupWindow popupWindow = null;
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);


        int x, y;
        int popHeight = 0, popWidth = 0;
        int location[] = new int[2];
        attachOnView.getLocationInWindow(location);
        x = location[0];
        y = location[1];

        int h = attachOnView.getHeight();
        int screenHeight = WindowSize.getWidth(activity);

        if (popShowHeight == defaultBotom) {
            popHeight = screenHeight / 6;
            popHeight = Math.abs(screenHeight - (h + y)) - popHeight;
        } else if (popHeight == ViewGroup.LayoutParams.WRAP_CONTENT) {
            popHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
        } else {
            popHeight = popShowHeight;
        }

        if (popShowWidth == ViewGroup.LayoutParams.WRAP_CONTENT) {
            popWidth = popView.getWidth();
            Logger.d("width:" + popWidth);
        } else {
            popWidth = popShowWidth;
        }

        popupWindow = new PopupWindow(popView, popWidth, popHeight, true);

        //这行代码时用来让PopupWindow点击区域之外消失的，这个应该是PopupWindow的一个bug。
        //但是利用这个bug可以做到点击区域外消失
        popupWindow.setBackgroundDrawable(new BitmapDrawable());


//        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
//        lp.alpha = 0.5f; //0.0-1.0
//        activity.getWindow().setAttributes(lp);


        popupWindow.setAnimationStyle(R.style.style_pop_animation);
        popupWindow.showAtLocation(attachOnView, Gravity.NO_GRAVITY, x, h + y);
        popupWindow.update();
        return popupWindow;
    }

    public static PopupWindow show(Activity activity, View attachOnView, View popView, final int popShowHeight, final int popShowWidth, int offHight, int offwiht) {
        if (popView != null && popView.getParent() != null) {
            ((ViewGroup) popView.getParent()).removeAllViews();
        }

        PopupWindow popupWindow = null;
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);

        int location[] = new int[2];
        int x, y;
        int popHeight = 0, popWidth = 0;

        attachOnView.getLocationOnScreen(location);
        x = location[0] + offwiht;
        y = location[1] + offHight;

        int h = attachOnView.getHeight();
        int screenHeight = WindowSize.getWidth(activity);

        if (popShowHeight == defaultBotom) {
            popHeight = screenHeight / 6;
            popHeight = Math.abs(screenHeight - (h + y)) - popHeight;
        } else if (popHeight == ViewGroup.LayoutParams.WRAP_CONTENT) {
            popHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
        } else {
            popHeight = popShowHeight;
        }

        if (popShowWidth == ViewGroup.LayoutParams.WRAP_CONTENT) {
            popWidth = attachOnView.getWidth();
        } else {
            popWidth = popShowWidth;
        }

        popupWindow = new PopupWindow(popView, popWidth, popHeight, true);

        //这行代码时用来让PopupWindow点击区域之外消失的，这个应该是PopupWindow的一个bug。
        //但是利用这个bug可以做到点击区域外消失
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        popupWindow.setAnimationStyle(R.style.style_pop_animation);
        popupWindow.showAtLocation(attachOnView, Gravity.NO_GRAVITY, x, h + y);
        popupWindow.update();
        return popupWindow;
    }
}
