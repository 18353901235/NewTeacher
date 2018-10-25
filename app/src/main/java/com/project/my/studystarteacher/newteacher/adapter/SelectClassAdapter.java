package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.bean.LoginClassBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by hasee on 2018/5/9.
 */

public class SelectClassAdapter extends CommonAdapter<LoginClassBean> {
    public SelectClassAdapter(Context context, int layoutId, List<LoginClassBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, LoginClassBean item, int position) {
        viewHolder.setText(R.id.tv_className, item.getBji());
    }
}
