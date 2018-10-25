package com.project.my.studystarteacher.newteacher.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by sufen.liu on 2016/1/2.
 */
@SuppressLint("AppCompatCustomView")
public class TimerTextView extends TextView {
    public static final long CURRENT_TIME = 50;
    private MyCountDownTimer md;

    public TimerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    private boolean run = false; //是否启动了
    public void setTimes(long times) {
        if (md == null) {
            md = new MyCountDownTimer(this, times, CURRENT_TIME);
            md.start();
        }else{
            md.setmMillisInFuture(times);
            md.start();
        }
    }

    public boolean isRun() {
        return run;
    }

    public void beginRun() {
        this.run = true;
        md.start();
    }

    public void stopRun() {
        this.run = false;
        md.cancel();
    }


    private OnTimeEndListener mListener;

    public void setOnTimeEndListener(OnTimeEndListener mListener) {
        this.mListener = mListener;
    }

    public interface OnTimeEndListener {
        void onTimeEnd();
    }

    //00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000
    class MyCountDownTimer extends CountDownTimer {
        private final TextView down;
        private final long millisInFuture;
        private final long countDownInterval;

        /**
         * @param millisInFuture    表示以毫秒为单位 倒计时的总数
         *                          <p/>
         *                          例如 millisInFuture=1000 表示1秒
         * @param countDownInterval 表示 间隔 多少微秒 调用一次 onTick 方法
         *                          <p/>
         *                          例如: countDownInterval =1000 ; 表示每1000毫秒调用一次onTick()
         */
        public MyCountDownTimer(TextView down, long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            this.down = down;
            this.millisInFuture = millisInFuture;
            this.countDownInterval = countDownInterval;
        }

        @Override
        public void onFinish() {
            if (mListener != null)
                mListener.onTimeEnd();
        }

        /**
         * 处理时间倒计时进行页面刷新
         */
        @Override
        public void onTick(int year, int month, int day, String hour, String mmin, String msecond, int millisecond) {
            if (down != null)
                down.setText(hour + "时" + mmin + " 分 " + msecond + "秒" + millisecond);
        }
    }
}
