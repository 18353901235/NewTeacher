package com.project.my.studystarteacher.newteacher.login;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.project.my.studystarteacher.newteacher.utils.Utility;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_regist)
public class RegistActivity extends BaseActivity {

    @ViewInject(R.id.phone)
    private EditText phone;
    @ViewInject(R.id.pwd)
    private EditText pwd;
    @ViewInject(R.id.rePwd)
    private EditText rePwd;
    @ViewInject(R.id.btnSSign)
    private Button btnSSign;

    @Override
    protected void init() {
        btnSSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String content = Utility.getContent(phone);
                final String pwds = Utility.getContent(pwd);
                String rePwdt = Utility.getContent(rePwd);
                if (isNull(phone, "手机号不能为空")) {
                    return;
                }
                if (!Utility.isPhoneNO(content)) {
                    phone.setError("手机号格式不正确");
                    phone.requestFocus();
                    return;
                }
                if (isNull(pwd, "密码不能为空")) {
                    return;
                }
                if (pwds.length() < 6) {
                    pwd.setError("密码长度不能小于6位");
                    pwd.requestFocus();
                    return;
                }
                if (!rePwdt.equals(pwds)) {
                    rePwd.setError("密码输入不一致");
                    rePwd.requestFocus();
                    return;
                }
                MiceNetWorker Worker = new MiceNetWorker(mContext);
                Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
                    @Override
                    public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                        super.onSuccess(netWorker, netTask, baseBean);
                        Intent intent = new Intent(mContext, RegistTwoActivity.class);
                        intent.putExtra("userNo", content);
                        intent.putExtra("userPwd", pwds);
                        startActivity(intent);
                    }
                });
                Worker.validatephone(content);


            }
        });
    }

}
