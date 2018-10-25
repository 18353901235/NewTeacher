package com.project.my.studystarteacher.newteacher.service;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.common.CommonDefine;
import com.project.my.studystarteacher.newteacher.utils.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class VoiceManager {
    public static final int STOP = 1;
    public static final int RECORD = 2;
    public static final int SUSPENDED = 3;
    private static final int PLAY = 4;
    public static int STATE_CODE = STOP;

    private ArrayList<File> mRecList = new ArrayList<File>();
    private Activity mActivity = null;
    private View mVRecRoot, mVPlaRoot, mVRecFinish;
    private ImageView mIVRecOperate, mIVPlaOperate, mIVPlaStop;
    private TextView mTVRecText, mTVRecTime, mTVPlaCurrent, mTVPlaTotal;
    private SeekBar mSBPlayProgress;
    private int mSavedState, mDeviceState = CommonDefine.MEDIA_STATE_UNDEFINE;
    private MediaRecorder mMediaRecorder = null;
    private MediaPlayer mMediaPlayer = null;
    private String mRecTimePrev, mPlaFilePath;
    private long mRecTimeSum = 0;
    private String filePath = "";// 录音所存放的位置 "/com.youmu.voicemanager/audio"
    private VoiceCallBack voiceCallBack;
    private TextView mBtRec, btn_cl;
    private TextView btn_zt;
    private ProgressBar bar;

    //这里，我们定义一个接口
    public interface OnRefreshDataListener {
        public void onRefreshData();
    }

    private OnRefreshDataListener mListener;

    //写一个设置接口监听的方法
    public void setOnRefreshDataListener(OnRefreshDataListener listener) {
        mListener = listener;
    }

    private MediaPlayer.OnCompletionListener mPlayCompetedListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            mDeviceState = CommonDefine.MEDIA_STATE_PLAY_STOP;
            mHandler.removeMessages(CommonDefine.MSG_TIME_INTERVAL);

            mMediaPlayer.stop();
            mMediaPlayer.release();
            if (mVPlaRoot != null) {
                mVPlaRoot.setVisibility(View.GONE);
            }
            mListener.onRefreshData();
            mSBPlayProgress.setProgress(0);
            mTVPlaCurrent.setText("00:00:00");
            mIVPlaOperate.setImageResource(R.mipmap.voice_play);
//			for (int i = 0; i < RecordData.getData().size(); i++) {
//				RecordData.getData().get(i).setState(false);
//			}


        }
    };
    int paridex = 0;
    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            TimeMethod ts;
            int current;
            try {
                switch (msg.what) {
                    case CommonDefine.MSG_TIME_INTERVAL:
                        if (mDeviceState == CommonDefine.MEDIA_STATE_RECORD_DOING) {
                            ts = TimeMethod.timeSpanToNow(mRecTimePrev);
                            mRecTimeSum += ts.mDiffSecond;
                            mRecTimePrev = TimeMethod
                                    .getTimeStrFromMillis(ts.mNowTime);

                            ts = TimeMethod.timeSpanSecond(mRecTimeSum);
                            mTVRecTime.setText(String.format("%02d:%02d:%02d",
                                    ts.mSpanHour, ts.mSpanMinute, ts.mSpanSecond));
                            paridex++;
                            bar.setProgress(paridex);
                            ;
                            if (paridex == 900) {
                                STATE_CODE = STOP;
                                clickRecordFinish();
                                stateOFPlay();
                            }
                            mHandler.sendEmptyMessageDelayed(
                                    CommonDefine.MSG_TIME_INTERVAL, 1000);

                        } else if (mDeviceState == CommonDefine.MEDIA_STATE_PLAY_DOING) {
                            current = mMediaPlayer.getCurrentPosition();
                            mSBPlayProgress.setProgress(current);

                            ts = TimeMethod.timeSpanSecond(current / 1000);
                            mTVPlaCurrent.setText(String.format("%02d:%02d:%02d",
                                    ts.mSpanHour, ts.mSpanMinute, ts.mSpanSecond));

                            mHandler.sendEmptyMessageDelayed(
                                    CommonDefine.MSG_TIME_INTERVAL, 1000);
                        }
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
            }
        }
    };

    public void setVoiceListener(VoiceCallBack callBack) {
        voiceCallBack = callBack;
    }

    // private Button mBtRec,btn_cl,btn_zt;
    public void Play() {
        STATE_CODE = PLAY;
        clickRecordFinish();
        stateOFPlay();
    }

    public void stateOFPlay() {
        if (STATE_CODE == STOP) {
            mBtRec.setText("开始录音");
            mBtRec.setVisibility(View.VISIBLE);
            // btn_cl.setVisibility(View.GONE);
            //  btn_zt.setVisibility(View.GONE);
            mVRecRoot.setVisibility(View.GONE);
            //   mVPlaRoot.setVisibility(View.GONE);
            Drawable nav_up = mActivity.getResources().getDrawable(
                    R.mipmap.record_kaishiluzhi);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(),
                    nav_up.getMinimumHeight());

            mBtRec.setCompoundDrawables(null, nav_up, null,
                    null);
        } else if (STATE_CODE == RECORD) {
            mBtRec.setText("暂停");
            Drawable nav_up = mActivity.getResources().getDrawable(
                    R.mipmap.play_btn_play);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(),
                    nav_up.getMinimumHeight());

            mBtRec.setCompoundDrawables(null, nav_up, null,
                    null);
            btn_cl.setVisibility(View.VISIBLE);
            btn_zt.setVisibility(View.VISIBLE);
            mVRecRoot.setVisibility(View.VISIBLE);
            mVPlaRoot.setVisibility(View.GONE);
        } else if (STATE_CODE == SUSPENDED) {
            mBtRec.setText("继续");
            Drawable nav_up = mActivity.getResources().getDrawable(
                    R.mipmap.play_btn_suspend);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(),
                    nav_up.getMinimumHeight());

            mBtRec.setCompoundDrawables(null, nav_up, null,
                    null);
            btn_cl.setVisibility(View.VISIBLE);
            btn_zt.setVisibility(View.VISIBLE);
            mVRecRoot.setVisibility(View.VISIBLE);
            //   mVPlaRoot.setVisibility(View.GONE);
        } else if (STATE_CODE == PLAY) {
            mBtRec.setText("开始录音");
            //   btn_cl.setVisibility(View.GONE);
            //  btn_zt.setVisibility(View.GONE);
            mVRecRoot.setVisibility(View.GONE);
            mVPlaRoot.setVisibility(View.VISIBLE);
        }
    }

    public VoiceManager(Activity act, String filePath) {
        this.mActivity = act;
        this.filePath = filePath;
        mBtRec = (TextView) mActivity.findViewById(R.id.button_rec);
        btn_cl = (TextView) mActivity.findViewById(R.id.button_chonglu);
        btn_zt = (TextView) mActivity.findViewById(R.id.button_wancheng);
        bar = (ProgressBar) mActivity.findViewById(R.id.bar);

        // unit_voice_record_operate
        mVRecRoot = mActivity.findViewById(R.id.unit_voice_record_root);
        mIVRecOperate = (ImageView) mActivity
                .findViewById(R.id.unit_voice_record_operate);
        mTVRecText = (TextView) mActivity
                .findViewById(R.id.unit_voice_record_text);
        mTVRecTime = (TextView) mActivity
                .findViewById(R.id.unit_voice_record_time);
        mVRecFinish = mActivity.findViewById(R.id.unit_voice_record_finish);

        mVPlaRoot = mActivity.findViewById(R.id.unit_voice_play_root);
        mIVPlaOperate = (ImageView) mActivity
                .findViewById(R.id.unit_voice_play_operate);
        mIVPlaStop = (ImageView) mActivity
                .findViewById(R.id.unit_voice_play_stop);
        mSBPlayProgress = (SeekBar) mActivity
                .findViewById(R.id.unit_voice_play_progress);
        mTVPlaCurrent = (TextView) mActivity
                .findViewById(R.id.unit_voice_play_current);
        mTVPlaTotal = (TextView) mActivity
                .findViewById(R.id.unit_voice_play_total);
        Drawable nav_up = mActivity.getResources().getDrawable(
                R.mipmap.record_kaishiluzhi);
        nav_up.setBounds(0, 0, nav_up.getMinimumWidth(),
                nav_up.getMinimumHeight());
        mBtRec.setCompoundDrawables(null, nav_up, null,
                null);
        if (btn_zt != null) {
            btn_zt.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    STATE_CODE = STOP;
                    clickRecordFinish();
                    stateOFPlay();
                }
            });
        }
        if (btn_cl != null) {
            btn_cl.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    STATE_CODE = RECORD;
                    sessionRecord(true);
                    stateOFPlay();
                }
            });
        }
        if (mBtRec != null) {
            mBtRec.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    if (STATE_CODE == STOP || STATE_CODE == PLAY) {
                        STATE_CODE = RECORD;
                        sessionRecord(true);
                    } else if (STATE_CODE == RECORD) {
                        STATE_CODE = SUSPENDED;
                        mDeviceState = CommonDefine.MEDIA_STATE_RECORD_PAUSE;
                        stopRecorder(mMediaRecorder, true);
                        mMediaRecorder = null;

                        mTVRecText.setText("已暂停");
//						mIVRecOperate.setImageResource(R.drawable.voice_pause);
                    } else if (STATE_CODE == SUSPENDED) {
                        sessionRecord(false);
                        STATE_CODE = RECORD;
                        mTVRecText.setText("正在录音...");
                    }

                    stateOFPlay();

                }
            });
        }
        if (mIVRecOperate != null) {
            mIVRecOperate.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mDeviceState == CommonDefine.MEDIA_STATE_RECORD_DOING) {
                        mDeviceState = CommonDefine.MEDIA_STATE_RECORD_PAUSE;
                        stopRecorder(mMediaRecorder, true);
                        mMediaRecorder = null;

                        mTVRecText.setText("已暂停");
                        mIVRecOperate.setImageResource(R.mipmap.voice_pause);
                    } else {
                        sessionRecord(false);
                    }
                }
            });
        }

        if (mVRecFinish != null) {
            mVRecFinish.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickRecordFinish();
                }
            });
        }

        if (mIVPlaOperate != null) {
            mIVPlaOperate.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mDeviceState == CommonDefine.MEDIA_STATE_PLAY_DOING) {
                        mDeviceState = CommonDefine.MEDIA_STATE_PLAY_PAUSE;
                        pauseMedia(mMediaPlayer);
                        mIVPlaOperate.setImageResource(R.mipmap.voice_play);

                    } else if (mDeviceState == CommonDefine.MEDIA_STATE_PLAY_PAUSE) {
                        mDeviceState = CommonDefine.MEDIA_STATE_PLAY_DOING;
                        playMedia(mMediaPlayer);

                        mIVPlaOperate.setImageResource(R.mipmap.voice_pause);
                        mHandler.removeMessages(CommonDefine.MSG_TIME_INTERVAL);
                        mHandler.sendEmptyMessage(CommonDefine.MSG_TIME_INTERVAL);

                    } else if (mDeviceState == CommonDefine.MEDIA_STATE_PLAY_STOP) {
                        if (!TextUtils.isEmpty(mPlaFilePath)) {
                            sessionPlay(false, mPlaFilePath);
                        }
                    }
                }
            });
        }

        if (mIVPlaStop != null) {
            mIVPlaStop.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        mHandler.removeMessages(CommonDefine.MSG_TIME_INTERVAL);
                        mDeviceState = CommonDefine.MEDIA_STATE_PLAY_STOP;

                        mIVPlaOperate.setImageResource(R.mipmap.voice_play);
                        mTVPlaCurrent.setText("00:00:00");

                        stopMedia(mMediaPlayer, true);
                        mMediaPlayer = null;

                    } catch (Exception e) {

                    } finally {
                        //   mVPlaRoot.setVisibility(View.GONE);
                    }
                }
            });
        }

        if (mSBPlayProgress != null) {
            mSBPlayProgress
                    .setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {
                            mHandler.removeMessages(CommonDefine.MSG_TIME_INTERVAL);
                            mSavedState = mDeviceState;
                            if (mSavedState == CommonDefine.MEDIA_STATE_PLAY_DOING) {
                                pauseMedia(mMediaPlayer);
                            }
                        }

                        @Override
                        public void onProgressChanged(SeekBar seekBar,
                                                      int progress, boolean fromUser) {
                            mHandler.removeMessages(CommonDefine.MSG_TIME_INTERVAL);

                            TimeMethod ts = TimeMethod
                                    .timeSpanSecond(progress / 1000);
                            mTVPlaCurrent.setText(String.format(
                                    "%02d:%02d:%02d", ts.mSpanHour,
                                    ts.mSpanMinute, ts.mSpanSecond));
                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                            seektoMedia(mMediaPlayer,
                                    mSBPlayProgress.getProgress());

                            if (mSavedState == CommonDefine.MEDIA_STATE_PLAY_DOING) {
                                playMedia(mMediaPlayer);
                                mHandler.sendEmptyMessage(CommonDefine.MSG_TIME_INTERVAL);
                            }
                        }
                    });
        }
    }

    public void sessionRecord(boolean init) {
        // if (!FileUtils.isSDCardAvailable()) return;
        if (init) {
            mRecTimeSum = 0;
            cleanFieArrayList(mRecList);
        }

        stopRecorder(mMediaRecorder, true);
        mMediaRecorder = null;

        stopMedia(mMediaPlayer, true);
        mMediaPlayer = null;

        if (mVPlaRoot != null) {
            //    mVPlaRoot.setVisibility(View.GONE);
        }
        mVRecRoot.setVisibility(View.VISIBLE);
        mIVRecOperate.setImageResource(R.mipmap.voice_record);

        mMediaRecorder = new MediaRecorder();
        File file = prepareRecorder(mMediaRecorder, true);
        if (file != null) {
            mTVRecText.setText("正在录音");
            mDeviceState = CommonDefine.MEDIA_STATE_RECORD_DOING;
            mRecTimePrev = TimeMethod.getTimeStrFromMillis(System
                    .currentTimeMillis());
            mRecList.add(file);

            mHandler.removeMessages(CommonDefine.MSG_TIME_INTERVAL);
            mHandler.sendEmptyMessage(CommonDefine.MSG_TIME_INTERVAL);
        }
    }

    public void sessionRecordEx(boolean init, int resTimeTextId) {
        // if (!FileUtils.isSDCardAvailable()) return;
        if (init) {
            mRecTimeSum = 0;
            cleanFieArrayList(mRecList);
        }

        mTVRecTime = (TextView) mActivity.findViewById(resTimeTextId);

        stopRecorder(mMediaRecorder, true);
        mMediaRecorder = null;

        stopMedia(mMediaPlayer, true);
        mMediaPlayer = null;

        if (mVPlaRoot != null) {
            //   mVPlaRoot.setVisibility(View.GONE);
        }

        mMediaRecorder = new MediaRecorder();
        File file = prepareRecorder(mMediaRecorder, true);
        if (file != null) {
            mDeviceState = CommonDefine.MEDIA_STATE_RECORD_DOING;
            mRecTimePrev = TimeMethod.getTimeStrFromMillis(System
                    .currentTimeMillis());
            mRecList.add(file);

            mHandler.removeMessages(CommonDefine.MSG_TIME_INTERVAL);
            mHandler.sendEmptyMessage(CommonDefine.MSG_TIME_INTERVAL);
        }
    }

    /**
     * 完成录音
     */
    public void clickRecordFinish() {
        try {
            mHandler.removeMessages(CommonDefine.MSG_TIME_INTERVAL);
            mDeviceState = CommonDefine.MEDIA_STATE_RECORD_STOP;
            stopRecorder(mMediaRecorder, true);
            mMediaRecorder = null;

            if (mVRecRoot != null) {
                mVRecRoot.setVisibility(View.GONE);
            }
            if (TimeMethod.timeSpanSecond(mRecTimeSum).mSpanSecond == 0) {
                Toast.makeText(mActivity, "时间过短", Toast.LENGTH_SHORT).show();
            } else {
                File file = getOutputVoiceFile(mRecList);
                if (file != null && file.length() > 0) {
                    cleanFieArrayList(mRecList);
                    // TODO 这里可以返回数据 setResult
                    voiceCallBack.recFinish();
                    voiceCallBack.voicePath(file.getAbsolutePath(), mMinute);
                }
            }

        } catch (Exception e) {
        }
    }

    /**
     * 录音播放
     *
     * @param init
     * @param path
     */
    public void sessionPlay(boolean init, String path) {
        if (TextUtils.isEmpty(path))
            return;
        mPlaFilePath = path;
        try {
            mVPlaRoot.setVisibility(View.VISIBLE);
//            if (mVRecRoot != null) {
//                mVRecRoot.setVisibility(View.GONE);
//            }

            mIVPlaOperate.setImageResource(R.mipmap.voice_pause);
            mTVPlaCurrent.setText("00:00:00");

            stopRecorder(mMediaRecorder, true);
            mMediaRecorder = null;

            stopMedia(mMediaPlayer, true);
            mMediaPlayer = null;

            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setOnCompletionListener(mPlayCompetedListener);

            if (prepareMedia(mMediaPlayer, path)) {
                mDeviceState = CommonDefine.MEDIA_STATE_PLAY_DOING;

                TimeMethod ts = TimeMethod.timeSpanSecond(mMediaPlayer
                        .getDuration() / 1000);
                mTVPlaTotal.setText(String.format("%02d:%02d:%02d",
                        ts.mSpanHour, ts.mSpanMinute, ts.mSpanSecond));

                mSBPlayProgress.setMax(Math.max(1, mMediaPlayer.getDuration()));

                if (init) {
                    mSBPlayProgress.setProgress(0);
                    seektoMedia(mMediaPlayer, 0);
                } else {
                    seektoMedia(mMediaPlayer, mSBPlayProgress.getProgress());
                }

                if (playMedia(mMediaPlayer)) {
                    mHandler.removeMessages(CommonDefine.MSG_TIME_INTERVAL);
                    mHandler.sendEmptyMessage(CommonDefine.MSG_TIME_INTERVAL);
                }
            }
        } catch (Exception e) {
        }
    }

    /**
     * 合并录音
     *
     * @param list
     * @return
     */
    private String mMinute;

    private File getOutputVoiceFile(ArrayList<File> list) {
        String mMinute1 = TimeMethod.getTime();
        mMinute = mMinute1 + ".amr";
        // String recFilePath =
        // FileUtils.getRecorDir(mActivity).getAbsolutePath();
        File recDirFile = FileUtils.getRecorDir(mActivity);

        // 创建音频文件,合并的文件放这里
        File resFile = new File(recDirFile, mMinute1 + ".amr");
        FileOutputStream fileOutputStream = null;

        if (!resFile.exists()) {
            try {
                resFile.createNewFile();
            } catch (IOException e) {
            }
        }
        try {
            fileOutputStream = new FileOutputStream(resFile);
        } catch (IOException e) {
        }
        // list里面为暂停录音 所产生的 几段录音文件的名字，中间几段文件的减去前面的6个字节头文件
        for (int i = 0; i < list.size(); i++) {
            File file = list.get(i);
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] myByte = new byte[fileInputStream.available()];
                // 文件长度
                int length = myByte.length;
                // 头文件
                if (i == 0) {
                    while (fileInputStream.read(myByte) != -1) {
                        fileOutputStream.write(myByte, 0, length);
                    }
                }
                // 之后的文件，去掉头文件就可以了
                else {
                    while (fileInputStream.read(myByte) != -1) {
                        fileOutputStream.write(myByte, 6, length - 6);
                    }
                }
                fileOutputStream.flush();
                fileInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 结束后关闭流
        try {
            fileOutputStream.close();
        } catch (IOException e) {
        }

        return resFile;
    }

    private void cleanFieArrayList(ArrayList<File> list) {
        for (File file : list) {
            file.delete();
        }
        list.clear();
    }

    /**
     * 播放录音准备工作
     *
     * @param mp
     * @param file
     * @return
     */
    private boolean prepareMedia(MediaPlayer mp, String file) {
        boolean result = false;

        try {
            mp.setDataSource(file);
            mp.prepare();
            result = true;
        } catch (Exception e) {
        }

        return result;
    }

    /**
     * 播放录音开始
     *
     * @param mp
     * @return
     */
    private boolean playMedia(MediaPlayer mp) {
        boolean result = false;

        try {
            if (mp != null) {
                mp.start();
                result = true;
            }
        } catch (Exception e) {
        }

        return result;
    }

    /**
     * 拖动播放进度条
     *
     * @param mp
     * @param pos
     * @return
     */
    private boolean seektoMedia(MediaPlayer mp, int pos) {
        boolean result = false;

        try {
            if (mp != null && pos >= 0) {
                mp.seekTo(pos);
                result = true;
            }
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 外部调用停止播放
     */
    public boolean stopMedia() {

        boolean result = false;

        try {
            if (mMediaPlayer != null) {
                mMediaPlayer.stop();


                mMediaPlayer.release();

                result = true;
            }
        } catch (Exception e) {
        }

        return result;
    }

    /**
     * 停止播放
     *
     * @param mp
     * @param release
     * @return
     */
    private boolean stopMedia(MediaPlayer mp, boolean release) {
        boolean result = false;

        try {
            if (mp != null) {
                mp.stop();

                if (release) {
                    mp.release();
                }
                result = true;
            }
        } catch (Exception e) {
        }

        return result;
    }

    /**
     * 暂停播放
     *
     * @param mp
     * @return
     */
    private boolean pauseMedia(MediaPlayer mp) {
        boolean result = false;

        try {
            if (mp != null) {
                mp.pause();
                result = true;
            }
        } catch (Exception e) {
        }

        return result;
    }

    /**
     * 停止录音
     *
     * @param mr
     * @param release
     * @return
     */
    private boolean stopRecorder(MediaRecorder mr, boolean release) {
        boolean result = false;

        try {
            if (mr != null) {
                mr.stop();
                if (release) {
                    mr.release();
                }
                result = true;
            }
        } catch (Exception e) {
        }

        return result;
    }

    /**
     * 录音准备工作 ，开始录音
     *
     * @param mr
     * @param start
     * @return
     */
    @SuppressWarnings("deprecation")
    private File prepareRecorder(MediaRecorder mr, boolean start) {
        File recFile = null;
        if (mr == null)
            return null;

        try {
            String path = FileUtils.getRecorDir(mActivity)
                    .getAbsolutePath();
            recFile = new File(path, TimeMethod.getTime() + ".amr");

            mr.setAudioSource(MediaRecorder.AudioSource.MIC);
            mr.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
            mr.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mr.setOutputFile(recFile.getAbsolutePath());
            mr.prepare();

            if (start) {
                mr.start();
            }
        } catch (Exception e) {
        }
        return recFile;
    }
}
