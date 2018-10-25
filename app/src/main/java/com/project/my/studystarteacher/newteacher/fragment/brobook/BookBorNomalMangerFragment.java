package com.project.my.studystarteacher.newteacher.fragment.brobook;


import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.BookBormangerNoamlAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseFragment;
import com.project.my.studystarteacher.newteacher.bean.ClassBorBook;
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

@ContentView(R.layout.fragment_bookbormanger_normal)
public class BookBorNomalMangerFragment extends BaseFragment {
    @ViewInject(R.id.list)
    private PullToRefreshGridView listView;
    String id = "";

    @Override
    public void init() {
        EventBus.getDefault().register(this);
//        BookBormangerNoamlAdapter demoAdapter = new BookBormangerNoamlAdapter(mContext, R.layout.bokbor_mangernomal_item, TempSourceSupply.getMyData());
//        listView.setAdapter(demoAdapter);
        //   listView.setMode(PullToRefreshBase.Mode.BOTH);
//        listView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
//            @Override
//            public void onLastItemVisible() {
//                //   ToastUtil.showLongToast(mContext, "加载下一页");
//            }
//        });
        getData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getClassData(EventBusUtil u) {
        if (u.getMsgWhat() == EventWhatId.CLASSID) {
            id = (int) u.getMsgStr() + "";
            getData();
        }
    }

    public void getData() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                ArrayList<ClassBorBook> classbookborrowinfo = JsonUtil.fromList((String) baseBean.getData(), "classbookborrowinfo", ClassBorBook.class);
                BookBormangerNoamlAdapter demoAdapter = new BookBormangerNoamlAdapter(mContext, R.layout.bokbor_mangernomal_item, classbookborrowinfo);
                listView.setAdapter(demoAdapter);
            }
        });
        Worker.getStudentBorrowList(id);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
