package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.utils.ImageUtility;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * Created by hasee on 2018/5/6.
 */

public class HomeLoveAdapter extends CommonAdapter<String> {
    ImageUtility imageUtility;
    private int ids[] = new int[]{
            R.mipmap.home_ic_music, R.mipmap.home_ic_activity, R.mipmap.home_ic_anchor, R.mipmap.home_ic_borrow

    };

    public HomeLoveAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
        imageUtility = new ImageUtility(R.mipmap.moren);
    }

    @Override
    protected void convert(ViewHolder viewHolder, String item, int position) {
    }
}
