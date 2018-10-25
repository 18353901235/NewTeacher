package com.project.my.studystarteacher.newteacher.activity.my;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.MyContactAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.project.my.studystarteacher.newteacher.common.TempSourceSupply;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_contact)
public class ContactActivity extends BaseActivity {
    @ViewInject(R.id.list)
    private PullToRefreshListView listView;

    @Override
    protected void init() {
        getCommonTitle().setText("通讯录");
        MyContactAdapter demoAdapter = new MyContactAdapter(mContext, R.layout.activity_contact_list, TempSourceSupply.getCZSData());
        listView.setAdapter(demoAdapter);
    }
}
