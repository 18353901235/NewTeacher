package com.project.my.studystarteacher.newteacher.activity.home;

import android.Manifest;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.project.my.studystarteacher.newteacher.bean.AudioBook;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.project.my.studystarteacher.newteacher.service.VoiceCallBack;
import com.project.my.studystarteacher.newteacher.service.VoiceManager;
import com.project.my.studystarteacher.newteacher.utils.EventBusUtil;
import com.project.my.studystarteacher.newteacher.utils.EventWhatId;
import com.project.my.studystarteacher.newteacher.utils.FileUtils;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.Logger;
import com.zhouqiang.framework.util.ToastUtil;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_recod)
public class RecodActivity extends BaseActivity {
    private static String path;
    @ViewInject(R.id.button_rec)
    private TextView buttonRec;
    @ViewInject(R.id.button_wancheng)
    private TextView buttonWancheng;
    @ViewInject(R.id.btton)
    private ImageView btton;
    private VoiceManager voiceManager;
    @ViewInject(R.id.bookName)
    private TextView boolN;
    String srcpath = "";
    boolean isUpdata;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updata(EventBusUtil u) {
        if (u.getMsgWhat() == EventWhatId.RECODE_START) {
            srcpath = "";
        }

    }

    @PermissionGrant(0)
    public void requestSdcardSuccess() {
    }

    @PermissionDenied(0)
    public void requestSdcardFailed() {
        Toast.makeText(mContext, "权限授权失败，无法录制音频", Toast.LENGTH_SHORT).show();
    }

    public void requestPermiss() {
        MPermissions.requestPermissions(this, 0, Manifest.permission
                        .RECORD_AUDIO,
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    AudioBook book;

    @Override
    protected void init() {
        EventBus.getDefault().register(this);
        requestPermiss();
        book = (AudioBook) getIntent().getSerializableExtra("data");
        boolN.setText("《" + book.getBookname() + "》");
        getCommonTitle().setText("音频录制");
        getRightTextView().setText("提交");
        path = FileUtils.getRecorDir(this).toString();
        Logger.d("path:" + path);
        voiceManager = new VoiceManager(mContext, path);

        voiceManager.setOnRefreshDataListener(new VoiceManager.OnRefreshDataListener() {
            @Override
            public void onRefreshData() {
                ToastUtil.showLongToast(mContext, "播放结束");
            }
        });
        getRightTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (voiceManager.STATE_CODE == voiceManager.RECORD || voiceManager.STATE_CODE == voiceManager.SUSPENDED) {
                    if (isNull(srcpath)) {
                        isUpdata = true;
                        voiceManager.clickRecordFinish();
                    } else {
                        ToastUtil.showLongToast(mContext, "您还没有录制");
                    }

                } else {
                    voiceManager.STATE_CODE = voiceManager.STOP;
                    isUpdata = true;
                    voiceManager.clickRecordFinish();
                    voiceManager.stateOFPlay();

                }
            }


        });

        voiceManager.setVoiceListener(new VoiceCallBack() {
            @Override
            public void voicePath(String path, String name) {
                Logger.d("::::" + path);
                srcpath = path;
                findViewById(R.id.unit_voice_play_root).setVisibility(View.VISIBLE);
                Logger.d(":::" + (findViewById(R.id.unit_voice_play_root).getVisibility() == View.GONE));

                if (isUpdata) {
                    updataRecode();
                    isUpdata = false;
                } else {
                    voiceManager.sessionPlay(true, srcpath);
                }
            }

            @Override
            public void recFinish() {

            }
        });

        buttonWancheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voiceManager.STATE_CODE = voiceManager.STOP;
                voiceManager.clickRecordFinish();
                voiceManager.stateOFPlay();
            }
        });
    }

    private void updataRecode() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                ToastUtil.showLongToast(mContext, "上传成功");
                EventBus.getDefault().post(new EventBusUtil(EventWhatId.RECODE_UPOK));
                finish();
            }
        });
        Logger.d("上传地址：" + srcpath);
        Worker.uploadAudio(book.getBookbhao(), srcpath);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
