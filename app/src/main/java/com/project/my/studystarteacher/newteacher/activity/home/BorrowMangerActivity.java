package com.project.my.studystarteacher.newteacher.activity.home;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.BaseVPFAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseFragment;
import com.project.my.studystarteacher.newteacher.base.BaseFragmentActivity;
import com.project.my.studystarteacher.newteacher.fragment.MyFragment;
import com.project.my.studystarteacher.newteacher.fragment.VideoFragment;
import com.project.my.studystarteacher.newteacher.fragment.brobook.BorMangFragment;

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
    private BorMangFragment mkHomeFragment = new BorMangFragment();
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
