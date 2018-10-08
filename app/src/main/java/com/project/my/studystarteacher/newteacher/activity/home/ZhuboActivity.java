package com.project.my.studystarteacher.newteacher.activity.home;

import android.view.View;
import android.widget.AdapterView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.DemoAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.project.my.studystarteacher.newteacher.common.TempSourceSupply;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_zhubo)
public class ZhuboActivity extends BaseActivity {
    @ViewInject(R.id.list)
    private PullToRefreshListView list;

    @Override
    protected void init() {
        getRight().setBackgroundResource(R.mipmap.musicbk_ic_search);
        getCommonTitle().setText("主播");
        final DemoAdapter adapter = new DemoAdapter(mContext, R.layout.zhubo_list_item, TempSourceSupply.getCZSData());
        list.setAdapter(adapter);
        final DemoAdapter adapter2 = new DemoAdapter(mContext, R.layout.zhubo_list2_item, TempSourceSupply.getCZSData());
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToActivity(mContext, RecodActivity.class);
            }
        });
        findViewById(R.id.bt_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ToActivity(mContext, RecodActivity.class);
                    }
                });
            }
        });
        findViewById(R.id.bt_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.setAdapter(adapter2);
            }
        });
    }
}
