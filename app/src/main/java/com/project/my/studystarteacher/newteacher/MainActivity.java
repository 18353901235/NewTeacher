package com.project.my.studystarteacher.newteacher;

import com.project.my.studystarteacher.newteacher.base.BaseActivity;

import org.xutils.view.annotation.ContentView;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @Override
    protected void init() {
        getCommonTitle().setText("测试页面");
        setTopBackBg(R.color.colorTitleBg);
    }
}
