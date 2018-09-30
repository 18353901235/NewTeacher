package com.project.my.studystarteacher.newteacher.activity.my;

import android.view.View;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;

import org.xutils.view.annotation.ContentView;

@ContentView(R.layout.activity_love_boss)
public class LoveBossActivity extends BaseActivity {

    @Override
    protected void init() {
        getCommonTitle().setText("分享主题");
        getRight().setBackgroundResource(R.mipmap.ic_statistics);
        getRight().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToActivity(mContext, LoveJoinDetailsActivity.class);
            }
        });
    }
}
