package com.project.my.studystarteacher.newteacher.activity.home;

import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.HomeActiviAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.project.my.studystarteacher.newteacher.bean.DyNamicBean;
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

@ContentView(R.layout.activity_read_actvi)
public class ReadActviActivity extends BaseActivity {
    @ViewInject(R.id.list)
    private PullToRefreshListView list;

    @Override
    protected void init() {
        getCommonTitle().setText("悦读活动");
        EventBus.getDefault().register(this);
        getRight().setBackgroundResource(R.mipmap.ic_add);
        getRight().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToActivity(mContext, PublicActviActivity.class);
            }
        });
        getData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refrsh(EventBusUtil u) {
        if (u.getMsgWhat() == EventWhatId.REFRSH) {
            getData();
        }
    }

    //dynamic/dynamicFunction
//  //type的类型,1是返回动态列表2是点赞,3是取消点赞,4添加评论,7删除
    public void getData() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                ArrayList<DyNamicBean> dyNamicBeans = JsonUtil.fromList((String) baseBean.getData(), "resultObject", DyNamicBean.class);
                HomeActiviAdapter adapter = new HomeActiviAdapter(mContext, R.layout.activi_list_item, dyNamicBeans);
                list.setAdapter(adapter);
            }
        });
        Worker.dynamicFunction("1", "", "", "", "");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
