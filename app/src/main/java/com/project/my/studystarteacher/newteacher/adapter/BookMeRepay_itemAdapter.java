package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;
import android.view.View;

import com.project.my.studystarteacher.newteacher.R;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * Created by hasee on 2018/5/9.
 */

public class BookMeRepay_itemAdapter extends CommonAdapter<String> {
    public BookMeRepay_itemAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, String item, int position) {
        View time_ll = viewHolder.getView(R.id.time_ll);
        time_ll.setVisibility(View.GONE);
        View name_ll = viewHolder.getView(R.id.name_ll);
        if (position % 4 == 0) {
            time_ll.setVisibility(View.VISIBLE);
        }
    }
}
