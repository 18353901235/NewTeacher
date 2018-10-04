package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.widget.TextView;

import com.project.my.studystarteacher.newteacher.R;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * Created by hasee on 2018/5/9.
 */

public class BookBoMangPeoAdapter extends CommonAdapter<String> {
    public BookBoMangPeoAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, String item, int position) {
        TextView book1 = viewHolder.getView(R.id.book1);
        TextView book2 = viewHolder.getView(R.id.book2);
        TextView book3 = viewHolder.getView(R.id.book3);
        setLine(book1);
        setLine(book2);
        setLine(book3);

    }

    public void setLine(TextView tv) {
        tv.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        tv.getPaint().setAntiAlias(true);//这里要加抗锯齿
    }
}
