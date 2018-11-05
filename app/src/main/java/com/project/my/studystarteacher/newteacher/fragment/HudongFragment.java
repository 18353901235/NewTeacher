package com.project.my.studystarteacher.newteacher.fragment;


import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.activity.home.VideoDetailsActivity;
import com.project.my.studystarteacher.newteacher.adapter.VideoChartAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseFragment;
import com.project.my.studystarteacher.newteacher.bean.ExpertLecture;
import com.project.my.studystarteacher.newteacher.bean.PinglunBean;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.project.my.studystarteacher.newteacher.utils.EventBusUtil;
import com.project.my.studystarteacher.newteacher.utils.EventWhatId;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import crossoverone.statuslib.AndroidBug5497Workaround;

@ContentView(R.layout.hudong_fragment)
public class HudongFragment extends BaseFragment {
    @ViewInject(R.id.btn_face)
    private ImageButton btnFace;
    @ViewInject(R.id.et_sendmessage)
    private EditText etSendmessage;
    @ViewInject(R.id.rl_input)
    private RelativeLayout rlInput;
    @ViewInject(R.id.vp_contains)
    private ViewPager vpContains;
    @ViewInject(R.id.iv_image)
    private LinearLayout ivImage;
    @ViewInject(R.id.ll_facechoose)
    private RelativeLayout llFacechoose;
    @ViewInject(R.id.FaceRelativeLayout)
    private com.project.my.studystarteacher.newteacher.utils.FaceRelativeLayout FaceRelativeLayout;
    @ViewInject(R.id.ly_main_weixin)
    private LinearLayout lyMainWeixin;
    @ViewInject(R.id.list)
    private ListView list;
    @ViewInject(R.id.et_sendmessage)
    private EditText et_sendmessage;
    ExpertLecture lecture;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getData(EventBusUtil e) {
        if (e.getMsgWhat() == EventWhatId.ExpertLectureDetais) {
            lecture = (ExpertLecture) e.getMsgStr();
            //      getData();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getHd(EventBusUtil e) {
        if (e.getMsgWhat() == EventWhatId.PinglunBeanList) {
            ArrayList<PinglunBean> msgStr = (ArrayList<PinglunBean>) e.getMsgStr();
            VideoChartAdapter homeClassAdapter3 = new VideoChartAdapter(getActivity(), R.layout.item_videochart, msgStr);
            list.setAdapter(homeClassAdapter3);
            //      getData();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void init() {
        EventBus.getDefault().register(this);
        AndroidBug5497Workaround.assistActivity(mContext);//加上这一行,一定要在第一行F


        et_sendmessage.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == EditorInfo.IME_ACTION_SEND) {
                    MiceNetWorker Worker = new MiceNetWorker(mContext);
                    Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
                        @Override
                        public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                            super.onSuccess(netWorker, netTask, baseBean);
                            ToastUtil.showLongToast(mContext, "发表成功");
                            ((VideoDetailsActivity) getActivity()).getHDData();
                        }
                    });
                    Worker.publish(lecture.getId() + "", et_sendmessage.getText().toString().trim(), "2");
                }
                return false;
            }
        });
    }
}
