package com.project.my.studystarteacher.newteacher.activity.home;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;

import org.xutils.view.annotation.ContentView;

@ContentView(R.layout.activity_parent_submit)
public class WornDetailActivity extends BaseActivity {

    @Override
    protected void init() {
        getCommonTitle().setText("破损管理");
        getRight().setBackgroundResource(R.mipmap.musicbk_ic_search);
    }
}
