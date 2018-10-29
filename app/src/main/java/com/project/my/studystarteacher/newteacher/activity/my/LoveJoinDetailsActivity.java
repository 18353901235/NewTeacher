package com.project.my.studystarteacher.newteacher.activity.my;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.HomeStatAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.project.my.studystarteacher.newteacher.bean.YaoSign;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.JsonUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

@ContentView(R.layout.activity_love_join_details)
public class LoveJoinDetailsActivity extends BaseActivity {
    @ViewInject(R.id.list)
    private PullToRefreshListView list;

    @Override
    protected void init() {
        getCommonTitle().setText("报名详情");

        //   statistics
        getData();
    }

    public void getData() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                ArrayList<YaoSign> yaoSigns = JsonUtil.fromList((String) baseBean.getData(), "detail", YaoSign.class);
                HomeStatAdapter adapter = new HomeStatAdapter(mContext, R.layout.item_love_mdetail, yaoSigns);
                list.setAdapter(adapter);
            }
        });
        Worker.statistics();
    }


}
