package com.project.my.studystarteacher.newteacher.activity.home;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.DemoAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.project.my.studystarteacher.newteacher.common.TempSourceSupply;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_histroy_commend)
public class HistroyCommendActivity extends BaseActivity {
    @ViewInject(R.id.list)
    private PullToRefreshListView list;

    @Override
    protected void init() {
        getCommonTitle().setText("历史推荐");
        DemoAdapter adapter = new DemoAdapter(mContext, R.layout.histroy_list_item, TempSourceSupply.getCZSData());
        list.setAdapter(adapter);
    }
}
