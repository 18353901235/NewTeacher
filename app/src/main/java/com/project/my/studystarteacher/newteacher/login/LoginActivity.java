package com.project.my.studystarteacher.newteacher.login;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.activity.home.HomeActivity;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity {
    @ViewInject(R.id.regist)
    private TextView regist;
    @ViewInject(R.id.btnSSign)
    private Button btnSSign;

    @Override
    protected void init() {
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToActivity(mContext, RegistActivity.class);
            }
        });
        btnSSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToActivity(mContext, HomeActivity.class);
            }
        });
    }
}
