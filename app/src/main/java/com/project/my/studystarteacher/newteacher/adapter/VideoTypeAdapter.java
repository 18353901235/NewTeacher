package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;
import android.widget.TextView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.bean.VideoType;
import com.project.my.studystarteacher.newteacher.utils.ImageUtility;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;

/**
 * Created by hasee on 2018/5/9.
 */

public class VideoTypeAdapter extends CommonAdapter<VideoType> {
    ImageUtility imageUtility;

    public VideoTypeAdapter(Context context, int layoutId, ArrayList<VideoType> datas) {
        super(context, layoutId, datas);
        imageUtility = new ImageUtility(R.mipmap.moren);
    }

    @Override
    protected void convert(ViewHolder viewHolder, VideoType item, int position) {
        viewHolder.setText(R.id.name, item.getLabel());
        TextView tv = viewHolder.getView(R.id.name);
        if (item.isCheck()) {
            tv.setBackgroundResource(R.mipmap.bg_all);
            tv.setTextColor(mContext.getResources().getColor(R.color.vk_white));
        } else {
            tv.setBackgroundResource(R.mipmap.bg_oth);
            tv.setTextColor(mContext.getResources().getColor(R.color.gray));
        }

    }
}
