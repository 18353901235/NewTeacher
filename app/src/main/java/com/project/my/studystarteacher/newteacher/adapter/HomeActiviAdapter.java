package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.bean.DyNamicBean;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.project.my.studystarteacher.newteacher.utils.ImageUtility;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.ToastUtil;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

import static com.zhouqiang.framework.util.BaseUtil.isNull;

/**
 * Created by hasee on 2018/5/6.
 */

public class HomeActiviAdapter extends CommonAdapter<DyNamicBean> {
    ImageUtility imageUtility;

    public HomeActiviAdapter(Context context, int layoutId, ArrayList<DyNamicBean> datas) {
        super(context, layoutId, datas);
        imageUtility = new ImageUtility(R.mipmap.moren);
    }

    @Override
    protected void convert(ViewHolder viewHolder, DyNamicBean item, int position) {
        GridView gridView = viewHolder.getView(R.id.gv);
        viewHolder.setText(R.id.userName, item.getName());
        viewHolder.setText(R.id.time, item.getInsertTime());
        viewHolder.setText(R.id.desc, item.getContents());
        imageUtility.showImage(item.getHeadImg(), (ImageView) viewHolder.getView(R.id.iv));
        viewHolder.getView(R.id.zan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        viewHolder.getView(R.id.share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MiceNetWorker Worker = new MiceNetWorker(mContext);
                Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
                    @Override
                    public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                        super.onSuccess(netWorker, netTask, baseBean);
                        ToastUtil.showLongToast(mContext, "点赞成功");
                    }
                });
                Worker.dynamicFunction("2", "", "", "", "");
            }
        });
        JzvdStd std = viewHolder.getView(R.id.videoplayer);
        String pics = item.getPics();
        gridView.setVisibility(View.GONE);
        std.setVisibility(View.GONE);
        if (!isNull(pics)) {

            String type = pics.substring(pics.length() - 3, pics.length());
            if (type.equals("png") || type.equals("jpg")) {
                gridView.setVisibility(View.VISIBLE);
                if (item.getAdapter() == null) {
                    String[] split = pics.split(",");
                    ArrayList<String> strings = new ArrayList<>();
                    for (String s : split) {
                        strings.add(s);
                    }
                    IvAdapter adapter = new IvAdapter(mContext, R.layout.iv_f_item, strings);
                    gridView.setAdapter(adapter);
                    item.setAdapter(adapter);
                } else {
                    gridView.setAdapter(item.getAdapter());
                }
                std.setVisibility(View.GONE);
            } else {
                std.setVisibility(View.VISIBLE);
                std.setUp(item.getPics()
                        , "", Jzvd.SCREEN_WINDOW_NORMAL);


                //  std.startVideo();

            }
        }
    }
}
