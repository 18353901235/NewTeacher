package com.project.my.studystarteacher.newteacher.activity.home;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.dmcbig.mediapicker.PickerActivity;
import com.dmcbig.mediapicker.PickerConfig;
import com.dmcbig.mediapicker.entity.Media;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.SelectIvAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.project.my.studystarteacher.newteacher.bean.MangOBJ;
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

import java.util.ArrayList;

@ContentView(R.layout.activity_worn_sign)
public class WornSignActivity extends BaseActivity {
    @ViewInject(R.id.bagNum)
    private TextView bagNum;
    @ViewInject(R.id.bookNum)
    private TextView bookNum;
    @ViewInject(R.id.bookName)
    private TextView bookName;


    @ViewInject(R.id.et_desc)
    private EditText etDesc;
    @ViewInject(R.id.error)
    private RadioButton error;
    @ViewInject(R.id.em)
    private RadioButton em;
    @ViewInject(R.id.gv)
    private GridView gv;
    int status = 1;
    Media nll;
    ArrayList<Media> selectList = new ArrayList<>();
    SelectIvAdapter selectIvAdapter;
    ArrayList<Media> select;

    @Override
    protected void init() {
        getCommonTitle().setText("破损登记");
        getRightTextView().setText("提交");
        nll = new Media("drawable://" + R.mipmap.communication_content_camera02, "", 0, 0, 0, 0, "");
        selectList.add(nll);
        selectIvAdapter = new SelectIvAdapter(mContext, R.layout.iv_f_item, selectList);
        gv.setAdapter(selectIvAdapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, PickerActivity.class);
                intent.putExtra(PickerConfig.SELECT_MODE, PickerConfig.PICKER_IMAGE);//default image and video (Optional)
                long maxSize = 1024 * 1024 * 18;//long long long
                intent.putExtra(PickerConfig.MAX_SELECT_SIZE, maxSize); //default 180MB (Optional)
                intent.putExtra(PickerConfig.MAX_SELECT_COUNT, 3);  //default 40 (Optional)
                intent.putExtra(PickerConfig.DEFAULT_SELECTED_LIST, select); // (Optional)
                startActivityForResult(intent, 200);
            }
        });
        final MangOBJ obj = (MangOBJ) getIntent().getSerializableExtra("data");
        final int position = getIntent().getIntExtra("data2", 0);
        bagNum.setText(obj.getBag().getSchoolbagbhao());
        bookNum.setText(obj.getBooks().get(position).getBookbhao());
        bookName.setText(obj.getBooks().get(position).getBookname());
        error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = 1;
            }
        });
        em.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = 2;
            }
        });
        getRightTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (select == null || select.size() < 1) {
                    ToastUtil.showLongToast(mContext, "请上传破损图片");
                    return;
                }
                MiceNetWorker Worker = new MiceNetWorker(mContext);
                Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
                    @Override
                    public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                        super.onSuccess(netWorker, netTask, baseBean);
                        ToastUtil.showLongToast(mContext, "上报成功");
                        EventBus.getDefault().post(new EventBusUtil(EventWhatId.REFRSH));
                        finish();
                    }
                });
                //  1表示严重，2表示丢失, 3家长端上传自己破损，4.家长端上传他人破损
                Worker.bookDamageUpload(obj.getXs_id() + "", obj.getBag().getSchoolbagbhao(), obj.getBooks().get(position).getBookbhao(), etDesc.getText().toString().trim(), status + "", obj.getBooks().get(position).getBookname(), selectList);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 200) {
            if (data != null) {
                select = data.getParcelableArrayListExtra(PickerConfig.EXTRA_RESULT);
                selectList.clear();
                selectList.addAll(select);
                if (select.size() < 3) {
                    selectList.add(nll);
                }
                selectIvAdapter.notifyDataSetChanged();
            }
        }
    }


}
