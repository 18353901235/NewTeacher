package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.utils.ImageUtility;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;


/**
 * Created by hasee on 2018/5/9.
 */

public class IvAdapter extends CommonAdapter<String> {
    ImageUtility imageUtility;

    public IvAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
        imageUtility = new ImageUtility(R.mipmap.moren);
    }


    @Override
    protected void convert(ViewHolder viewHolder, String item, int position) {
        ImageView iv = viewHolder.getView(R.id.iv);
        if (item.contains("http")) {
            imageUtility.showImage(item, iv);
        }
    }

}
