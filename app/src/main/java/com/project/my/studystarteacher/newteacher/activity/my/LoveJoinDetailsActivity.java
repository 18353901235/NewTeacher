package com.project.my.studystarteacher.newteacher.activity.my;

import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.HomeStatAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.project.my.studystarteacher.newteacher.bean.YaoSign;
import com.project.my.studystarteacher.newteacher.common.UserSingleton;
import com.project.my.studystarteacher.newteacher.net.DemoHttpInformation;
import com.zhouqiang.framework.util.JsonUtil;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

@ContentView(R.layout.activity_love_join_details)
public class LoveJoinDetailsActivity extends BaseActivity {
    @ViewInject(R.id.list)
    private PullToRefreshListView list;
    @ViewInject(R.id.num)
    private TextView allp;

    @Override
    protected void init() {
        getCommonTitle().setText("报名详情");

        //   statistics
        getData();
    }
    int index;
    public void getData() {
        index = 0;
        //DemoHttpInformation.GETSCORELIST
        RequestParams requestParams = new RequestParams(DemoHttpInformation.GETSCORELIST.getUrlPath() + "?classId=" + UserSingleton.getInstance().getSysUser().getBji());
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    ArrayList<YaoSign> yaoSigns = JsonUtil.fromList(result, YaoSign.class);
                    HomeStatAdapter adapter = new HomeStatAdapter(mContext, R.layout.item_love_mdetail, yaoSigns);
                    list.setAdapter(adapter);

                    for (YaoSign s : yaoSigns) {
                        index += s.getScore();
                    }
                    allp.setText(index + "");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


}
