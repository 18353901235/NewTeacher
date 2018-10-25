package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;
import android.widget.GridView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.bean.RepayBookBean;
import com.project.my.studystarteacher.newteacher.utils.ImageUtility;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;

/**
 * Created by hasee on 2018/5/9.
 */

public class RepayBookAdapter extends CommonAdapter<RepayBookBean> {
    ImageUtility imageUtility;

    public RepayBookAdapter(Context context, int layoutId, ArrayList<RepayBookBean> datas) {
        super(context, layoutId, datas);
        imageUtility = new ImageUtility(R.mipmap.moren);
    }

    @Override
    protected void convert(ViewHolder viewHolder, RepayBookBean item, int position) {
        viewHolder.setText(R.id.time, item.getBtime());
        GridView gridView = viewHolder.getView(R.id.gv);
        RepayBoookItemAdapter demoAdapter = new RepayBoookItemAdapter(mContext, R.layout.repaybook_item, item.getBags());
        gridView.setAdapter(demoAdapter);
    }
}
