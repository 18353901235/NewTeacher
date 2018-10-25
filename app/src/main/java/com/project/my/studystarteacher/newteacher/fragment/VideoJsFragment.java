package com.project.my.studystarteacher.newteacher.fragment;


import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.base.BaseFragment;
import com.project.my.studystarteacher.newteacher.bean.ExpertLecture;
import com.project.my.studystarteacher.newteacher.utils.EventBusUtil;
import com.project.my.studystarteacher.newteacher.utils.EventWhatId;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.videojs_fragment)
public class VideoJsFragment extends BaseFragment {
    @ViewInject(R.id.p_name)
    private TextView pName;
    @ViewInject(R.id.topic)
    private TextView topic;
    @ViewInject(R.id.paly_num)
    private TextView palyNum;
    @ViewInject(R.id.desc)
    private TextView desc;
    @ViewInject(R.id.ly_main_weixin)
    private LinearLayout lyMainWeixin;
    @ViewInject(R.id.gv)
    private GridView gv;
    ExpertLecture lecture;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getData(EventBusUtil e) {
        if (e.getMsgWhat() == EventWhatId.ExpertLectureDetais) {
            lecture = (ExpertLecture) e.getMsgStr();
            pName.setText(lecture.getLecturer());
            topic.setText(lecture.getTopic());
            palyNum.setText("播放：" + lecture.getPlay_Count() + "+次");
            desc.setText(lecture.getDescription());
        }
    }

    @Override
    public void init() {
        EventBus.getDefault().register(this);


//        HomeZhuboAdapter homeClassAdapter3 = new HomeZhuboAdapter(getActivity(), R.layout.item_zhubosmall, TempSourceSupply.getTemp());
//        gv.setAdapter(homeClassAdapter3);


    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

}
