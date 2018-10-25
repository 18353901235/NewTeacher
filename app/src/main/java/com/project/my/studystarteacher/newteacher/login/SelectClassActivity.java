package com.project.my.studystarteacher.newteacher.login;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.SelectClassAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.project.my.studystarteacher.newteacher.bean.LoginClassBean;
import com.project.my.studystarteacher.newteacher.bean.LoginSchollBean;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.project.my.studystarteacher.newteacher.utils.EventBusUtil;
import com.project.my.studystarteacher.newteacher.utils.EventWhatId;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.JsonUtil;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import org.greenrobot.eventbus.EventBus;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

@ContentView(R.layout.activity_select_class)
public class SelectClassActivity extends BaseActivity {
    @ViewInject(R.id.et_schoolName)
    private EditText etSchoolName;
    @ViewInject(R.id.iv_search)
    private ImageView iv_search;
    @ViewInject(R.id.tv_schoolName)
    private TextView tvSchoolName;
    @ViewInject(R.id.rlv)
    private RecyclerView rlv;

    @Override
    protected void init() {
        getCommonTitle().setText("选择班级");
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        rlv.setLayoutManager(layoutManager);
        // rlv.addItemDecoration(new MyItemDecoration(this));
        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = etSchoolName.getText().toString().trim();
                MiceNetWorker Worker = new MiceNetWorker(mContext);
                Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
                    @Override
                    public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                        super.onSuccess(netWorker, netTask, baseBean);
                        final LoginSchollBean school = JsonUtil.fromBean((String) baseBean.getData(), "school", LoginSchollBean.class);
                        tvSchoolName.setVisibility(View.VISIBLE);
                        tvSchoolName.setText(school.getSchoolName());
                        final ArrayList<LoginClassBean> classList = JsonUtil.fromList((String) baseBean.getData(), "classList", LoginClassBean.class);
                        SelectClassAdapter selectClassAdapter = new SelectClassAdapter(mContext, R.layout.acitivity_chooseclass_item, classList);
                        rlv.setAdapter(selectClassAdapter);
                        selectClassAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                                LoginClassBean loginClassBean = classList.get(position);
                                loginClassBean.setBean(school);
                                EventBusUtil eventBusUtil = new EventBusUtil(EventWhatId.SELECTCLASS, loginClassBean);
                                EventBus.getDefault().post(eventBusUtil);
                                finish();
                            }

                            @Override
                            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                                return false;
                            }
                        });
                    }
                });

                Worker.seachSchoolAndClassCtrl(content);
            }

        });


    }

}
