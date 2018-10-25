package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.bean.RepayBookBean;
import com.project.my.studystarteacher.newteacher.utils.ImageUtility;
import com.project.my.studystarteacher.newteacher.utils.TimeUtil;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * Created by hasee on 2018/5/9.
 */

public class RepayBoookItemAdapter extends CommonAdapter<RepayBookBean.BagsBean> {
    ImageUtility imageUtility;

    public RepayBoookItemAdapter(Context context, int layoutId, List<RepayBookBean.BagsBean> datas) {
        super(context, layoutId, datas);
        imageUtility = new ImageUtility(R.mipmap.moren);
    }

    @Override
    protected void convert(ViewHolder viewHolder, RepayBookBean.BagsBean item, int position) {
        viewHolder.setText(R.id.bagNum, item.getSchoolbagno());
        viewHolder.setText(R.id.userName, "还书人:" + item.getXs_xming());
        viewHolder.setText(R.id.time, TimeUtil.TransTime(item.getBacktime(), "HH:mm"));
    }
}
