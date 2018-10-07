package com.project.my.studystarteacher.newteacher.activity.home;

import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.HomeActiviAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.project.my.studystarteacher.newteacher.common.TempSourceSupply;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_read_actvi)
public class ReadActviActivity extends BaseActivity {
    @ViewInject(R.id.list)
    private PullToRefreshListView list;

    @Override
    protected void init() {
        getCommonTitle().setText("悦读活动");
        getRight().setBackgroundResource(R.mipmap.ic_add);
        getRight().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToActivity(mContext, PublicActviActivity.class);
            }
        });
        HomeActiviAdapter adapter = new HomeActiviAdapter(mContext, R.layout.activi_list_item, TempSourceSupply.getCZSData());
        list.setAdapter(adapter);
    }
}
