package com.project.my.studystarteacher.newteacher.utils;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by jia-changyu on 2016/3/30.
 */
public class CountdownUtility {
    private Context context;
    private int hour;
    private int minute;
    private int second;
    /**
     * 倒计时线程
     */
    private CountDownThread countDownThread;

    public CountdownUtility(Context context, int hour, int minute, int second) {
        this.context = context;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public void starCountDown(CountCallBack countCallBack) {
        if (countDownThread != null) {
            countDownThread.cancel();
        }
        countDownThread = new CountDownThread(new TimeHandler(countCallBack), hour, minute, second);
        countDownThread.start();
        countCallBack.CountStart();
    }

    /***
     * 取消线程
     */
    public void cancelCountDown(){
        if(countDownThread !=null){
            countDownThread.cancel();
            countDownThread =null;
        }
    }

    private class CountDownThread extends Thread {
        private TimeHandler timeHandler;
        private int hour;
        private int minute;
        private int second;
        private Bundle bundle;

        public CountDownThread(TimeHandler timeHandler, int hour, int minute, int second) {
            this.timeHandler = timeHandler;
            this.hour = hour;
            this.minute = minute;
            this.second = second;
            bundle = new Bundle();
        }

        public void cancel() {
            hour = 0;
            minute = 0;
            second = 0;
        }

        @Override
        public void run() {
            Looper.prepare();
            while (second > 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                if (second == 1) {
                    if (minute != 0) {
                        minute -= 1;
                        second = 59;
                    } else {
                        if (hour > 0) {
                            minute = 59;
                            second = 59;
                            hour -= 1;
                        } else {
                            second = 0;
                        }
                    }
                } else {
                    second -= 1;
                }
                bundle.clear();
                bundle.putInt("hour", hour);
                bundle.putInt("minute", minute);
                bundle.putInt("second", second);
                Message message = new Message();
                message.setData(bundle);
                message.what = 1;
                timeHandler.sendMessage(message);
            }
        }
    }

    private class TimeHandler extends Handler {
        /**
         * 回调
         */
        CountCallBack countCallBack;

        public TimeHandler(CountCallBack countCallBack) {
            this.countCallBack = countCallBack;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Bundle bundle = msg.getData();
                    int hour = bundle.getInt("hour", 0);
                    int minute = bundle.getInt("minute", 0);
                    int second = bundle.getInt("second", 0);
                    if (hour == 0 && minute == 0 && second == 0) {
                        countCallBack.CountFinish();
                    } else {
                        String sHour = "";
                        if (hour < 10) {
                            sHour = new StringBuffer("0").append(hour).toString();
                        } else {
                            sHour = String.valueOf(hour);
                        }
                        String sMinute = "";
                        if (minute < 10) {
                            sMinute = new StringBuffer("0").append(minute).toString();
                        } else {
                            sMinute = String.valueOf(minute);
                        }
                        String sSecond = "";
                        if (second < 10) {
                            sSecond = new StringBuffer("0").append(second).toString();
                        } else {
                            sSecond = String.valueOf(second);
                        }
                        countCallBack.CountDuring(sHour, sMinute, sSecond);
                    }
                    break;
            }
        }
    }

    public interface CountCallBack {
        /**
         * 倒计时中
         */
        public void CountDuring(String hour, String minute, String second);

        /**
         * 倒计时开始
         */
        public void CountStart();

        /**
         * 倒计时结束
         */
        public void CountFinish();
    }

}
