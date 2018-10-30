package com.project.my.studystarteacher.newteacher.activity.home;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.RankingAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.project.my.studystarteacher.newteacher.bean.RankingBean;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.project.my.studystarteacher.newteacher.utils.ImageUtility;
import com.project.my.studystarteacher.newteacher.view.CircleImageView;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.JsonUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import butterknife.ButterKnife;

@ContentView(R.layout.activity_yue_du_bang)
public class YueDuBangActivity extends BaseActivity {
    @ViewInject(R.id.iv_f)
    private ImageView ivF;
    @ViewInject(R.id.second_icon)
    private CircleImageView secondIcon;
    @ViewInject(R.id.second_name)
    private TextView secondName;
    @ViewInject(R.id.sco)
    private TextView sco;
    @ViewInject(R.id.iv_f3)
    private ImageView ivF3;
    @ViewInject(R.id.third_icon)
    private CircleImageView thirdIcon;
    @ViewInject(R.id.third_name)
    private TextView thirdName;
    @ViewInject(R.id.sco3)
    private TextView sco3;
    @ViewInject(R.id.list)
    private ListView list;
    @ViewInject(R.id.iv_f2)
    private ImageView ivF2;
    @ViewInject(R.id.first_icon)
    private CircleImageView firstIcon;
    @ViewInject(R.id.first_name)
    private TextView firstName;
    @ViewInject(R.id.sco1)
    private TextView sco1;
    ImageUtility imageUtility;

    @Override
    protected void init() {
        //  list.setAdapter();
        getData();
        imageUtility = new ImageUtility(R.mipmap.moren);
    }

    public void getData() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                ArrayList<RankingBean> rankingBean = JsonUtil.fromList((String) baseBean.getData(), "rankingData", RankingBean.class);
                if (rankingBean.size() > 3) {
                    imageUtility.showImage(rankingBean.get(0).getXs_pic(), firstIcon);
                    firstName.setText(rankingBean.get(0).getXs_xming());
                    sco1.setText(rankingBean.get(0).getCount() + "");
                    imageUtility.showImage(rankingBean.get(1).getXs_pic(), secondIcon);
                    secondName.setText(rankingBean.get(1).getXs_xming());
                    sco.setText(rankingBean.get(1).getCount() + "");
                    imageUtility.showImage(rankingBean.get(2).getXs_pic(), thirdIcon);
                    thirdName.setText(rankingBean.get(2).getXs_xming());
                    sco3.setText(rankingBean.get(2).getCount() + "");
                }
                ArrayList<RankingBean> rankingBeans = new ArrayList<>();
                for (int i = 2; i < rankingBean.size(); i++) {
                    rankingBeans.add(rankingBean.get(i));
                }

                RankingAdapter demoAdapter = new RankingAdapter(mContext, R.layout.bang_list_item, rankingBeans);
                list.setAdapter(demoAdapter);

            }
        });
        Worker.getActivityRanking();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
