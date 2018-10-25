package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;
import android.view.View;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.bean.AudioRecodeBean;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.project.my.studystarteacher.newteacher.utils.ImageUtility;
import com.project.my.studystarteacher.newteacher.utils.TimeUtil;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.ToastUtil;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;

/**
 * Created by hasee on 2018/5/9.
 */

public class AudioRecodeAdapter extends CommonAdapter<AudioRecodeBean> {
    private final ArrayList<AudioRecodeBean> datas;
    ImageUtility imageUtility;

    public AudioRecodeAdapter(Context context, int layoutId, ArrayList<AudioRecodeBean> datas) {
        super(context, layoutId, datas);
        this.datas = datas;
        imageUtility = new ImageUtility(R.mipmap.moren);
    }

    @Override
    protected void convert(ViewHolder viewHolder, final AudioRecodeBean item, int position) {
        viewHolder.setText(R.id.bookName, "《" + item.getBookname() + "》");
        viewHolder.setText(R.id.time, TimeUtil.TransTime(item.getInsertdate(), "yyyy-MM-dd HH:mm:ss"));
        viewHolder.getView(R.id.del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MiceNetWorker Worker = new MiceNetWorker(mContext);
                Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
                    @Override
                    public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                        super.onSuccess(netWorker, netTask, baseBean);
                        ToastUtil.showLongToast(mContext, "删除成功");
                        datas.remove(item);
                        notifyDataSetChanged();
                    }
                });
                Worker.delAudio(item.getId() + "");
            }
        });
    }
}
