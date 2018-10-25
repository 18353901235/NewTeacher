package com.project.my.studystarteacher.newteacher.fragment.brobook;


import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.ParentSumbitAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseFragment;
import com.project.my.studystarteacher.newteacher.bean.BookDamageBean;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.JsonUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

@ContentView(R.layout.fragment__record_list)
public class BookWornParentSubmitFragment extends BaseFragment {
    @ViewInject(R.id.list)
    private PullToRefreshListView listView;

    ArrayList<BookDamageBean> bookDamageBeans;
    ParentSumbitAdapter demoAdapter;

    @Override
    public void init() {
        getData();
    }

    ArrayList<BookDamageBean> dataList = new ArrayList();

    public void getData() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                bookDamageBeans = JsonUtil.fromList((String) baseBean.getData(), "bookDamageList", BookDamageBean.class);
                for (BookDamageBean b : bookDamageBeans) {
                    if (b.getDamagedegree() == 3 || b.getDamagedegree() == 4) {
                        dataList.add(b);
                    }
                }
                demoAdapter = new ParentSumbitAdapter(mContext, R.layout.parent_submit_item, dataList);
                listView.setAdapter(demoAdapter);
            }
        });
        Worker.getbookDamageList("");
    }
}
