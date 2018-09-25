package com.project.my.studystarteacher.newteacher.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.project.my.studystarteacher.newteacher.base.BaseFragment;

import java.util.ArrayList;

/***
 * @author zq更换合格和电话
 */
public class BaseVPFAdapter extends FragmentPagerAdapter {
    private ArrayList<BaseFragment> fragmentList;

    public BaseVPFAdapter(FragmentManager fm,
                          ArrayList<BaseFragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new Fragment();
        fragment = fragmentList.get(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

}
