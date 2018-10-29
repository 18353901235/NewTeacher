package com.project.my.studystarteacher.newteacher.activity.home;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.project.my.studystarteacher.newteacher.bean.DyNamicBean;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.project.my.studystarteacher.newteacher.utils.EventBusUtil;
import com.project.my.studystarteacher.newteacher.utils.EventWhatId;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_public_comment)
public class PublicCommentActivity extends BaseActivity {
    @ViewInject(R.id.et)
    private EditText et;

    @Override
    protected void init() {
        getCommonTitle().setText("发布评论");
        getRightTextView().setText("提交");
        final DyNamicBean item = (DyNamicBean) getIntent().getSerializableExtra("data");
        getRightTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(et.getText().toString().trim())) {
                    ToastUtil.showLongToast(mContext, "评论内容不能为空");

                    return;
                }
                MiceNetWorker Worker = new MiceNetWorker(mContext);

                Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
                    @Override
                    public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                        super.onSuccess(netWorker, netTask, baseBean);
                        finish();
                        ToastUtil.showLongToast(mContext, "评论成功");
                        EventBus.getDefault().post(new EventBusUtil(EventWhatId.REFRSH));
                    }
                });
                Worker.dynamicFunction("4", et.getText().toString().trim(), item.getID(), "", "");
            }
        });

    }
}
