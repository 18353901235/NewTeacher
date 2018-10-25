package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.bean.AudioBook;
import com.project.my.studystarteacher.newteacher.utils.ImageUtility;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;

/**
 * Created by hasee on 2018/5/9.
 */

public class AudioBookAdapter extends CommonAdapter<AudioBook> {
    ImageUtility imageUtility;

    public AudioBookAdapter(Context context, int layoutId, ArrayList<AudioBook> datas) {
        super(context, layoutId, datas);
        imageUtility = new ImageUtility(R.mipmap.moren);
    }

    @Override
    protected void convert(ViewHolder viewHolder, AudioBook item, int position) {
        viewHolder.setText(R.id.vviews, item.getAgegroup() + " | " + item.getBookcategory());
        imageUtility.showImage(item.getBooklogourl(), (ImageView) viewHolder.getView(R.id.iv));
        viewHolder.setText(R.id.vName, item.getBookname());

    }
}
