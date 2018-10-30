package com.project.my.studystarteacher.newteacher.fragment.brobook;


import android.annotation.SuppressLint;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.RecommendAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseFragment;
import com.project.my.studystarteacher.newteacher.bean.HandlerTJ;
import com.project.my.studystarteacher.newteacher.utils.EventBusUtil;
import com.project.my.studystarteacher.newteacher.utils.EventWhatId;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

@ContentView(R.layout.fragment__record_list)
public class RecommendCBookFragment extends BaseFragment {
    @ViewInject(R.id.list)
    private PullToRefreshListView listView;
    int id = -1;

    @SuppressLint("ValidFragment")
    public RecommendCBookFragment(int id) {
        this.id = id;
    }

    public RecommendCBookFragment() {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void update(EventBusUtil u) {
        if (u.getMsgWhat() == EventWhatId.C) {
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


}
