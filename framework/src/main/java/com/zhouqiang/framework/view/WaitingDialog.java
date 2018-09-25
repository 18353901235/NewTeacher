package com.zhouqiang.framework.view;

import android.app.Dialog;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.zhouqiang.framework.R;


/***
 * 等待提示
 */
public class WaitingDialog extends Dialog {

    /**
     * 关联
     */
    private Context context;
    /**
     * 进度
     */
    private TextView image;
    /**
     * 动画
     */
    private Animation anim;

    public WaitingDialog(Context context) {
        super(context, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setContentView(R.layout.common_waiting_layout);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        this.context = context;
        image = (TextView) findViewById(R.id.waiting_img);
    }

    /**
     * 显示
     */
    public void show() {
        try {
            anim = AnimationUtils.loadAnimation(getContext(), R.anim.common_progresscrl);
            anim.setDuration(1500);
            image.startAnimation(anim);
            anim.startNow();
            super.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 消失
     */
    public void dismiss() {
        try {
            image.clearAnimation();
            super.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
