package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.bean.MeRepayBook;
import com.project.my.studystarteacher.newteacher.utils.EventBusUtil;
import com.project.my.studystarteacher.newteacher.utils.EventWhatId;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by hasee on 2018/5/9.
 */

public class BookMeRepay_itemAdapter extends CommonAdapter<MeRepayBook> {
    private final Context context;
    private final ArrayList<String> checkList;
    String timeS;

    public BookMeRepay_itemAdapter(Context context, int layoutId, ArrayList<MeRepayBook> datas, ArrayList<String> checkList) {
        super(context, layoutId, datas);
        this.context = context;
        this.checkList = checkList;
        timeS = "";
    }


    @Override
    protected void convert(ViewHolder viewHolder, final MeRepayBook item, int position) {
        View time_ll = viewHolder.getView(R.id.time_ll);
        time_ll.setVisibility(View.GONE);
        viewHolder.setText(R.id.time, item.getReadTime());
        viewHolder.setText(R.id.bagNum, item.getSchoolbagNo());
        viewHolder.setText(R.id.name, item.getXs_xming());
        if (!timeS.equals(item.getReadTime())) {
            time_ll.setVisibility(View.VISIBLE);
        }
        final ImageView imageView = viewHolder.getView(R.id.check_iv);
        final ImageView all = viewHolder.getView(R.id.ivAll);
        all.setBackgroundResource(R.mipmap.checkbtn_select);
        for (String s : checkList) {
            if (item.getReadTime().equals(s)) {
                all.setBackgroundResource(R.mipmap.check_btn);
            }
        }
        if (item.isCheck()) {
            imageView.setBackgroundResource(R.mipmap.check_btn);
        } else {
            imageView.setBackgroundResource(R.mipmap.checkbtn_select);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setCheck(!item.isCheck());
                EventBus.getDefault().post(new EventBusUtil(EventWhatId.MEREPAY, item));
            }
        });
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (all.getTag().equals("1")) {

                    all.setTag("0");
                    checkList.remove(item.getReadTime());
                    EventBus.getDefault().post(new EventBusUtil(EventWhatId.MEREPAYUNCHECKED, item));
                } else {
                    all.setTag("1");
                    checkList.add(item.getReadTime());
                    EventBus.getDefault().post(new EventBusUtil(EventWhatId.MEREPAYCHECKED, item));
                }
            }
        });
        timeS = item.getReadTime();
    }
}
