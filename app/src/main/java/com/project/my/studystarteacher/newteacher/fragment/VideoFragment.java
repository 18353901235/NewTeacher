package com.project.my.studystarteacher.newteacher.fragment;


import android.view.View;
import android.widget.AdapterView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.activity.home.VideoDetailsActivity;
import com.project.my.studystarteacher.newteacher.adapter.VideoAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseFragment;
import com.project.my.studystarteacher.newteacher.common.TempSourceSupply;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.fragment_video)
public class VideoFragment extends BaseFragment {
    @ViewInject(R.id.list)
    private PullToRefreshListView listView;

    @Override
    public void init() {
        getCommonTitle().setText("育儿直播");
        getLeft().setVisibility(View.GONE);
        getRight().setBackgroundResource(R.mipmap.musicbk_ic_search);
        VideoAdapter videoAdapter = new VideoAdapter(getActivity(), R.layout.item_video, TempSourceSupply.getTemp());
        listView.setAdapter(videoAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToActivity(mContext, VideoDetailsActivity.class);
            }
        });
    }

}
