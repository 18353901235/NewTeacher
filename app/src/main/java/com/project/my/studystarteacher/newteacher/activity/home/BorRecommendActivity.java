package com.project.my.studystarteacher.newteacher.activity.home;


import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.BaseVPFAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseFragment;
import com.project.my.studystarteacher.newteacher.base.BaseFragmentActivity;
import com.project.my.studystarteacher.newteacher.fragment.brobook.RecommendBookFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;


@ContentView(R.layout.activity_brorecommend)
public class BorRecommendActivity extends BaseFragmentActivity {
    @ViewInject(R.id.rg)
    private RadioGroup group;
    private RecommendBookFragment test1 = new RecommendBookFragment();
    private RecommendBookFragment test2 = new RecommendBookFragment();
    private RecommendBookFragment test3 = new RecommendBookFragment();
    /**
     * 加载所有fragment
     */
    @ViewInject(R.id.viewPager)
    private ViewPager linAllFragment;

    @Override
    public void init() {
        getCommonTitle().setText("手工推荐");
        ArrayList<BaseFragment> frg = new ArrayList<BaseFragment>();
        frg.add(test1);
        frg.add(test2);
        frg.add(test3);
        linAllFragment.setAdapter(new BaseVPFAdapter(getSupportFragmentManager(), frg));
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.one) {
                    linAllFragment.setCurrentItem(0);
                    getRight().setBackground(null);
                } else if (checkedId == R.id.two) {
                    linAllFragment.setCurrentItem(1);
                    getRight().setBackgroundResource(R.mipmap.musicbk_ic_search);
                } else if (checkedId == R.id.three) {
                    getRight().setBackground(null);
                    linAllFragment.setCurrentItem(2);
                }
            }
        });
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
