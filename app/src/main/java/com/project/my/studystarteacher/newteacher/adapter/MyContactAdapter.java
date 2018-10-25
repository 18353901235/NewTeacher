package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.common.TempSourceSupply;
import com.project.my.studystarteacher.newteacher.view.AllListView;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * Created by hasee on 2018/5/9.
 */

public class MyContactAdapter extends CommonAdapter<String> {
    public MyContactAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, String item, int position) {
        AllListView listView = viewHolder.getView(R.id.list);
        DemoAdapter adapter = new DemoAdapter(mContext, R.layout.contact_list_item, TempSourceSupply.getCZSData());
        listView.setAdapter(adapter);
    }
}
