package com.project.my.studystarteacher.newteacher.fragment;


import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.base.BaseFragment;

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

    }

}
