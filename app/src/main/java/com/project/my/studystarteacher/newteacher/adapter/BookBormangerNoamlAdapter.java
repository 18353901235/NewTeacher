package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.activity.home.BorRecommendActivity;
import com.project.my.studystarteacher.newteacher.activity.home.HistroyCommendActivity;
import com.project.my.studystarteacher.newteacher.bean.ClassBorBook;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;

/**
 * Created by hasee on 2018/5/9.
 */

public class BookBormangerNoamlAdapter extends CommonAdapter<ClassBorBook> {

    public BookBormangerNoamlAdapter(Context context, int layoutId, ArrayList<ClassBorBook> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, ClassBorBook item, int position) {

        viewHolder.setText(R.id.userName, item.getXs_xming());
        viewHolder.setText(R.id.num, item.getDamage_times() + "");
        viewHolder.setText(R.id.bor_time, "借阅时间: " + item.getRecommentTime());
        viewHolder.setText(R.id.vip, "会员到期：" + item.getVipEndtime());


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
