package com.project.my.studystarteacher.newteacher.activity.home;

import android.view.View;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.project.my.studystarteacher.newteacher.service.VoiceCallBack;
import com.project.my.studystarteacher.newteacher.service.VoiceManager;
import com.project.my.studystarteacher.newteacher.utils.FileUtils;
import com.zhouqiang.framework.util.Logger;
import com.zhouqiang.framework.util.ToastUtil;

import org.xutils.view.annotation.ContentView;

@ContentView(R.layout.activity_recod)
public class RecodActivity extends BaseActivity {
    private static String path;
    private VoiceManager voiceManager;

    @Override
    protected void init() {
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
        voiceManager.setVoiceListener(new VoiceCallBack() {
            @Override
            public void voicePath(String path, String name) {
                Logger.d("path:" + path + ":::name:" + name);
                voiceManager.sessionPlay(true, path);
                findViewById(R.id.unit_voice_play_root).setVisibility(View.VISIBLE);
                Logger.d(":::" + (findViewById(R.id.unit_voice_play_root).getVisibility() == View.GONE));
            }

            @Override
            public void recFinish() {

            }
        });

    }
}
