package com.project.my.studystarteacher.newteacher.activity.home;

import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.HomeLoveAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.project.my.studystarteacher.newteacher.bean.RedCard;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.JsonUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

@ContentView(R.layout.activity_love_details)
public class LoveDetailsActivity extends BaseActivity {
    @ViewInject(R.id.list)
    private PullToRefreshListView list;
    @ViewInject(R.id.money)
    private TextView money;

    @Override
    protected void init() {
        getCommonTitle().setText("邀约明细");

        getData();
    }

    double allMoney;

    public void getData() {
        allMoney = 0;
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                ArrayList<RedCard> detail = JsonUtil.fromList((String) baseBean.getData(), "detail", RedCard.class);
                HomeLoveAdapter adapter = new HomeLoveAdapter(mContext, R.layout.item_love_detail, detail);
                list.setAdapter(adapter);
                for (RedCard r : detail) {
                    allMoney += r.getMoney();
                }
                money.setText("总计：￥"+allMoney );
            }
        });
        Worker.count();
    }
}
