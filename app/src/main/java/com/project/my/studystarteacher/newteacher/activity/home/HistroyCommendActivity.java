package com.project.my.studystarteacher.newteacher.activity.home;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.HistroyBookAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.project.my.studystarteacher.newteacher.bean.HistroyBook;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.JsonUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

@ContentView(R.layout.activity_histroy_commend)
public class HistroyCommendActivity extends BaseActivity {
    @ViewInject(R.id.list)
    private PullToRefreshListView list;
    int id;

    @Override
    protected void init() {
        getCommonTitle().setText("历史推荐");
        id = getIntent().getIntExtra("data", -1);

        getData();
    }

    public void getData() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                ArrayList<HistroyBook> historyBags = JsonUtil.fromList((String) baseBean.getData(), "historyBags", HistroyBook.class);

                HistroyBookAdapter adapter = new HistroyBookAdapter(mContext, R.layout.histroy_list_item, historyBags);
                list.setAdapter(adapter);

            }
        });
        Worker.getNewHistoryRecommended(id);
    }
}
