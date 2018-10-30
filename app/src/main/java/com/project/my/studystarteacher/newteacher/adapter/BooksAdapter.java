package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.bean.HandlerTJ;
import com.project.my.studystarteacher.newteacher.utils.ImageUtility;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * Created by hasee on 2018/5/9.
 */

public class BooksAdapter extends CommonAdapter<HandlerTJ.BagsBean.BookListBean> {
    ImageUtility imageUtility;

    public BooksAdapter(Context context, int layoutId, List<HandlerTJ.BagsBean.BookListBean> datas) {
        super(context, layoutId, datas);
        imageUtility = new ImageUtility(R.mipmap.moren);
    }

    @Override
    protected void convert(ViewHolder viewHolder, HandlerTJ.BagsBean.BookListBean item, int position) {
        imageUtility.showImage(item.getBooklogourl(), (ImageView) viewHolder.getView(R.id.iv));
        viewHolder.setText(R.id.vName, item.getBookname());
    }
}
