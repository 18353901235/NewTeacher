package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.bean.YaoSign;
import com.project.my.studystarteacher.newteacher.utils.ImageUtility;
import com.project.my.studystarteacher.newteacher.utils.TimeUtil;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;

/**
 * Created by hasee on 2018/5/6.
 */

public class HomeStatAdapter extends CommonAdapter<YaoSign> {
    ImageUtility imageUtility;
    private int ids[] = new int[]{
            R.mipmap.home_ic_music, R.mipmap.home_ic_activity, R.mipmap.home_ic_anchor, R.mipmap.home_ic_borrow

    };

    public HomeStatAdapter(Context context, int layoutId, ArrayList<YaoSign> datas) {
        super(context, layoutId, datas);
        imageUtility = new ImageUtility(R.mipmap.moren);
    }

    @Override
    protected void convert(ViewHolder viewHolder, YaoSign item, int position) {
        viewHolder.setText(R.id.userName, item.getName());
        viewHolder.setText(R.id.time, TimeUtil.TransTime(item.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
        viewHolder.setText(R.id.money, item.getScore() + "人");
    }
}
