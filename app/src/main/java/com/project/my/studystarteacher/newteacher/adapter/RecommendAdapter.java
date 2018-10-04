package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;
import android.widget.GridView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.common.TempSourceSupply;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * Created by hasee on 2018/5/9.
 */

public class RecommendAdapter extends CommonAdapter<String> {
    public RecommendAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, String item, int position) {
        GridView gridView = viewHolder.getView(R.id.gv);
        DemoAdapter demoAdapter = new DemoAdapter(mContext, R.layout.item_recomend_list, TempSourceSupply.getCZSData());
        gridView.setAdapter(demoAdapter);
    }
}
