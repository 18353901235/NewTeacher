package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;
import android.widget.GridView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.common.TempSourceSupply;
import com.project.my.studystarteacher.newteacher.utils.ImageUtility;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * Created by hasee on 2018/5/6.
 */

public class HomeActiviAdapter extends CommonAdapter<String> {
    ImageUtility imageUtility;

    public HomeActiviAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
        imageUtility = new ImageUtility(R.mipmap.moren);
    }

    @Override
    protected void convert(ViewHolder viewHolder, String item, int position) {
        GridView gridView = viewHolder.getView(R.id.gv);
        DemoAdapter adapter = new DemoAdapter(mContext, R.layout.iv_f_item, TempSourceSupply.getCZSData());
        gridView.setAdapter(adapter);
    }
}
