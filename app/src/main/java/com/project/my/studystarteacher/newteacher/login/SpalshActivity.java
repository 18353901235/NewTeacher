package com.project.my.studystarteacher.newteacher.login;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.activity.home.HomeActivity;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.project.my.studystarteacher.newteacher.bean.User;
import com.project.my.studystarteacher.newteacher.common.ProjectConstant;
import com.project.my.studystarteacher.newteacher.common.UserSingleton;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.project.my.studystarteacher.newteacher.utils.CommonUtil;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.JsonUtil;
import com.zhouqiang.framework.util.Logger;
import com.zhouqiang.framework.util.SharedPreferencesUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_spalsh)
public class SpalshActivity extends BaseActivity {
    @ViewInject(R.id.tv_v)
    private TextView tv_v;
    @ViewInject(R.id.Iv)
    private ImageView Iv;
    private PackageManager packageManager;
    private int clientVersionCode;
    private Intent intent;

    @Override
    protected void init() {
        //版本显示
        try {
            packageManager = getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(
                    getPackageName(), 0);
            String version = packInfo.versionName;
            clientVersionCode = packInfo.versionCode;
            tv_v.setText("学之星  v" + version);
//        clientVersionCode =2;
        } catch (PackageManager.NameNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        setBeginningAnimation();
    }

    private void setBeginningAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation_alpha);
        animation.setAnimationListener(new StartAnimationListener());
        Iv.startAnimation(animation);
    }

    /**
     * 类       名:StartAnimationListener
     * 主要功能:动画的事件
     */
    private class StartAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
//            if (UserSingleton.isLogin()) {
//                intent = new Intent(mContext, HomeActivity.class);
//                startActivity(intent);
//            } else {
//                intent = new Intent(mContext, LoginActivity.class);
//                startActivity(intent);
//            }
//            finish();

            toMain();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    private void toMain() {
        //ces
        int intAppStartUpTimes;
        String strAppStartUpTimes = SharedPreferencesUtil.get(this, ProjectConstant.APP_START_UP_TIMES);
        if (CommonUtil.isNull(strAppStartUpTimes)) {
            intAppStartUpTimes = 0;
        } else {
            intAppStartUpTimes = Integer.valueOf(strAppStartUpTimes);
        }
        //  判定项目启动次数
        if (intAppStartUpTimes <= 0) {
            SharedPreferencesUtil.save(mContext, ProjectConstant.APP_START_UP_TIMES, "1");
            // 跳转到引导页
            intent = new Intent(mContext, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            intAppStartUpTimes = ++intAppStartUpTimes;
            if (UserSingleton.isLogin()) {
                MiceNetWorker Worker = new MiceNetWorker(mContext);
                Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
                    @Override
                    public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                        super.onSuccess(netWorker, netTask, baseBean);
                        User info = JsonUtil.fromBean((String) baseBean.getData(), "Info", User.class);
                        String token = JsonUtil.fromString((String) baseBean.getData(), "token");
                        Logger.d("info:" + info.getId() + "/token:" + token);
                        UserSingleton.getInstance().saveUser(mContext, info, SharedPreferencesUtil.get(mContext, ProjectConstant.USER_PWD), token, SharedPreferencesUtil.get(mContext, ProjectConstant.USER_PHONE));

                        ToActivity(mContext, HomeActivity.class);
                        finish();
                    }

                    @Override
                    public void onFailed(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean, int failedType) {
                        super.onFailed(netWorker, netTask, baseBean, failedType);
                        startActivity(new Intent(mContext, LoginActivity.class));
                        finish();
                    }
                });
                Worker.Login(SharedPreferencesUtil.get(mContext, ProjectConstant.USER_PHONE), SharedPreferencesUtil.get(mContext, ProjectConstant.USER_PWD));


            } else {
                intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
                finish();
            }

            SharedPreferencesUtil.save(mContext, ProjectConstant.APP_START_UP_TIMES, String.valueOf
                    (intAppStartUpTimes));

        }
    }


}
