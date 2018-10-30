package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;
import android.view.View;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.bean.HistroyBook;
import com.project.my.studystarteacher.newteacher.utils.ImageUtility;
import com.project.my.studystarteacher.newteacher.utils.TimeUtil;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hasee on 2018/5/9.
 */

public class HistroyBookAdapter extends CommonAdapter<HistroyBook> {
    ImageUtility imageUtility;

    public HistroyBookAdapter(Context context, int layoutId, ArrayList<HistroyBook> datas) {
        super(context, layoutId, datas);
        imageUtility = new ImageUtility(R.mipmap.moren);
    }

    @Override
    protected void convert(ViewHolder viewHolder, HistroyBook item, int position) {
        viewHolder.setText(R.id.userName, item.getSchoolbagname());
        viewHolder.setText(R.id.time, TimeUtil.TransTime(item.getBorrowtime(), "MM/dd"));
        viewHolder.setText(R.id.bagNum, item.getSchoolbagbhao());
        List<HistroyBook.BooksBean> books = item.getBooks();
        viewHolder.getView(R.id.oneBook).setVisibility(View.GONE);
        viewHolder.getView(R.id.twoBook).setVisibility(View.GONE);
        viewHolder.getView(R.id.threeBook).setVisibility(View.GONE);
        if (books.size() > 0) {
            viewHolder.getView(R.id.oneBook).setVisibility(View.VISIBLE);
            viewHolder.setText(R.id.oneBook, "《" + books.get(0).getBookname() + "》");
        }
        if (books.size() > 1) {
            viewHolder.setText(R.id.twoBook, "《" + books.get(1).getBookname() + "》");
            viewHolder.getView(R.id.twoBook).setVisibility(View.VISIBLE);
        }
        if (books.size() > 2) {
            viewHolder.setText(R.id.threeBook, "《" + books.get(2).getBookname() + "》");
            viewHolder.getView(R.id.threeBook).setVisibility(View.VISIBLE);
        }
        int borrowstatus = item.getBorrowstatus();
        if (borrowstatus == 0) {
            viewHolder.setText(R.id.status, "借阅中");
        } else if (borrowstatus == 1) {
            viewHolder.setText(R.id.status, "已归还");
        } else if (borrowstatus == 2) {
            viewHolder.setText(R.id.status, "家长预约");
        }

    }
}
