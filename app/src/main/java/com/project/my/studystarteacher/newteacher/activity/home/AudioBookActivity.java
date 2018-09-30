package com.project.my.studystarteacher.newteacher.activity.home;

import android.view.View;
import android.widget.AdapterView;

import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.DemoAdapter;
import com.project.my.studystarteacher.newteacher.adapter.HomeZhuboAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.project.my.studystarteacher.newteacher.common.TempSourceSupply;
import com.project.my.studystarteacher.newteacher.utils.PopWindowUtils;
import com.project.my.studystarteacher.newteacher.view.AllGridView;
import com.zhouqiang.framework.util.WindowSize;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_audio_book)
public class AudioBookActivity extends BaseActivity {
    @ViewInject(R.id.list)
    private PullToRefreshGridView list;

    @Override
    protected void init() {
        getCommonTitle().setText("有声绘本");
        getRight().setBackgroundResource(R.mipmap.musicbk_ic_search);
        getRight2().setBackgroundResource(R.mipmap.musicbk_ic_screen);
        HomeZhuboAdapter homeClassAdapter3 = new HomeZhuboAdapter(mContext, R.layout.item_audio_list, TempSourceSupply.getMyData());
        final View inflate = View.inflate(mContext, R.layout.pop_fifter_audio, null);
        AllGridView gridView = inflate.findViewById(R.id.gridview);
        DemoAdapter adapter = new DemoAdapter(mContext, R.layout.item_audio_fifter, TempSourceSupply.getCZSData());
        gridView.setAdapter(adapter);
        list.setAdapter(homeClassAdapter3);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToActivity(mContext, AudioPayerActivity.class);
            }
        });
        getRight2().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopWindowUtils.show(mContext, v, inflate, -1, WindowSize.getWidth(mContext) / 4 * 3);
            }
        });
    }
}
