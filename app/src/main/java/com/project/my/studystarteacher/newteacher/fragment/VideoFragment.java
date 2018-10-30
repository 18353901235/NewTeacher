package com.project.my.studystarteacher.newteacher.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.activity.home.VideoDetailsActivity;
import com.project.my.studystarteacher.newteacher.adapter.VideoAdapter;
import com.project.my.studystarteacher.newteacher.adapter.VideoTypeAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseFragment;
import com.project.my.studystarteacher.newteacher.bean.ExpertLecture;
import com.project.my.studystarteacher.newteacher.bean.VideoType;
import com.project.my.studystarteacher.newteacher.common.UserSingleton;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.JsonUtil;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

@ContentView(R.layout.fragment_video)
public class VideoFragment extends BaseFragment {
    @ViewInject(R.id.list)
    private PullToRefreshListView listView;
    @ViewInject(R.id.rv)
    private RecyclerView rv;

    @Override
    public void init() {
        getCommonTitle().setText("育儿直播");
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        //设置布局管理器
        rv.setLayoutManager(layoutManager);
        getLeft().setVisibility(View.GONE);
        // getRight().setBackgroundResource(R.mipmap.musicbk_ic_search);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ExpertLecture ex = (ExpertLecture) parent.getItemAtPosition(position);
                ToActivity(mContext, VideoDetailsActivity.class, ex.getId());
            }
        });
        getData();

    }

    public void getListData(String values) {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                ArrayList<ExpertLecture> list = JsonUtil.fromList((String) baseBean.getData(), "list", ExpertLecture.class);
                //  listView.setAdapter();
                VideoAdapter videoAdapter = new VideoAdapter(getActivity(), R.layout.item_video, list);
                listView.setAdapter(videoAdapter);
            }
        });
        Worker.listLive(values);
    }

    String values;

    public void getData() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                final ArrayList<VideoType> type = JsonUtil.fromList((String) baseBean.getData(), "type", VideoType.class);
                if (UserSingleton.getInstance().getSysUser().getBookmanager() != 3) {
                    type.remove(type.size() - 1);
                }
                if (UserSingleton.getInstance().getSysUser().getBookmanager() == 3) {
                    type.remove(type.size() - 2);
                }
                for (int i = 0; i < type.size(); i++) {
                    if (i == 0) {
                        type.get(0).setCheck(true);
                        values = type.get(0).getValue();
                        getListData(values);

                    }
                }
                final VideoTypeAdapter videoTypeAdapter = new VideoTypeAdapter(mContext, R.layout.video_item_type, type);
                rv.setAdapter(videoTypeAdapter);
                videoTypeAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                        for (int i = 0; i < type.size(); i++) {
                            type.get(i).setCheck(false);
                        }
                        type.get(position).setCheck(true);
                        videoTypeAdapter.notifyDataSetChanged();
                        getListData(type.get(position).getValue());
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                        return false;
                    }
                });
            }
        });
        Worker.typeLive();
    }
}
