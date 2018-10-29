package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.bean.DyNamicBean;
import com.project.my.studystarteacher.newteacher.utils.ImageUtility;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * Created by hasee on 2018/5/9.
 */

public class CommentListAdapter extends CommonAdapter<DyNamicBean.CommentsBean> {
    ImageUtility imageUtility;

    public CommentListAdapter(Context context, int layoutId, List<DyNamicBean.CommentsBean> datas) {
        super(context, layoutId, datas);
        imageUtility = new ImageUtility(R.mipmap.moren);
    }

    @Override
    protected void convert(ViewHolder viewHolder, DyNamicBean.CommentsBean item, int position) {
        viewHolder.setText(R.id.userName, item.getName() + ":");
        viewHolder.setText(R.id.content, item.getContents());

    }
}
