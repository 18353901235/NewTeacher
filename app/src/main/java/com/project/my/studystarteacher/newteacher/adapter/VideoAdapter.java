package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.bean.ExpertLecture;
import com.project.my.studystarteacher.newteacher.utils.ImageUtility;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;

/**
 * Created by hasee on 2018/5/6.
 */

public class VideoAdapter extends CommonAdapter<ExpertLecture> {
    ImageUtility imageUtility;
    private int ids[] = new int[]{
            R.mipmap.home_ic_music, R.mipmap.home_ic_activity, R.mipmap.home_ic_anchor, R.mipmap.home_ic_borrow

    };

    public VideoAdapter(Context context, int layoutId, ArrayList<ExpertLecture> datas) {
        super(context, layoutId, datas);
        imageUtility = new ImageUtility(R.mipmap.moren);
    }

    @Override
    protected void convert(ViewHolder viewHolder, ExpertLecture item, int position) {
        viewHolder.setText(R.id.title, item.getTitle());
        viewHolder.setText(R.id.type, "主讲：" + item.getTopic());
        viewHolder.setText(R.id.teacher, "主讲人：" + item.getLecturer());
        viewHolder.setText(R.id.num, item.getPlay_Count() + "次");
        imageUtility.showImage(item.getCover(), (ImageView) viewHolder.getView(R.id.iv));
    }
}
