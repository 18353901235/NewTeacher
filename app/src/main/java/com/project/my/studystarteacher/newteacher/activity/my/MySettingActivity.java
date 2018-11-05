package com.project.my.studystarteacher.newteacher.activity.my;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.album.PicturePickActivity;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.project.my.studystarteacher.newteacher.bean.LoginClassBean;
import com.project.my.studystarteacher.newteacher.bean.User;
import com.project.my.studystarteacher.newteacher.common.ProjectConstant;
import com.project.my.studystarteacher.newteacher.common.UserSingleton;
import com.project.my.studystarteacher.newteacher.login.LoginActivity;
import com.project.my.studystarteacher.newteacher.login.SelectClassActivity;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.project.my.studystarteacher.newteacher.utils.EventBusUtil;
import com.project.my.studystarteacher.newteacher.utils.EventWhatId;
import com.project.my.studystarteacher.newteacher.utils.ImageUtility;
import com.project.my.studystarteacher.newteacher.view.CircleImageView;
import com.zhouqiang.framework.BaseActivityManager;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.JsonUtil;
import com.zhouqiang.framework.util.TimeUtil;
import com.zhouqiang.framework.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

@ContentView(R.layout.activity_my_setting)
public class MySettingActivity extends BaseActivity {

    @ViewInject(R.id.icon)
    private CircleImageView icon;
    @ViewInject(R.id.name)
    private EditText name;
    @ViewInject(R.id.age)
    private EditText age;
    @ViewInject(R.id.birth)
    private EditText birth;
    @ViewInject(R.id.bj)
    private EditText bj;
    @ViewInject(R.id.xb)
    private EditText xb;
    @ViewInject(R.id.num)
    private TextView num;
    @ViewInject(R.id.clear)
    private LinearLayout clear;
    @ViewInject(R.id.logout)
    private LinearLayout logout;
    @ViewInject(R.id.submit)
    private Button submit;

    @Override
    protected void init() {
        //  teacher/getInfo
        EventBus.getDefault().register(this);
        getCommonTitle().setText("个人信息");
        getData();
        bj.setFocusable(false);
        birth.setFocusable(false);
        bj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToActivity(mContext, SelectClassActivity.class);
            }
        });
        findViewById(R.id.icon_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });
    }

    // 拍照
    public void takePicture() {
        Intent intent = new Intent(mContext, PicturePickActivity.class);
        intent.putExtra("pictureNum", 1);
        startActivityForResult(intent, ProjectConstant.SEL_PHOTO_REQUESTCODE);
    }

    LoginClassBean loginClassBean;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void event(EventBusUtil Y) {
        if (Y.getMsgWhat() == EventWhatId.SELECTCLASS) {
            loginClassBean = (LoginClassBean) Y.getMsgStr();
            bj.setText(loginClassBean.getBean().getSchoolName() + loginClassBean.getBji());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    User info;

    public void getData() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                info = JsonUtil.fromBean((String) baseBean.getData(), "Info", User.class);
                UserSingleton.getInstance().setSysUser(info);
                name.setText(info.getTruename());
                age.setText(info.getAge() + "");
                if (isNull(info.getBrith())) {
                    birth.setText(TimeUtil.TransTime(Long.parseLong(info.getBrith()), "yyyy-MM-dd"));
                }
                bj.setText(info.getFymcheng());
                xb.setText(info.getSex());
                UserSingleton.getInstance().getSysUser().setHeadPic(info.getHeadPic());
                new ImageUtility(R.mipmap.img_headportrait).showImage(UserSingleton.getInstance().getSysUser().getHeadPic(), icon);
            }
        });
        Worker.getInfo();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ProjectConstant.SEL_PHOTO_REQUESTCODE) {
            if (data != null && data.getStringArrayListExtra("choicePicture") != null) {
                ArrayList<String> selImage = data.getStringArrayListExtra("choicePicture");
                if (selImage != null && selImage.size() > 0) {

                    MiceNetWorker Worker = new MiceNetWorker(mContext);
                    Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
                        @Override
                        public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                            super.onSuccess(netWorker, netTask, baseBean);
                            ToastUtil.showLongToast(mContext, "上传成功");
                            getData();
                            EventBus.getDefault().post(new EventBusUtil(EventWhatId.REFRSH));

                        }
                    });
                    Worker.uploadHeadImg(selImage.get(0));
                }
            }
        }
    }

    @Event({R.id.icon, R.id.clear, R.id.logout, R.id.submit})
    private void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon:
                break;
            case R.id.clear:


                break;
            case R.id.logout:
                UserSingleton.getInstance().clearUser(mContext);
                BaseActivityManager.finishAll();
                ToActivity(mContext, LoginActivity.class);
                break;
            case R.id.submit:
                if (loginClassBean != null) {
                    return;
                }

                MiceNetWorker Worker = new MiceNetWorker(mContext);
                Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
                    @Override
                    public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                        super.onSuccess(netWorker, netTask, baseBean);
                        ToastUtil.showLongToast(mContext, "更改成功");
                    }
                });
                if (loginClassBean != null)
                    Worker.perfectInfo(loginClassBean.getBjid() + "", loginClassBean.getBji(), loginClassBean.getBean().getMainSchoolNo(), loginClassBean.getBean().getPartSchoolNo(), name.getText().toString().trim());
                break;
        }
    }
}
