package com.project.my.studystarteacher.newteacher.activity.my;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.HomeLoveAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.project.my.studystarteacher.newteacher.common.TempSourceSupply;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_love_join_details)
public class LoveJoinDetailsActivity extends BaseActivity {
    @ViewInject(R.id.list)
    private PullToRefreshListView list;

    @Override
    protected void init() {
        getCommonTitle().setText("报名详情");
        HomeLoveAdapter adapter = new HomeLoveAdapter(mContext, R.layout.item_love_mdetail, TempSourceSupply.getCZSData());
        list.setAdapter(adapter);

    }
}
