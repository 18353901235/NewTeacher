package com.project.my.studystarteacher.newteacher.fragment;


import android.view.View;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.base.BaseFragment;

import org.xutils.view.annotation.ContentView;

@ContentView(R.layout.fragment_mine)
public class MyFragment extends BaseFragment {
    @Override
    public void init() {
        getLeft().setVisibility(View.GONE);
        getCommonTitle().setText("我的");
        getRight().setBackgroundResource(R.mipmap.me_ic_scan);

    }

}
