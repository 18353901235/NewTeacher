package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;
import android.text.SpannableString;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.bean.PinglunBean;
import com.project.my.studystarteacher.newteacher.utils.FaceConversionUtil;
import com.project.my.studystarteacher.newteacher.utils.ImageUtility;
import com.zhouqiang.framework.util.TimeUtil;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;

/**
 * Created by hasee on 2018/5/6.
 */

public class VideoChartAdapter extends CommonAdapter<PinglunBean> {
    ImageUtility imageUtility;
    private int ids[] = new int[]{
            R.mipmap.home_ic_music, R.mipmap.home_ic_activity, R.mipmap.home_ic_anchor, R.mipmap.home_ic_borrow

    };

    public VideoChartAdapter(Context context, int layoutId, ArrayList<PinglunBean> datas) {
        super(context, layoutId, datas);
        imageUtility = new ImageUtility(R.mipmap.moren);
    }

    @Override
    protected void convert(ViewHolder viewHolder, PinglunBean item, int position) {
        SpannableString spannable =
                FaceConversionUtil.getInstace().getExpressionString(mContext,
                        item.getContent());
        TextView tv = viewHolder.getView(R.id.content_tv);
        tv.setText(spannable);
        viewHolder.setText(R.id.name, item.getUsername());
        viewHolder.setText(R.id.time, TimeUtil.TransTime(item.getCreate_time(), "yyyy-MM-dd HH:mm:ss"));
        imageUtility.showImage(item.getHeadImg(), (ImageView) viewHolder.getView(R.id.iv));
    }
}
