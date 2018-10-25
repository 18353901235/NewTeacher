package com.project.my.studystarteacher.newteacher.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.project.my.studystarteacher.newteacher.bean.LoginClassBean;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.project.my.studystarteacher.newteacher.utils.EventBusUtil;
import com.project.my.studystarteacher.newteacher.utils.EventWhatId;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_registtwo)
public class RegistTwoActivity extends BaseActivity {

    @ViewInject(R.id.name)
    private EditText name;
    @ViewInject(R.id.boy)
    private RadioButton boy;
    @ViewInject(R.id.girl)
    private RadioButton girl;
    @ViewInject(R.id.rg)
    private RadioGroup rg;
    @ViewInject(R.id.className)
    private TextView className;
    @ViewInject(R.id.btnSSign)
    private Button btnSSign;

    @Override
    protected void init() {
        final String phone = getIntent().getStringExtra("userNo");
        final String userPwd = getIntent().getStringExtra("userPwd");


        EventBus.getDefault().register(this);
        findViewById(R.id.select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToActivity(mContext, SelectClassActivity.class);
            }
        });
        btnSSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNull(name, "姓名不能为空")) {
                    return;
                }
                if (isNull(className, "班级不能为空")) {
                    return;
                }
                if (boy.isChecked() || girl.isChecked()) {
                    MiceNetWorker Worker = new MiceNetWorker(mContext);
                    Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
                        @Override
                        public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                            super.onSuccess(netWorker, netTask, baseBean);
                            ToastUtil.showLongToast(mContext, "注册成功");
                            ToActivity(mContext, LoginActivity.class);
                        }
                    });
                    Worker.register(phone, userPwd, boy.isChecked() ? "0" : "1", loginClassBean.getBjid() + "", loginClassBean.getBji(), loginClassBean.getBean().getMainSchoolNo(), loginClassBean.getBean().getPartSchoolNo(), name.getText().toString().trim());
                } else {
                    ToastUtil.showLongToast(mContext, "请选择性别");
                }

            }
        });
    }

    LoginClassBean loginClassBean;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void event(EventBusUtil Y) {
        if (Y.getMsgWhat() == EventWhatId.SELECTCLASS) {
            loginClassBean = (LoginClassBean) Y.getMsgStr();
            className.setText(loginClassBean.getBean().getSchoolName() + loginClassBean.getBji());
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
