package com.project.my.studystarteacher.newteacher.fragment;


import android.view.View;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.base.BaseFragment;

import org.xutils.view.annotation.ContentView;

@ContentView(R.layout.fragment_class)
public class ClassFragment extends BaseFragment {
    @Override
    public void init() {
        getCommonTitle().setText("育儿直播");
        getLeft().setVisibility(View.GONE);
        getRight().setBackgroundResource(R.mipmap.musicbk_ic_search);
    }

}
