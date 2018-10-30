package com.project.my.studystarteacher.newteacher.activity.my;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.MyContactAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.project.my.studystarteacher.newteacher.bean.People;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.JsonUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

@ContentView(R.layout.activity_contact)
public class ContactActivity extends BaseActivity {
    @ViewInject(R.id.list)
    private PullToRefreshListView listView;

    @Override
    protected void init() {
        getCommonTitle().setText("通讯录");


        //PHONEBOOK
        getData();
    }

    public void getData() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                ArrayList<People> tearcherList = JsonUtil.fromList((String) baseBean.getData(), "tearcherList", People.class);
                MyContactAdapter demoAdapter = new MyContactAdapter(mContext, R.layout.contact_list_item, tearcherList);
                listView.setAdapter(demoAdapter);

            }
        });
        Worker.phoneBook();
    }
}
