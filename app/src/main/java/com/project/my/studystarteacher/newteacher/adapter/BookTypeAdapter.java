package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;
import android.widget.TextView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.bean.BookType;
import com.project.my.studystarteacher.newteacher.utils.ImageUtility;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;

/**
 * Created by hasee on 2018/5/9.
 */

public class BookTypeAdapter extends CommonAdapter<BookType> {
    ImageUtility imageUtility;

    public BookTypeAdapter(Context context, int layoutId, ArrayList<BookType> datas) {
        super(context, layoutId, datas);
        //imageUtility = new ImageUtility(R.mipmap.moren);
    }

    @Override
    protected void convert(ViewHolder viewHolder, BookType item, int position) {
        viewHolder.setText(R.id.vName, item.getDictionaryname());
        TextView tv = viewHolder.getView(R.id.vName);
        if (item.isCheck()) {
            tv.setBackgroundResource(R.drawable.home_cic_orange10px);
        } else {
            tv.setBackgroundResource(R.drawable.home_cic_graybtnall);
        }
    }
}
