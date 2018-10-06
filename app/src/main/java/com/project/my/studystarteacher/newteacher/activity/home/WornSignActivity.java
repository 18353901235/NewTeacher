package com.project.my.studystarteacher.newteacher.activity.home;

import android.widget.GridView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.DemoAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.project.my.studystarteacher.newteacher.common.TempSourceSupply;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_worn_sign)
public class WornSignActivity extends BaseActivity {
    @ViewInject(R.id.gv)
    private GridView gv;

    @Override
    protected void init() {
        getCommonTitle().setText("破损登记");
        DemoAdapter demoAdapter = new DemoAdapter(mContext, R.layout.iv_f_item, TempSourceSupply.getCZSData());
        gv.setAdapter(demoAdapter);
    }
}
