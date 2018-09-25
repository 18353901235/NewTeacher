package com.project.my.studystarteacher.newteacher.login;

import android.view.View;
import android.widget.Button;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_regist)
public class RegistActivity extends BaseActivity {

    @ViewInject(R.id.btnSSign)
    private Button btnSSign;

    @Override
    protected void init() {
        btnSSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToActivity(mContext, RegistTwoActivity.class);
            }
        });
    }
}
