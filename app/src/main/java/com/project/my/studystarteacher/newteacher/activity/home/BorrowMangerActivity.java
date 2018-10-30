package com.project.my.studystarteacher.newteacher.activity.home;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.BaseVPFAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseFragment;
import com.project.my.studystarteacher.newteacher.base.BaseFragmentActivity;
import com.project.my.studystarteacher.newteacher.common.UserSingleton;
import com.project.my.studystarteacher.newteacher.fragment.brobook.BorMangFragment;
import com.project.my.studystarteacher.newteacher.fragment.brobook.BorNomalMangFragment;
import com.project.my.studystarteacher.newteacher.fragment.brobook.RepayMangFragment;
import com.project.my.studystarteacher.newteacher.fragment.brobook.WornMangFragment;

import org.greenrobot.eventbus.EventBus;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import crossoverone.statuslib.StatusUtil;

@ContentView(R.layout.activity_borrow_manger)
public class BorrowMangerActivity extends BaseFragmentActivity {
    /**
     * 加载所有fragment
     */
    @ViewInject(R.id.viewPager)
    private ViewPager linAllFragment;
    /**
     * 首页
     */
    @ViewInject(R.id.linFirstPage)
    private LinearLayout linHomePage;
    private BaseFragment mkHomeFragment;


    /**
     * 行程
     */
    @ViewInject(R.id.linMarketClass)
    private LinearLayout linMarketClass;
    private RepayMangFragment mkTravelFragment = new RepayMangFragment();

    /**
     * 我的
     */
    @ViewInject(R.id.linAboutMine)
    private LinearLayout linAboutMine;
    private WornMangFragment mkMineFragment = new WornMangFragment();

    @Override
    protected void init() {
//        //管理员老师
        if (UserSingleton.getInstance().getSysUser().getBookmanager() == 2) {
            mkHomeFragment = new BorMangFragment();
        } else {
            //普通老师
            mkHomeFragment = new BorNomalMangFragment();
        }

//        //

        ArrayList<BaseFragment> frg = new ArrayList<BaseFragment>();
        frg.add(mkHomeFragment);
        frg.add(mkTravelFragment);
        frg.add(mkMineFragment);
//        //frg.add(linMarketClass);
//
        linAllFragment.setAdapter(new BaseVPFAdapter(getSupportFragmentManager(), frg));
        linAllFragment.setOffscreenPageLimit(3);
//
        linHomePage.setSelected(true);
        linHomePage.setEnabled(false);
        initListener();
        StatusUtil.setSystemStatus(mContext, true, true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
