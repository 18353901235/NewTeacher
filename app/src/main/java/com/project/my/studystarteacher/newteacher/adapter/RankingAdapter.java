package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.bean.RankingBean;
import com.project.my.studystarteacher.newteacher.utils.ImageUtility;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;

/**
 * Created by hasee on 2018/5/9.
 */

public class RankingAdapter extends CommonAdapter<RankingBean> {
    ImageUtility imageUtility;

    public RankingAdapter(Context context, int layoutId, ArrayList<RankingBean> datas) {
        super(context, layoutId, datas);
        imageUtility = new ImageUtility(R.mipmap.img_headportrait);
    }

    @Override
    protected void convert(ViewHolder viewHolder, RankingBean item, int position) {
        viewHolder.setText(R.id.userName, item.getXs_xming());
        viewHolder.setText(R.id.num, item.getRanking() + "");
        viewHolder.setText(R.id.sco, item.getCount() + "");
        //  viewHolder.setText(R.id.address, item.get)

        imageUtility.showImage(item.getXs_pic(), (ImageView) viewHolder.getView(R.id.iv));
    }
}
