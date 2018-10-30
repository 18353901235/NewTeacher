package com.project.my.studystarteacher.newteacher.fragment.brobook;


import android.annotation.SuppressLint;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.RecommendAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseFragment;
import com.project.my.studystarteacher.newteacher.bean.HandlerTJ;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.project.my.studystarteacher.newteacher.utils.EventBusUtil;
import com.project.my.studystarteacher.newteacher.utils.EventWhatId;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.JsonUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

@ContentView(R.layout.fragment__record_list)
public class RecommendBBookFragment extends BaseFragment {
    @ViewInject(R.id.list)
    private PullToRefreshListView listView;
    int id = -1;

    @SuppressLint("ValidFragment")
    public RecommendBBookFragment(int id) {
        this.id = id;
    }

    public RecommendBBookFragment() {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void update(EventBusUtil u) {
        if (u.getMsgWhat() == EventWhatId.B) {
            ArrayList<HandlerTJ.BagsBean> Object = (ArrayList<HandlerTJ.BagsBean>) u.getMsgStr();
            RecommendAdapter demoAdapter = new RecommendAdapter(mContext, R.layout.commendbook_item, Object, id);
            listView.setAdapter(demoAdapter);
        }
    }

    @Override
    public void init() {
        EventBus.getDefault().register(this);
        if (id != -1) {
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void getData() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                ArrayList<HandlerTJ> recommendbooklist = JsonUtil.fromList((String) baseBean.getData(), "recommendbooklist", HandlerTJ.class);
                for (HandlerTJ tj : recommendbooklist) {
                    if (tj.getCategoryletter().equals("A")) {

                    }
                    if (tj.getCategoryletter().equals("B")) {
                        RecommendAdapter demoAdapter = new RecommendAdapter(mContext, R.layout.commendbook_item, tj.getBags(), id);
                        listView.setAdapter(demoAdapter);
                    }
                    if (tj.getCategoryletter().equals("C")) {

                    }
                }

            }
        });
        Worker.getRecommendBookList(id + "");
    }


}
