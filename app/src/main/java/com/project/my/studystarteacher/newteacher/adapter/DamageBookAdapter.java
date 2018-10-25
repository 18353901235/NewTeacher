package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.bean.BookDamageBean;
import com.project.my.studystarteacher.newteacher.utils.ImageUtility;
import com.project.my.studystarteacher.newteacher.utils.TimeUtil;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;

/**
 * Created by hasee on 2018/5/9.
 */

public class DamageBookAdapter extends CommonAdapter<BookDamageBean> {
    ImageUtility imageUtility;

    public DamageBookAdapter(Context context, int layoutId, ArrayList<BookDamageBean> datas) {
        super(context, layoutId, datas);
        imageUtility = new ImageUtility(R.mipmap.moren);
    }

    @Override
    protected void convert(ViewHolder viewHolder, BookDamageBean item, int position) {
        viewHolder.setText(R.id.userName, item.getStudentName());
        viewHolder.setText(R.id.time, TimeUtil.TransTime(item.getInserttime(), "yyyy-MM-dd HH:mm"));
        viewHolder.setText(R.id.bagNum, item.getSchoolbagbhao());
        viewHolder.setText(R.id.bookName, item.getBookname());
        TextView tv = viewHolder.getView(R.id.status);
        if (item.getDamagedegree() == 1) {
            viewHolder.setText(R.id.status, "严重");
            tv.setBackgroundColor(Color.parseColor("#F1697B"));
        }
        if (item.getDamagedegree() == 2) {
            viewHolder.setText(R.id.status, "丢失");
            tv.setBackgroundColor(Color.parseColor("#EEC671"));
        }
        if (item.getDamagedegree() == 3) {
            viewHolder.setText(R.id.status, "他人");
            tv.setBackgroundColor(Color.parseColor("#93E69F"));
        }
        if (item.getDamagedegree() == 4) {
            viewHolder.setText(R.id.status, "自己");
            tv.setBackgroundColor(Color.parseColor("#C3B4EB"));

        }
        //
    }
}
