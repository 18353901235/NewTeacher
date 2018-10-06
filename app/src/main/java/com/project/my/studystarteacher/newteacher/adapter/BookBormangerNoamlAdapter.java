package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.activity.home.BorRecommendActivity;
import com.project.my.studystarteacher.newteacher.activity.home.HistroyCommendActivity;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * Created by hasee on 2018/5/9.
 */

public class BookBormangerNoamlAdapter extends CommonAdapter<String> {

    public BookBormangerNoamlAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, String item, int position) {
        viewHolder.getView(R.id.recommend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, BorRecommendActivity.class);
                mContext.startActivity(intent);
            }
        });
        viewHolder.getView(R.id.hist_recommend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, HistroyCommendActivity.class);
                mContext.startActivity(intent);
            }
        });
    }
}
