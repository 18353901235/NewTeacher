package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.bean.BookDamageBean;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.project.my.studystarteacher.newteacher.utils.EventBusUtil;
import com.project.my.studystarteacher.newteacher.utils.EventWhatId;
import com.project.my.studystarteacher.newteacher.utils.TimeUtil;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.ToastUtil;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import static com.project.my.studystarteacher.newteacher.utils.CommonUtil.isNull;

/**
 * Created by hasee on 2018/5/9.
 */

public class ParentSumbitAdapter extends CommonAdapter<BookDamageBean> {
    private final ArrayList<BookDamageBean> datas;

    public ParentSumbitAdapter(Context context, int layoutId, ArrayList<BookDamageBean> datas) {
        super(context, layoutId, datas);
        this.datas = datas;
    }

    @Override
    protected void convert(ViewHolder viewHolder, final BookDamageBean item, final int position) {
        viewHolder.setText(R.id.userName, item.getStudentName());
        viewHolder.setText(R.id.time, TimeUtil.TransTime(item.getInserttime(), "yyyy-MM-dd HH:mm"));
        viewHolder.setText(R.id.bagNum, item.getSchoolbagbhao());
        viewHolder.setText(R.id.bookName, "《" + item.getBookname() + "》");
        TextView tv = viewHolder.getView(R.id.status);
        if (item.getDamagedegree() == 1) {
            viewHolder.setText(R.id.status, "严重");
        }
        if (item.getDamagedegree() == 2) {
            viewHolder.setText(R.id.status, "丢失");
        }
        if (item.getDamagedegree() == 3) {
            viewHolder.setText(R.id.status, "他人");
        }
        if (item.getDamagedegree() == 4) {
            viewHolder.setText(R.id.status, "自己");
        }
        viewHolder.getView(R.id.oneName).setVisibility(View.GONE);
        viewHolder.getView(R.id.twoName).setVisibility(View.GONE);
        if (item.getRecentBorrower().size() > 0) {
            viewHolder.getView(R.id.oneName).setVisibility(View.VISIBLE);
            viewHolder.setText(R.id.oneName, item.getRecentBorrower().get(0).getMname() + " " + item.getRecentBorrower().get(0).getMclass() + " " + TimeUtil.TransTime(item.getRecentBorrower().get(0).getMdate(), "yyyy-MM-dd HH:mm:ss"));
        }
        if (item.getRecentBorrower().size() > 1) {
            viewHolder.getView(R.id.twoName).setVisibility(View.VISIBLE);
            viewHolder.setText(R.id.twoName, item.getRecentBorrower().get(1).getMname() + " " + item.getRecentBorrower().get(1).getMclass() + " " + TimeUtil.TransTime(item.getRecentBorrower().get(1).getMdate(), "yyyy-MM-dd HH:mm:ss"));
        }

        GridView gv = viewHolder.getView(R.id.imgs);
        String damageimg = item.getDamageimg();
        ArrayList<String> strings = new ArrayList<>();
        if (!isNull(damageimg)) {
            String[] split = damageimg.split(",");
            for (String s : split) {
                strings.add(s);
            }
        }
        IvAdapter ivAdapter = new IvAdapter(mContext, R.layout.iv_f_item, strings);
        gv.setAdapter(ivAdapter);

        viewHolder.getView(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MiceNetWorker Worker = new MiceNetWorker(mContext);
                Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
                    @Override
                    public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                        super.onSuccess(netWorker, netTask, baseBean);
                        ToastUtil.showLongToast(mContext, "取消成功");
                        datas.remove(item);
                        EventBus.getDefault().post(new EventBusUtil(EventWhatId.REFRSH));
                        notifyDataSetChanged();
                    }
                });
                Worker.cancelDamage(item.getId() + "");
            }
        });

    }
}
