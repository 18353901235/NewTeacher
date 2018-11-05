package com.project.my.studystarteacher.newteacher.activity.home;


import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.BaseVPFAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseFragment;
import com.project.my.studystarteacher.newteacher.base.BaseFragmentActivity;
import com.project.my.studystarteacher.newteacher.fragment.BagListMangerFragment;
import com.project.my.studystarteacher.newteacher.fragment.brobook.BookBorRecordFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

//BorNomalMangFragment
@ContentView(R.layout.fragment_open_manger)
public class OpenMangerActivity extends BaseFragmentActivity {
    @ViewInject(R.id.rg)
    private RadioGroup group;
    private BagListMangerFragment test1 = new BagListMangerFragment();
    private BookBorRecordFragment test2 = new BookBorRecordFragment();
    /**
     * 加载所有fragment
     */
    @ViewInject(R.id.viewPager)
    private ViewPager linAllFragment;

    @Override
    public void init() {
        getCommonTitle().setText("开箱管理");
        ArrayList<BaseFragment> frg = new ArrayList<BaseFragment>();
        frg.add(test1);
        frg.add(test2);
        findViewById(R.id.three).setVisibility(View.GONE);
        linAllFragment.setAdapter(new BaseVPFAdapter(getSupportFragmentManager(), frg));
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.one) {
                    linAllFragment.setCurrentItem(0);
                    getRight().setBackground(null);
                } else if (checkedId == R.id.two) {
                    linAllFragment.setCurrentItem(1);
                    //  getRight().setBackgroundResource(R.mipmap.musicbk_ic_search);
                } else if (checkedId == R.id.three) {
                    getRight().setBackground(null);
                    linAllFragment.setCurrentItem(2);
                }

            }
        });

        //getStudentBorrowList
        linAllFragment.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    group.check(R.id.one);
                } else if (position == 1) {
                    group.check(R.id.two);
                } else if (position == 2) {
                    group.check(R.id.three);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


}
