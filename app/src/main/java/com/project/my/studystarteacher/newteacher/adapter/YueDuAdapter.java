package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.bean.ReadDynamics;
import com.project.my.studystarteacher.newteacher.utils.ImageUtility;
import com.project.my.studystarteacher.newteacher.utils.TimeUtil;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * Created by hasee on 2018/5/9.
 */

public class YueDuAdapter extends CommonAdapter<ReadDynamics> {
    ImageUtility imageUtility;

    public YueDuAdapter(Context context, int layoutId, List<ReadDynamics> datas) {
        super(context, layoutId, datas);
        imageUtility = new ImageUtility(R.mipmap.moren);
    }

    @Override
    protected void convert(ViewHolder viewHolder, ReadDynamics item, int position) {
        viewHolder.setText(R.id.title, item.getTitle());
        viewHolder.setText(R.id.content, item.getDescription());
        viewHolder.setText(R.id.read_num, "浏览次数：" + item.getCount());
        viewHolder.setText(R.id.time, TimeUtil.TransTime(item.getInserttime(), "yyyy.MM.dd"));

        imageUtility.showImage(item.getContentsimg(), (ImageView) viewHolder.getView(R.id.iv));
    }
}
