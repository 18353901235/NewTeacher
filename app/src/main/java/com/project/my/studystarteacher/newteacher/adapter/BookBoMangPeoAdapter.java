package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.activity.home.WornSignActivity;
import com.project.my.studystarteacher.newteacher.bean.MangOBJ;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.ToastUtil;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hasee on 2018/5/9.
 */

public class BookBoMangPeoAdapter extends CommonAdapter<MangOBJ> {
    private final ArrayList<MangOBJ> datas;

    public BookBoMangPeoAdapter(Context context, int layoutId, ArrayList<MangOBJ> datas) {
        super(context, layoutId, datas);
        this.datas = datas;
    }

    @Override
    protected void convert(ViewHolder viewHolder, final MangOBJ item, final int position) {
        TextView book1 = viewHolder.getView(R.id.book1);
        TextView book2 = viewHolder.getView(R.id.book2);
        TextView book3 = viewHolder.getView(R.id.book3);
        List<MangOBJ.BooksBean> books = item.getBooks();
        final Intent intent = new Intent(mContext, WornSignActivity.class);
        intent.putExtra("data", item);
        if (books.size() > 0) {
            book1.setVisibility(View.VISIBLE);
            setLine(book1);
            book1.setText(books.get(0).getBookname());

            book1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent.putExtra("data2", 0);
                    mContext.startActivity(intent);
                }
            });
        }
        if (books.size() > 1) {
            book2.setVisibility(View.VISIBLE);
            setLine(book2);
            book2.setText(books.get(1).getBookname());
            book2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent.putExtra("data2", 1);
                    mContext.startActivity(intent);
                }
            });
        }
        if (books.size() > 2) {
            book3.setVisibility(View.VISIBLE);
            setLine(book3);
            book3.setText(books.get(2).getBookname());
            book3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent.putExtra("data2", 2);
                    mContext.startActivity(intent);
                }
            });
        }
        viewHolder.setText(R.id.userName, item.getXs_xming());
        viewHolder.setText(R.id.bagNum, item.getBag().getSchoolbagbhao());
        viewHolder.setText(R.id.num, item.getDamage_times() + "");
        viewHolder.setText(R.id.vip, "会员到期：" + item.getVipEndtime() + "");
        viewHolder.setText(R.id.borTime, "借阅时间：" + item.getRecommentTime() + "");
        viewHolder.getView(R.id.del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // bookBorrowReadCtrl/removeBlackList
                update(item.getXs_id() + "", position);
            }
        });
    }

    public void update(String id, final int position) {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                ToastUtil.showLongToast(mContext, "移除成功");
                datas.remove(position);
                notifyDataSetChanged();
            }
        });
        Worker.removeBlackList(id);
    }

    public void setLine(TextView tv) {
        tv.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        tv.getPaint().setAntiAlias(true);//这里要加抗锯齿
    }
}
