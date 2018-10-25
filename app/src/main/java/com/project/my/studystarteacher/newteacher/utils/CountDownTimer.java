package com.project.my.studystarteacher.newteacher.utils;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

/**
 * 作者：周强
 *
 * Schedule a countdown until a time in the future, with
 * regular notifications on intervals along the way.
 * <p/>
 * Example of showing a 30 second countdown in a text field:
 * <p/>
 * <pre class="prettyprint">
 * new CountDownTimer(30000, 1000) {
 * <p/>
 * public void onTick(long millisUntilFinished) {
 * mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
 * }
 * <p/>
 * public void onFinish() {
 * mTextField.setText("done!");
 * }
 * }.start();
 * </pre>
 * <p/>
 * compared to the countdown interval.
 */
public abstract class CountDownTimer {
    /**
     * Millis since epoch when alarm should stop.
     */
    private long mMillisInFuture;

    /**
     * The interval in millis that the user receives callbacks
     */
    private final long mCountdownInterval;

    private long mStopTimeInFuture;

    /**
     * boolean representing if the timer was cancelled
     */
    private boolean mCancelled = false;

    public long getmMillisInFuture() {
        return mMillisInFuture;
    }

    public void setmMillisInFuture(long mMillisInFuture) {
        this.mMillisInFuture = mMillisInFuture;
    }

    /**
     */
    public CountDownTimer(long millisInFuture, long countDownInterval) {
        mMillisInFuture = millisInFuture;
        mCountdownInterval = countDownInterval;
    }

    /**
     * Cancel the countdown.
     */
    public synchronized final void cancel() {
        mCancelled = true;
        mHandler.removeMessages(MSG);
    }
    /**
     * Start the countdown.
     */
    public synchronized final CountDownTimer start() {
        mCancelled = false;
        if (mMillisInFuture <= 0) {
            onFinish();
            return this;
        }
        mStopTimeInFuture = SystemClock.elapsedRealtime() + mMillisInFuture;
        mHandler.sendMessage(mHandler.obtainMessage(MSG));
        return this;
    }

    public abstract void onTick(int year, int month, int day, String hour, String mmin, String msecond, int millisecond);

    public abstract void onFinish();


    private static final int MSG = 1;


    // handles counting down
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            synchronized (CountDownTimer.this) {
                if (mCancelled) {
                    return;
                }

                final long millisLeft = mStopTimeInFuture - SystemClock.elapsedRealtime();
                if (millisLeft <= 0) {
                    onFinish();
                } else if (millisLeft < mCountdownInterval) {
                    // no tick, just delay until done
                    sendMessageDelayed(obtainMessage(MSG), millisLeft);
                } else {
                    long lastTickStart = SystemClock.elapsedRealtime();
                    //--------------------------------------------
                    long yearLevelValue = 365 * 24 * 60 * 60 * 1000;
                    long monthLevelValue = 30 * 24 * 60 * 60 * 1000;
                    long dayLevelValue = 24 * 60 * 60 * 1000;
                    long hourLevelValue = 60 * 60 * 1000;
                    long minuteLevelValue = 60 * 1000;
                    long secondLevelValue = 1000;

                    int year = (int) (millisLeft / yearLevelValue);
                    int month = (int) ((millisLeft - year * yearLevelValue) / monthLevelValue);
                    int day = (int) ((millisLeft - year * yearLevelValue - month * monthLevelValue) / dayLevelValue);
                    int hour = (int) ((millisLeft - year * yearLevelValue - month * monthLevelValue - day *
                            dayLevelValue) / hourLevelValue);
                    int mmin = (int) ((millisLeft - year * yearLevelValue - month * monthLevelValue - day *
                            dayLevelValue - hour *
                            hourLevelValue) / minuteLevelValue);
                    int msecond = (int) ((millisLeft - year * yearLevelValue - month * monthLevelValue - day *
                            dayLevelValue - hour *
                            hourLevelValue - mmin * minuteLevelValue) / secondLevelValue);
                    int millisecond = (int) ((millisLeft - year * yearLevelValue - month * monthLevelValue - day *
                            dayLevelValue -
                            hour * hourLevelValue - mmin * minuteLevelValue - msecond * secondLevelValue) / 100);
                    String strhour = hour < 10 ? "0" + hour : "" + hour;//分钟
                    String strMinute = mmin < 10 ? "0" + mmin : "" + mmin;//分钟
                    String strSecond = msecond < 10 ? "0" + msecond : "" + msecond;//秒
                    //  String strMilliSecond = millisecond < 10 ? "0" + millisecond : "" + millisecond;//毫秒不满十前面补0
          /*  strMilliSecond = milliSecond > 100 ? strMilliSecond.substring(0, strMilliSecond.length() - 1) : "" +
                    strMilliSecond;////毫秒超过100显示前2位*/
                    //显示分秒毫秒的倒计时


                    //----------------------------------------------
                    onTick(year, month, day, strhour, strMinute, strSecond, millisecond);

                    // take into account user's onTick taking time to execute
                    long delay = lastTickStart + mCountdownInterval - SystemClock.elapsedRealtime();

                    // special case: user's onTick took more than interval to
                    // complete, skip to next interval
                    while (delay < 0)
                        delay += mCountdownInterval;

                    sendMessageDelayed(obtainMessage(MSG), delay);
                }
            }
        }
    };
}
