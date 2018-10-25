package com.project.my.studystarteacher.newteacher.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.SeekBar;

import com.project.my.studystarteacher.newteacher.activity.home.AudioPayerActivity;
import com.project.my.studystarteacher.newteacher.common.Constant;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

import static com.project.my.studystarteacher.newteacher.activity.home.AudioPayerActivity.skbProgress;


public class MyService extends Service implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, SeekBar.OnSeekBarChangeListener {
    int position;
    TimerTask mTimerTask = new TimerTask() {
        @Override
        public void run() {
            if (mp == null)
                return;

            if (mp.isPlaying() && !skbProgress.isPressed()) {
                handleProgress.sendEmptyMessage(0);
                Log.d("run-----", "run: ");
            }
        }
    };

    Handler handleProgress = new Handler() {
        public void handleMessage(Message msg) {
            int position = mp.getCurrentPosition();
            int duration = mp.getDuration();
            Log.d("handleMessage-----", "handleMessage: ");
            if (duration > 0) {
                long pos = skbProgress.getMax() * position / duration;
                skbProgress.setProgress((int) pos);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                String format = simpleDateFormat.format(position);
                AudioPayerActivity.tv_currentTime.setText(format);
                Log.d("sumTime", position + "--" + format);

            }
        }
    };

    private int progress;
    private MediaPlayer mp;
    private Timer timer;
    private boolean isStop = false;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mp = new MediaPlayer();
        mp.setOnBufferingUpdateListener(this);
        mp.setOnCompletionListener(this);
        mp.setOnPreparedListener(this);
        mp.setOnErrorListener(this);
        timer = new Timer();
        timer.schedule(mTimerTask, 0, 1000);
        if (skbProgress != null) {
            skbProgress.setOnSeekBarChangeListener(this);
        }
    }

//    @Override
//    public void onStart(Intent intent, int startId) {
//        super.onStart(intent, startId);
//        switch (intent.getAction()) {
//            case Constant.Action.PLAY:
////                String song_ = song_list.get(position);
//                String song_ = intent.getStringExtra("song");
//                play(song_);
//                break;
//            case Constant.Action.PAUSE:
//                pause();
//                break;
//            case Constant.Action.RESTART:
//                reStart();
//                break;
//            case Constant.Action.STOP:
//                stopSelf();
//                break;
//            case Constant.Action.NEXT:
//                if (position == song_list.size()) {
//                    Toast.makeText(MyService.this, "已经是最后一首了", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                position++;
//                String song = song_list.get(position);
//                play(song);
//                break;
//            case Constant.Action.FRONT:
//                if (position == 0) {
//                    Toast.makeText(MyService.this, "前面没有了", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                position--;
//                String song__ = song_list.get(position);
//                play(song__);
//                break;
//        }
//    }

//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//
//
//        return super.onStartCommand(intent, flags, startId);
//    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        switch (intent.getAction()) {
            case Constant.Action.PLAY:
//                String song_ = song_list.get(position);
                String song_ = intent.getStringExtra("song");
                play(song_);
                break;
            case Constant.Action.PAUSE:
                pause();
                break;
            case Constant.Action.RESTART:
                reStart();
                break;
            case Constant.Action.STOP:
                mTimerTask.cancel();
                timer.cancel();
                stopSelf();
                Log.d("stopSelf-----", "stopSelf");
                break;
            case Constant.Action.NEXT:
//                if (position == song_list.size() - 1) {
//                    Toast.makeText(MyService.this, "已经是最后一首了", Toast.LENGTH_SHORT).show();
//                    break;
//                }
                //    String song = BOOK_PLAY_URL + song_list.get(position);
                song_ = intent.getStringExtra("song");
                play(song_);
                break;
            case Constant.Action.FRONT:
//                if (position == 0) {
//                    Toast.makeText(MyService.this, "前面没有了", Toast.LENGTH_SHORT).show();
//                    break;
//                }
//                position--;
                //  String song__ = BOOK_PLAY_URL + song_list.get(position);
                song_ = intent.getStringExtra("song");
                play(song_);
                break;
        }

        return START_REDELIVER_INTENT;
    }

    public void play(final String song) {

        new Thread() {
            @Override
            public void run() {
                super.run();
                mp.stop();
                mp.reset();
                try {
                    mp.setDataSource(song);
                    mp.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                    /**
                     * 这地方可能有个错误。  mp.setDataSource(song);
                     *
                     */

//                    Toast.makeText(MyService.this, "当前音频资源错误，无法播放,", Toast.LENGTH_SHORT).show();
                }
            }
        }.start();


    }

    public void reStart() {
        mp.start();
    }

    public void pause() {
        mp.pause();
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int bufferingProgress) {
        if (skbProgress != null) {
            try {
                skbProgress.setSecondaryProgress(bufferingProgress);
//                int currentProgress = skbProgress.getMax() * mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration();
//                Log.e(currentProgress + "% play", bufferingProgress + "% buffer");
            } catch (Exception e) {

            }


        }

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
//        if (position == song_list.size() - 1) {
//            Toast.makeText(this, "歌曲播放完毕", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        position++;
//        String song = GlobalContants.BOOK_PLAY_URL + song_list.get(position);
//        Log.d("onCompletion", position + "----------" + song);
//        play(song);

        skbProgress.setProgress(0);
        mp.seekTo(0);
        mp.start();

    }

    @Override
    public void onPrepared(final MediaPlayer mp) {
        int duration = mp.getDuration();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        String format = simpleDateFormat.format(duration);
        Log.d("sumTime", duration + "----" + format);
        AudioPayerActivity.tv_sumTime.setText(format);
        mp.start();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.d("what--extra", what + "----" + extra);
//        mp.stop();
////        mp.release();
//        String song = BOOK_PLAY_URL + song_list.get(position++);
//        play(song);
//        Toast.makeText(this, "当前音频文件损坏，请播放下一首", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        this.progress = progress * mp.getDuration() / seekBar.getMax();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mp.seekTo(progress);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        String format = simpleDateFormat.format(mp.getCurrentPosition());
        AudioPayerActivity.tv_currentTime.setText(format);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy------", "onDestroy: ");
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}