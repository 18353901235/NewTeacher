package com.project.my.studystarteacher.newteacher.activity.home;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.AudioRecodeAdapter;
import com.project.my.studystarteacher.newteacher.adapter.ZhuboBookAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.project.my.studystarteacher.newteacher.bean.AudioBook;
import com.project.my.studystarteacher.newteacher.bean.AudioRecodeBean;
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

@ContentView(R.layout.activity_zhubo)
public class ZhuboActivity extends BaseActivity {
    @ViewInject(R.id.list)
    private PullToRefreshListView list;
    @ViewInject(R.id.bt_1)
    private ImageView bt_1;
    @ViewInject(R.id.bt_3)
    private ImageView bt_3;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updata(EventBusUtil u) {
        if (u.getMsgWhat() == EventWhatId.RECODE_UPOK) {
            getAudioData();
        }

    }

    @Override
    protected void init() {
        EventBus.getDefault().register(this);
        // getRight().setBackgroundResource(R.mipmap.musicbk_ic_search);
        getCommonTitle().setText("主播");
        list.setOnItemClickListener(getListener());
        findViewById(R.id.bt_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.setAdapter(adapter);
                bt_1.setBackgroundResource(R.mipmap.btn_hblist);
                bt_3.setBackgroundResource(R.mipmap.btn_video_normal);
                list.setOnItemClickListener(getListener());
            }
        });
        findViewById(R.id.bt_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.setAdapter(adapter2);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Object itemAtPosition = parent.getItemAtPosition(position);
                        if (itemAtPosition instanceof AudioRecodeBean) {
                            AudioRecodeBean au = (AudioRecodeBean) itemAtPosition;
                            if (au == null) {
                                return;
                            }
                            //   ToActivity(mContext, RecodActivity.class, au);
                        }
                    }
                });
                bt_1.setBackgroundResource(R.mipmap.btn_hblist_normal);
                bt_3.setBackgroundResource(R.mipmap.btn_video);
            }
        });
        //AudioBook
        getFData();
        getAudioData();
    }

    @NonNull
    private AdapterView.OnItemClickListener getListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object itemAtPosition = parent.getItemAtPosition(position);
                if (itemAtPosition instanceof AudioBook) {
                    AudioBook au = (AudioBook) itemAtPosition;
                    if (au == null) {
                        return;
                    }
                    ToActivity(mContext, RecodActivity.class, au);
                }
            }
        };
    }

    ZhuboBookAdapter adapter;

    public void getFData() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                ArrayList<AudioBook> bookList = JsonUtil.fromList((String) baseBean.getData(), "bookList", AudioBook.class);
                adapter = new ZhuboBookAdapter(mContext, R.layout.zhubo_list_item, bookList);
                list.setAdapter(adapter);

            }

            @Override
            public void onPostExecute(SanmiNetWorker netWorker, SanmiNetTask netTask) {
                super.onPostExecute(netWorker, netTask);
                list.onRefreshComplete();
            }
        });
        Worker.bookList();
    }

    AudioRecodeAdapter adapter2;

    public void getAudioData() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                ArrayList<AudioRecodeBean> bookList = JsonUtil.fromList((String) baseBean.getData(), "myAudio", AudioRecodeBean.class);
                adapter2 = new AudioRecodeAdapter(mContext, R.layout.zhubo_list2_item, bookList);
                list.setAdapter(adapter);

            }

            @Override
            public void onPostExecute(SanmiNetWorker netWorker, SanmiNetTask netTask) {
                super.onPostExecute(netWorker, netTask);
                list.onRefreshComplete();

            }
        });
        Worker.myAudio();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
