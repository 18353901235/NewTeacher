package com.project.my.studystarteacher.newteacher.fragment;


import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.BagMangagerAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseFragment;
import com.project.my.studystarteacher.newteacher.bean.BagXzBean;
import com.project.my.studystarteacher.newteacher.common.UserSingleton;
import com.project.my.studystarteacher.newteacher.net.DemoHttpInformation;
import com.project.my.studystarteacher.newteacher.utils.EventBusUtil;
import com.project.my.studystarteacher.newteacher.utils.EventWhatId;
import com.zhouqiang.framework.util.JsonUtil;
import com.zhouqiang.framework.util.Logger;
import com.zhouqiang.framework.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

@ContentView(R.layout.fragment_bookbormanger_normal)
public class BagListMangerFragment extends BaseFragment {
    @ViewInject(R.id.list)
    private PullToRefreshGridView listView;

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
            getData();
        }
    }

    public void getData() {

        final RequestParams params = new RequestParams(DemoHttpInformation.BOXLIST.getUrlPath());
        params.addBodyParameter("token", UserSingleton.getInstance().getToken());
        params.addBodyParameter("code", "9907");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Logger.d(params.getBodyContent() + "/result:" + result);
                try {
                    ArrayList<BagXzBean> classbookborrowinfo = JsonUtil.fromList(result, "boxList", BagXzBean.class);
                    BagMangagerAdapter demoAdapter = new BagMangagerAdapter(mContext, R.layout.bag_mangernomal_item, classbookborrowinfo);
                    listView.setAdapter(demoAdapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ToastUtil.showLongToast(mContext, "请求失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

//    public void getData() {
//
//
//        MiceNetWorker Worker = new MiceNetWorker(mContext);
//        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
//            @Override
//            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
//                super.onSuccess(netWorker, netTask, baseBean);
//                ArrayList<BagXzBean> classbookborrowinfo = JsonUtil.fromList((String) baseBean.getData(), "boxList", BagXzBean.class);
////                BookBormangerNoamlAdapter demoAdapter = new BookBormangerNoamlAdapter(mContext, R.layout.bokbor_mangernomal_item, classbookborrowinfo);
////                listView.setAdapter(demoAdapter);
//            }
//
//            @Override
//            public void onFailed(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean, int failedType) {
//                super.onFailed(netWorker, netTask, baseBean, failedType);
//                System.out.println("::::::::::::::::::::::::::::;");
//            }
//        });
//        Worker.boxList();
//    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
