package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;
import android.view.View;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.bean.People;
import com.project.my.studystarteacher.newteacher.utils.Utility;
import com.project.my.studystarteacher.newteacher.view.AllListView;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;

/**
 * Created by hasee on 2018/5/9.
 */

public class MyContactAdapter extends CommonAdapter<People> {
    public MyContactAdapter(Context context, int layoutId, ArrayList<People> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, final People item, int position) {
        AllListView listView = viewHolder.getView(R.id.list);
        viewHolder.setText(R.id.userName, item.getName());
        viewHolder.setText(R.id.phone, item.getPhone());
        viewHolder.getView(R.id.call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.makePhone(mContext, item.getPhone());
            }
        });
//        DemoAdapter adapter = new DemoAdapter(mContext, R.layout.contact_list_item, TempSourceSupply.getCZSData());
//        listView.setAdapter(adapter);
    }
}
