package com.project.my.studystarteacher.newteacher.activity.home;

import android.view.View;
import android.widget.AdapterView;

import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.HomeZhuboAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.project.my.studystarteacher.newteacher.bean.ZhuBoBean;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.JsonUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

@ContentView(R.layout.activity_audio_book)
public class HomeZhuboActivity extends BaseActivity {
    @ViewInject(R.id.list)
    private PullToRefreshGridView list;

    @Override
    protected void init() {
        getCommonTitle().setText("人气主播");
     //   getRight().setBackgroundResource(R.mipmap.musicbk_ic_search);
        //   HomeZhuboAdapter homeClassAdapter3 = new HomeZhuboAdapter(mContext, R.layout.item_audio_list, TempSourceSupply.getMyData());
        final View inflate = View.inflate(mContext, R.layout.pop_fifter_audio, null);
//        AllGridView gridView = inflate.findViewById(R.id.gridview);
//        DemoAdapter adapter = new DemoAdapter(mContext, R.layout.item_zhubo, TempSourceSupply.getCZSData());
//        gridView.setAdapter(adapter);
        //    list.setAdapter(homeClassAdapter3);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToActivity(mContext, AudioPayerActivity.class, rankingData, position);
            }
        });
        popularity();
    }

    ArrayList<ZhuBoBean> rankingData;

    private void popularity() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                rankingData = JsonUtil.fromList((String) baseBean.getData(), "myAudio", ZhuBoBean.class);
                HomeZhuboAdapter homeClassAdapter3 = new HomeZhuboAdapter(mContext, R.layout.item_zhubo, rankingData);
                list.setAdapter(homeClassAdapter3);

            }
        });
        Worker.popularity("6666");
    }
}
