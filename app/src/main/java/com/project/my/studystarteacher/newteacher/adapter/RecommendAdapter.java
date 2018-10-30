package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.bean.HandlerTJ;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.ToastUtil;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * Created by hasee on 2018/5/9.
 */

public class RecommendAdapter extends CommonAdapter<HandlerTJ.BagsBean> {
    private final int id;

    public RecommendAdapter(Context context, int layoutId, List<HandlerTJ.BagsBean> datas, int id) {
        super(context, layoutId, datas);
        this.id = id;
    }

    @Override
    protected void convert(ViewHolder viewHolder, final HandlerTJ.BagsBean item, int position) {
        GridView gridView = viewHolder.getView(R.id.gv);
        viewHolder.setText(R.id.bagNum, item.getSchoolbagbhao());
        BooksAdapter demoAdapter = new BooksAdapter(mContext, R.layout.item_recomend_list, item.getBookList());
        gridView.setAdapter(demoAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long posion) {
                //
                MiceNetWorker Worker = new MiceNetWorker(mContext);
                Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
                    @Override
                    public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                        super.onSuccess(netWorker, netTask, baseBean);
                        ToastUtil.showLongToast(mContext, "推荐成功");
                    }
                });
                Worker.makeRecommendedBagToOneStudent(id + "", item.getSchoolbagbhao());

            }
        });

    }
}
