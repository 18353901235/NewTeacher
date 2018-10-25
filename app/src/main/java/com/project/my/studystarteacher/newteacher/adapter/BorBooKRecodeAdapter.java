package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.bean.BorBookRecodeBean;
import com.project.my.studystarteacher.newteacher.utils.ImageUtility;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hasee on 2018/5/9.
 */

public class BorBooKRecodeAdapter extends CommonAdapter<BorBookRecodeBean> {
    ImageUtility imageUtility;

    public BorBooKRecodeAdapter(Context context, int layoutId, ArrayList<BorBookRecodeBean> datas) {
        super(context, layoutId, datas);
        imageUtility = new ImageUtility(R.mipmap.moren);
    }

    @Override
    protected void convert(ViewHolder viewHolder, BorBookRecodeBean item, int position) {
        viewHolder.setText(R.id.userName, item.getStudentname());
        viewHolder.setText(R.id.bj, item.getClassname());
        viewHolder.setText(R.id.bagNum, item.getBorrowuser() + "");
        List<BorBookRecodeBean.BookListBean> bookList = item.getBookList();
        TextView tv = viewHolder.getView(R.id.oneName);
        TextView tv2 = viewHolder.getView(R.id.twoName);
        TextView tv3 = viewHolder.getView(R.id.threeName);

        if (bookList.size() > 0) {
            tv.setVisibility(View.VISIBLE);
            tv.setText("《" + bookList.get(0).getBookname() + "》");
        }
        if (bookList.size() > 1) {
            tv2.setVisibility(View.VISIBLE);
            tv2.setText("《" + bookList.get(1).getBookname() + "》");
        }
        if (bookList.size() > 2) {
            tv3.setVisibility(View.VISIBLE);
            tv3.setText("《" + bookList.get(2).getBookname() + "》");
        }
        viewHolder.setText(R.id.time, "借阅时间： " + item.getBorrowsuretime());

    }
}
