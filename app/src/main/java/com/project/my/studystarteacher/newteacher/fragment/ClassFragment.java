package com.project.my.studystarteacher.newteacher.fragment;


import android.view.View;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.base.BaseFragment;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;

import org.xutils.view.annotation.ContentView;

@ContentView(R.layout.fragment_class)
public class ClassFragment extends BaseFragment {
    @Override
    public void init() {
        getCommonTitle().setText("育儿直播");
        getLeft().setVisibility(View.GONE);
        getRight().setBackgroundResource(R.mipmap.musicbk_ic_search);
        //typeLive
        getData();
    }

    public void getData() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
            }
        });
        Worker.typeLive();
    }
}
