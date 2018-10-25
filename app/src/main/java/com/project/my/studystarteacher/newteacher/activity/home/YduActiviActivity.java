package com.project.my.studystarteacher.newteacher.activity.home;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.YueDuAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.project.my.studystarteacher.newteacher.bean.ReadDynamics;
import com.project.my.studystarteacher.newteacher.common.CommonWebViewActivity;
import com.project.my.studystarteacher.newteacher.common.ProjectConstant;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.JsonUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

@ContentView(R.layout.activity_ydu_activi)
public class YduActiviActivity extends BaseActivity {
    @ViewInject(R.id.list)
    private PullToRefreshListView list;
    ArrayList dataList = new ArrayList<ReadDynamics>();
    int index;
    YueDuAdapter adapter;

    @Override
    protected void init() {
        getCommonTitle().setText("悦读动态");
        adapter = new YueDuAdapter(mContext, R.layout.yued_item, dataList);
        list.setAdapter(adapter);
        getReadDynamics();
        list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                index = 0;
                getReadDynamics();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                index++;
                getReadDynamics();
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ReadDynamics readDynamics = (ReadDynamics) parent.getItemAtPosition(position);
                Intent intent = new Intent(mContext, CommonWebViewActivity.class);
                intent.putExtra(ProjectConstant.WV_TITLE, readDynamics.getTitle());
                intent.putExtra(ProjectConstant.WV_URL, readDynamics.getUrl());
                startActivity(intent);
            }
        });
    }

    private void getReadDynamics() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                final ArrayList<ReadDynamics> readDynamics = JsonUtil.fromList((String) baseBean.getData(), "readDynamics", ReadDynamics.class);
                if (index == 0) {
                    dataList.clear();
                }
                dataList.addAll(readDynamics);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onPostExecute(SanmiNetWorker netWorker, SanmiNetTask netTask) {
                super.onPostExecute(netWorker, netTask);
                list.onRefreshComplete();
            }

            @Override
            public void onFailed(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean, int failedType) {
                super.onFailed(netWorker, netTask, baseBean, failedType);
            }
        });
        Worker.getReadDynamics(index, 10);
    }

}
