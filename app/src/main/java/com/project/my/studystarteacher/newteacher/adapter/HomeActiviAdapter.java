package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.activity.home.PublicCommentActivity;
import com.project.my.studystarteacher.newteacher.bean.DyNamicBean;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.project.my.studystarteacher.newteacher.share.ShareActivity;
import com.project.my.studystarteacher.newteacher.utils.FaceConversionUtil;
import com.project.my.studystarteacher.newteacher.utils.ImageUtility;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.ToastUtil;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import org.xutils.common.util.DensityUtil;

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
    protected void convert(ViewHolder viewHolder, final DyNamicBean item, int position) {
        GridView gridView = viewHolder.getView(R.id.gv);
        GridView commentgv = viewHolder.getView(R.id.commentgv);
        CommentListAdapter commentListAdapter = new CommentListAdapter(mContext, R.layout.comment_list_item, item.getComments());
        commentgv.setAdapter(commentListAdapter);
        //commentgv.setAdapter();
        viewHolder.setText(R.id.userName, item.getName());
        viewHolder.setText(R.id.time, item.getInsertTime());

        SpannableString spannable =
                FaceConversionUtil.getInstace().getExpressionString(mContext,
                        item.getContents());
        final TextView desc = viewHolder.getView(R.id.desc);
        desc.setText(spannable);
        imageUtility.showImage(item.getHeadImg(), (ImageView) viewHolder.getView(R.id.iv));
        final TextView tv = viewHolder.getView(R.id.zan);
        tv.setText(item.getPraiseCount() + "");
        viewHolder.setText(R.id.pinglun, item.getComments().size() + "");
        viewHolder.getView(R.id.pinglun).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PublicCommentActivity.class);
                intent.putExtra("data", item);
                mContext.startActivity(intent);
            }
        });
        Drawable drawable;
        if (item.getWetherZan().equals("0")) {
            drawable = mContext.getResources().getDrawable(R.mipmap.ic_praise);
        } else {
            drawable = mContext.getResources().getDrawable(R.mipmap.home_btn_collect_selected);
        }
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tv.setCompoundDrawables(drawable, null, null, null);
        tv.setCompoundDrawablePadding(DensityUtil.dip2px(5));//设置图片和text之间的间距
        viewHolder.getView(R.id.zan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getWetherZan().equals("0")) {
                    MiceNetWorker Worker = new MiceNetWorker(mContext);
                    Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
                        @Override
                        public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                            super.onSuccess(netWorker, netTask, baseBean);
                            ToastUtil.showLongToast(mContext, "点赞成功");
                            //
                            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.home_btn_collect_selected);
                            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());

                            tv.setCompoundDrawables(drawable, null, null, null);
                            tv.setCompoundDrawablePadding(DensityUtil.dip2px(5));//设置图片和text之间的间距
                            //   tv.setPadding(-5, 0, 0, 0);
                            item.setWetherZan("1");
                            item.setPraiseCount((Integer.parseInt(item.getPraiseCount()) + 1) + "");
                            notifyDataSetChanged();
                        }
                    });
                    Worker.dynamicFunction("2", "", item.getID(), "", "");
                } else {
                    MiceNetWorker Worker = new MiceNetWorker(mContext);
                    Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
                        @Override
                        public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                            super.onSuccess(netWorker, netTask, baseBean);
                            ToastUtil.showLongToast(mContext, "取消点赞成功");
                            //
                            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.ic_praise);
                            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());

                            tv.setCompoundDrawables(drawable, null, null, null);
                            tv.setCompoundDrawablePadding(DensityUtil.dip2px(5));//设置图片和text之间的间距
                            //   tv.setPadding(-5, 0, 0, 0);
                            item.setWetherZan("0");
                            item.setPraiseCount((Integer.parseInt(item.getPraiseCount()) - 1) + "");
                            notifyDataSetChanged();
                        }
                    });
                    Worker.dynamicFunction("3", "", item.getID(), "", "");
                }

            }
        });
        viewHolder.getView(R.id.share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ShareActivity.class);
                intent.putExtra("url", "");
                intent.putExtra("title", "测试分享");
                intent.putExtra("image", "测试分享");
                intent.putExtra("content", "测试分享");
                mContext.startActivity(intent);


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
