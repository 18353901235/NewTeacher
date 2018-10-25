package com.project.my.studystarteacher.newteacher.activity.home;

import android.view.View;
import android.widget.TextView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.IvAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.project.my.studystarteacher.newteacher.bean.BookDamageBean;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.project.my.studystarteacher.newteacher.utils.EventBusUtil;
import com.project.my.studystarteacher.newteacher.utils.EventWhatId;
import com.project.my.studystarteacher.newteacher.utils.TimeUtil;
import com.project.my.studystarteacher.newteacher.view.AllGridView;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

@ContentView(R.layout.activity_parent_submit)
public class WornDetailActivity extends BaseActivity {

    @ViewInject(R.id.bookName)
    private TextView bookName;
    @ViewInject(R.id.bagNum)
    private TextView bagNum;
    @ViewInject(R.id.userName)
    private TextView userName;
    @ViewInject(R.id.time)
    private TextView time;
    @ViewInject(R.id.status)
    private TextView status;
    @ViewInject(R.id.desc)
    private TextView desc;
    @ViewInject(R.id.gv)
    private AllGridView gv;
    @ViewInject(R.id.reset)
    private TextView reset;
    BookDamageBean data;

    @Override
    protected void init() {
        data = (BookDamageBean) getIntent().getSerializableExtra("data");
        bookName.setText("《" + data.getBookname() + "》");
        time.setText(TimeUtil.TransTime(data.getInserttime(), "yyyy-MM-dd HH:mm"));
        bagNum.setText(data.getSchoolbagbhao());
        userName.setText(data.getStudentName());
        desc.setText(data.getDamagedesc());

        if (data.getDamagedegree() == 1) {
            status.setText("严重");
        }
        if (data.getDamagedegree() == 2) {
            status.setText("丢失");
        }
        if (data.getDamagedegree() == 3) {
            status.setText("他人");
        }
        if (data.getDamagedegree() == 4) {
            status.setText("自己");

        }
        String damageimg = data.getDamageimg();
        ArrayList<String> strings = new ArrayList<>();
        if (!isNull(damageimg)) {
            String[] split = damageimg.split(",");
            for (String s : split) {
                strings.add(s);
            }
        }
        IvAdapter ivAdapter = new IvAdapter(mContext, R.layout.iv_f_item, strings);
        gv.setAdapter(ivAdapter);

        getCommonTitle().setText("破损管理");
        getRight().setBackgroundResource(R.mipmap.musicbk_ic_search);
    }


    @Event(R.id.reset)
    private void onViewClicked(View w) {
        //  cancelDamage
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                ToastUtil.showLongToast(mContext, "取消成功");
                EventBus.getDefault().post(new EventBusUtil(EventWhatId.REFRSH, data));
                finish();
            }
        });
        Worker.cancelDamage(data.getId() + "");
    }
}
