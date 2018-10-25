package com.project.my.studystarteacher.newteacher.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.activity.home.HomeActivity;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.project.my.studystarteacher.newteacher.bean.User;
import com.project.my.studystarteacher.newteacher.common.UserSingleton;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.project.my.studystarteacher.newteacher.utils.MyUtils;
import com.project.my.studystarteacher.newteacher.utils.Utility;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.JsonUtil;
import com.zhouqiang.framework.util.Logger;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity {
    @ViewInject(R.id.regist)
    private TextView regist;
    @ViewInject(R.id.btnSSign)
    private Button btnSSign;
    @ViewInject(R.id.phone)
    private EditText etPhone;
    @ViewInject(R.id.pwd)
    private EditText etPWD;

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
                String content = Utility.getContent(etPhone);
                String PWD = Utility.getContent(etPWD);
                if (MyUtils.isNull(etPhone, "手机号不能为空")) {
                    return;
                }
                if (MyUtils.isNull(etPWD, "密码不能为空")) {
                    return;
                }
                login(content, PWD);
                //
            }
        });
    }

    public void login(final String content, final String PWD) {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                User info = JsonUtil.fromBean((String) baseBean.getData(), "Info", User.class);
                String token = JsonUtil.fromString((String) baseBean.getData(), "token");
                Logger.d("info:" + info.getId() + "/token:" + token);
                UserSingleton.getInstance().saveUser(mContext, info, PWD, token, content);
                ToActivity(mContext, HomeActivity.class);
            }
        });
        Worker.Login(content, PWD);
    }
}
