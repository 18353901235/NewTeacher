package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.bean.LoginClassBean;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;

/**
 * Created by hasee on 2018/5/9.
 */

public class BookBormangerAdapter extends CommonAdapter<LoginClassBean> {
    private int[] borIds = new int[]{R.mipmap.borrow_bg1, R.mipmap.borrow_bg2, R.mipmap.borrow_bg3, R.mipmap.borrow_bg4, R.mipmap.borrow_bg5, R.mipmap.borrow_bg6, R.mipmap.borrow_bg7, R.mipmap.borrow_bg8};

    public BookBormangerAdapter(Context context, int layoutId, ArrayList<LoginClassBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, LoginClassBean item, int position) {
        ImageView iv = viewHolder.getView(R.id.iv);
        iv.setBackgroundResource(borIds[position % 8]);
        viewHolder.setText(R.id.clazz_tv, item.getNjname() + item.getBji());

    }
}
