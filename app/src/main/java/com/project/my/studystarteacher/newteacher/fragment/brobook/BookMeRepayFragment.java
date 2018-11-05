package com.project.my.studystarteacher.newteacher.fragment.brobook;


import android.view.View;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.BookMeRepay_itemAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseFragment;
import com.project.my.studystarteacher.newteacher.bean.MeRepayBook;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.project.my.studystarteacher.newteacher.utils.EventBusUtil;
import com.project.my.studystarteacher.newteacher.utils.EventWhatId;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.JsonUtil;
import com.zhouqiang.framework.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

@ContentView(R.layout.fragment_book_bor_record_list)
public class BookMeRepayFragment extends BaseFragment {
    @ViewInject(R.id.list)
    private PullToRefreshListView listView;
    ImageView iv;

    @Override
    public void init() {
        iv = (ImageView) findViewById(R.id.all);
        iv.setTag("0");
        EventBus.getDefault().register(this);
        getData();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    ArrayList<String> checkList = new ArrayList<>();

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setData(EventBusUtil u) {
        if (u.getMsgWhat() == EventWhatId.MEREPAY) {
            MeRepayBook book = (MeRepayBook) u.getMsgStr();
            if (book.isCheck()) {
                boolean check = false;
                boolean join = false;
                for (int i = 0; i < dataList.size(); i++) {
                    if (dataList.get(i).getReadTime().equals(book.getReadTime())) {
                        if (dataList.get(i).isCheck() == false && !dataList.get(i).getXs_id().equals(book.getXs_id())) {
                            check = false;
                            adapter.notifyDataSetChanged();
                            return;
                        }
                        checkList.add(book.getReadTime());
                    }
                }
                if (check) {
                    checkList.add(book.getReadTime());
                } else {
                    checkList.remove(book.getReadTime());
                }

            } else {
                checkList.remove(book.getReadTime());
            }
            adapter.notifyDataSetChanged();

        }
        if (u.getMsgWhat() == EventWhatId.MEREPAYCHECKED) {
            MeRepayBook book = (MeRepayBook) u.getMsgStr();
            checkList.add(book.getReadTime());
            for (int i = 0; i < dataList.size(); i++) {
                if (dataList.get(i).getReadTime().equals(book.getReadTime())) {
                    dataList.get(i).setCheck(true);
                }
            }
            adapter.notifyDataSetChanged();
        }
        if (u.getMsgWhat() == EventWhatId.MEREPAYUNCHECKED) {
            MeRepayBook book = (MeRepayBook) u.getMsgStr();
            checkList.remove(book.getReadTime());
            for (int i = 0; i < dataList.size(); i++) {
                if (dataList.get(i).getReadTime().equals(book.getReadTime())) {
                    dataList.get(i).setCheck(false);
                }
            }
            adapter.notifyDataSetChanged();
        }
        if (u.getMsgWhat() == EventWhatId.SUBMITREPAY) {//提交
            String s = JsonUtil.BeanToJson(checkList);
            MiceNetWorker Worker = new MiceNetWorker(mContext);
            Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
                @Override
                public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                    super.onSuccess(netWorker, netTask, baseBean);
                    ToastUtil.showLongToast(mContext, JsonUtil.fromString((String) baseBean.getData(), "msg"));
                }
            });
            Worker.giveBackBags(s);

        }
    }

    ArrayList<MeRepayBook> dataList;
    BookMeRepay_itemAdapter adapter;

    public void getData() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                //  ArrayList<String> timeList = JsonUtil.fromList((String) baseBean.getData(), "timeList", String.class);
                JSONObject parse = (JSONObject) JSON.parse((String) baseBean.getData());
                final JSONArray timeList = parse.getJSONArray("timeList");
                final JSONObject noGivebackList = parse.getJSONObject("noGivebackList");
                dataList = new ArrayList<>();
                if (timeList == null) {
//                    String msg = JsonUtil.fromString((String) baseBean.getData(), "msg");
//                    ToastUtil.showLongToast(mContext,msg);
                    return;
                }
                for (int i = 0; i < timeList.size(); i++) {
                    ArrayList<MeRepayBook> meRepayBooks = JsonUtil.fromList(noGivebackList.toJSONString(), (String) timeList.get(i), MeRepayBook.class);
                    dataList.addAll(meRepayBooks);
                }
                adapter = new BookMeRepay_itemAdapter(mContext, R.layout.me_repay_list_item, dataList, checkList);
                listView.setAdapter(adapter);


                findViewById(R.id.all).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (iv.getTag().equals("1")) {
                            for (int i = 0; i < dataList.size(); i++) {
                                dataList.get(i).setCheck(false);
                            }
                            checkList.clear();
                            iv.setBackgroundResource(R.mipmap.checkbtn_select);
                            iv.setTag("0");
                        } else {
                            checkList.clear();
                            for (int i = 0; i < dataList.size(); i++) {
                                dataList.get(i).setCheck(true);
                                checkList.add(dataList.get(i).getReadTime());
                            }

                            iv.setBackgroundResource(R.mipmap.check_btn);
                            iv.setTag("1");
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
//                BookMeRepayAdapter demoAdapter = new BookMeRepayAdapter(mContext, R.layout.fragment_book_merepay_list, TempSourceSupply.getCZSData());
//                listView.setAdapter(demoAdapter);

            }
        });
        Worker.giveBackBagsList();
    }
}
