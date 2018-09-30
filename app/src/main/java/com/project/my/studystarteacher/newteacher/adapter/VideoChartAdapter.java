package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;
import android.text.SpannableString;
import android.widget.TextView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.utils.FaceConversionUtil;
import com.project.my.studystarteacher.newteacher.utils.ImageUtility;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * Created by hasee on 2018/5/6.
 */

public class VideoChartAdapter extends CommonAdapter<String> {
    ImageUtility imageUtility;
    private int ids[] = new int[]{
            R.mipmap.home_ic_music, R.mipmap.home_ic_activity, R.mipmap.home_ic_anchor, R.mipmap.home_ic_borrow

    };

    public VideoChartAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
        imageUtility = new ImageUtility(R.mipmap.moren);
    }

    @Override
    protected void convert(ViewHolder viewHolder, String item, int position) {
        SpannableString spannable =
                FaceConversionUtil.getInstace().getExpressionString(mContext,
                        "哈哈哈[调皮][发怒][调皮][调皮][调皮][惊讶][呲牙][调皮][发怒][调皮][流汗][擦汗][月亮][右哼哼][右哼哼][鼓掌][瓢虫]");
        TextView tv = viewHolder.getView(R.id.content_tv);
        tv.setText(spannable);
    }
}
