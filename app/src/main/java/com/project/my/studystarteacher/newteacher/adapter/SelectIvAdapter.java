package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.dmcbig.mediapicker.entity.Media;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.utils.ImageUtility;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;


/**
 * Created by hasee on 2018/5/9.
 */

public class SelectIvAdapter extends CommonAdapter<Media> {
    ImageUtility imageUtility;

    public SelectIvAdapter(Context context, int layoutId, List<Media> datas) {
        super(context, layoutId, datas);
        imageUtility = new ImageUtility(R.mipmap.moren);
    }


    @Override
    protected void convert(ViewHolder viewHolder, Media item, int position) {
        ImageView iv = viewHolder.getView(R.id.iv);
        if (item.getPath().contains("http")) {
            imageUtility.showImage(item.getPath(), iv);
        } else if (item.getPath().startsWith("drawable://")) {
            imageUtility.showImage(item.getPath(), iv);
        } else {
            String storage = item.getPath().replaceAll("/storage", "storage");
            imageUtility.showImage("file://" + storage, iv);
        }
    }

}
