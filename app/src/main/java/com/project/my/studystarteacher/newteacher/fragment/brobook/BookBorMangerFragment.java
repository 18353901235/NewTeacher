package com.project.my.studystarteacher.newteacher.fragment.brobook;


import android.view.View;
import android.widget.AdapterView;

import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.BookBormangerAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseFragment;
import com.project.my.studystarteacher.newteacher.bean.LoginClassBean;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.JsonUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

@ContentView(R.layout.fragment_bookbormanger)
public class BookBorMangerFragment extends BaseFragment {
    @ViewInject(R.id.list)
    private PullToRefreshGridView listView;

    @Override
    public void init() {
        getClassData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LoginClassBean bean = (LoginClassBean) parent.getItemAtPosition(position);
                ToActivity(mContext, BorMangerActivity.class, bean.getBjid());
            }
        });

    }

    public void getClassData() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                ArrayList<LoginClassBean> classList = JsonUtil.fromList((String) baseBean.getData(), "classList", LoginClassBean.class);
                BookBormangerAdapter demoAdapter = new BookBormangerAdapter(mContext, R.layout.bookbmanger_item, classList);
                listView.setAdapter(demoAdapter);
            }
        });
        Worker.getClassList();
    }


}
