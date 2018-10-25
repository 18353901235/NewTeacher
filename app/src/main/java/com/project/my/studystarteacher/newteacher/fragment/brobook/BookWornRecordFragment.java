package com.project.my.studystarteacher.newteacher.fragment.brobook;


import android.view.View;
import android.widget.AdapterView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.activity.home.WornDetailActivity;
import com.project.my.studystarteacher.newteacher.adapter.DamageBookAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseFragment;
import com.project.my.studystarteacher.newteacher.bean.BookDamageBean;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.project.my.studystarteacher.newteacher.utils.EventBusUtil;
import com.project.my.studystarteacher.newteacher.utils.EventWhatId;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.JsonUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

@ContentView(R.layout.fragment__record_list)
public class BookWornRecordFragment extends BaseFragment {
    @ViewInject(R.id.list)
    private PullToRefreshListView listView;

    @Override
    public void init() {
        EventBus.getDefault().register(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BookDamageBean bookDamageBean = (BookDamageBean) parent.getItemAtPosition(position);
                ToActivity(mContext, WornDetailActivity.class, bookDamageBean);
            }
        });
        getData();
        //
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refrsh(EventBusUtil i) {
        if (i.getMsgWhat() == EventWhatId.REFRSH) {
            //  BookDamageBean bookDamageBean = (BookDamageBean) i.getMsgStr();
            getData();
//            for (int z = 0; z < bookDamageBeans.size(); z++) {
//                if (bookDamageBean.getId() == bookDamageBeans.get(z).getId()) {
//                    bookDamageBeans.remove(i);
//                }
//            }
//            demoAdapter.notifyDataSetChanged();
        }
    }

    ArrayList<BookDamageBean> bookDamageBeans;
    DamageBookAdapter demoAdapter;

    public void getData() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                bookDamageBeans = JsonUtil.fromList((String) baseBean.getData(), "bookDamageList", BookDamageBean.class);
                demoAdapter = new DamageBookAdapter(mContext, R.layout.bokworn_record_item, bookDamageBeans);
                listView.setAdapter(demoAdapter);
            }
        });
        Worker.getbookDamageList("");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
