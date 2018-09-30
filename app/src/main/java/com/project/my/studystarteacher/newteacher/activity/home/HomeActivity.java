package com.project.my.studystarteacher.newteacher.activity.home;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.project.my.studystarteacher.newteacher.MiceApplication;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.BaseVPFAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseFragment;
import com.project.my.studystarteacher.newteacher.base.BaseFragmentActivity;
import com.project.my.studystarteacher.newteacher.common.ProjectConstant;
import com.project.my.studystarteacher.newteacher.common.UserSingleton;
import com.project.my.studystarteacher.newteacher.fragment.HomeFragment;
import com.project.my.studystarteacher.newteacher.fragment.MyFragment;
import com.project.my.studystarteacher.newteacher.fragment.VideoFragment;
import com.project.my.studystarteacher.newteacher.utils.ToastUtility;
import com.zhouqiang.framework.BaseActivityManager;
import com.zhouqiang.framework.util.Logger;
import com.zhouqiang.framework.util.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import cn.jpush.android.api.JPushInterface;
import crossoverone.statuslib.StatusUtil;


@ContentView(R.layout.activity_mainhome)
public class HomeActivity extends BaseFragmentActivity {
    /***
     * 是否退出
     */
    private static Boolean isExit = false;
    /**
     * 加载所有fragment
     */
    @ViewInject(R.id.linAllFragment)
    private ViewPager linAllFragment;
    /**
     * 首页
     */
    @ViewInject(R.id.linFirstPage)
    private LinearLayout linHomePage;
    private HomeFragment mkHomeFragment = new HomeFragment();
    /**
     * 行程
     */
    @ViewInject(R.id.linMarketClass)
    private LinearLayout linMarketClass;
    private VideoFragment mkTravelFragment = new VideoFragment();

    /**
     * 我的
     */
    @ViewInject(R.id.linAboutMine)
    private LinearLayout linAboutMine;
    private MyFragment mkMineFragment = new MyFragment();


    @Override
    protected void init() {
        ArrayList<BaseFragment> frg = new ArrayList<BaseFragment>();
        frg.add(mkHomeFragment);
        frg.add(mkTravelFragment);
        frg.add(mkMineFragment);
        //frg.add(linMarketClass);

        linAllFragment.setAdapter(new BaseVPFAdapter(getSupportFragmentManager(), frg));
        linAllFragment.setOffscreenPageLimit(4);

        linHomePage.setSelected(true);
        linHomePage.setEnabled(false);
        initListener();
        getVersion();
        StatusUtil.setSystemStatus(mContext, true, true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.d("onResume");
        if (UserSingleton.isLogin()) {
            JPushInterface.init(mContext);             // 初始化 JPush
            //       Logger.d("isPushStopped:" + JPushInterface.isPushStopped(mContext));
            if (JPushInterface.isPushStopped(mContext)) {
                JPushInterface.resumePush(mContext);//恢复极光推送
                String s = SharedPreferencesUtil.get(MiceApplication.getInstance(), ProjectConstant.USERID);
                if (!isNull(s)) {
                    MiceApplication.getInstance().setAlias(s.replaceAll("-", ""));
                }

            } else {
                String s = SharedPreferencesUtil.get(MiceApplication.getInstance(), ProjectConstant.USERID);
                if (!isNull(s)) {
                    MiceApplication.getInstance().setAlias(s.replaceAll("-", ""));
                }
                Logger.d("alisa;" + s);
            }
        } else {

        }
    }


    @Override
    public void onBackPressed() {
        exitDoubleClick();
    }

    /***
     * 双击退出
     */
    private void exitDoubleClick() {
        Timer exitTime = null;
        if (isExit == false) {
            isExit = true;
            ToastUtility.showToast(mContext, "再按一次退出程序");
            exitTime = new Timer();
            exitTime.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 3000);
        } else {

            ToastUtility.cancelToast();
            BaseActivityManager.finishAll();
            // System.exit(0);
        }
    }

    private void initListener() {
        linHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //       StatusUtil.setSystemStatus(mContext, true, true);
                clearAllSelect();
                linAllFragment.setCurrentItem(0, false);
//                tabMainEnum = MkTabMainEnum.FIRST_PAGE;
//                setFragmentSelect();
                linHomePage.setSelected(true);
                linHomePage.setEnabled(false);
            }
        });
        linMarketClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //      StatusUtil.setSystemStatus(mContext, true, true);
                clearAllSelect();
                linAllFragment.setCurrentItem(1, false);
//                tabMainEnum = MkTabMainEnum.MARKET_ClASS;
//                setFragmentSelect();
                linMarketClass.setSelected(true);
                linMarketClass.setEnabled(false);
            }
        });

        linAboutMine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //      StatusUtil.setSystemStatus(mContext, true, true);
                clearAllSelect();
                linAllFragment.setCurrentItem(2, false);
//                tabMainEnum = MkTabMainEnum.ABOUT_MINE;
//                setFragmentSelect();
                linAboutMine.setSelected(true);
                linAboutMine.setEnabled(false);
            }
        });
    }

    private void getVersion() {
//        Intent service = new Intent(mContext, UpdateApkService.class);
//        service.putExtra("isUserCheck", false);
//        startService(service);
    }

    /***
     * 清除所有选定状态
     */
    private void clearAllSelect() {
        linHomePage.setSelected(false);
        linHomePage.setEnabled(true);
        linMarketClass.setSelected(false);
        linMarketClass.setEnabled(true);
        linAboutMine.setSelected(false);
        linAboutMine.setEnabled(true);
    }
}