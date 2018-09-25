package com.zhouqiang.framework;

import android.support.v4.app.Fragment;

import java.util.ArrayList;

/**
 * Created by jia-changyu on 2016/3/22.
 */
public class SanmiFragmentManager {
    private static ArrayList<Fragment> mFragments;

    public static Fragment getFragmentByClass(Class<?> cl) {
        ArrayList<Fragment> temp = getFragmentsByClass(cl);
        return (temp.size() != 0) ? temp.get(temp.size() - 1) : null;
    }

    public static ArrayList<Fragment> getFragmentsByClass(Class<?> cl) {
        ArrayList<Fragment> temp = new ArrayList<>();
        if (mFragments != null && mFragments.size() > 0) {
            for (Fragment fg : mFragments) {
                if (fg.getClass().equals(cl))
                    temp.add(fg);
            }
        }
        return temp;
    }

    public static void add(Fragment fragment) {
        if (mFragments == null)
            mFragments = new ArrayList<Fragment>();
        if (!mFragments.contains(fragment))
            mFragments.add(fragment);
    }

    public static void remove(Fragment fragment) {
        if (mFragments != null)
            mFragments.remove(fragment);
    }

    public static Fragment getLastFragment() {
        int size = mFragments == null ? 0 : mFragments.size();
        if (size > 0) {
            return mFragments.get(size - 1);
        } else {
            return null;
        }
    }
}
