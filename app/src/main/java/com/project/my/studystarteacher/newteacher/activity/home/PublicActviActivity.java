package com.project.my.studystarteacher.newteacher.activity.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.dmcbig.mediapicker.PickerActivity;
import com.dmcbig.mediapicker.PickerConfig;
import com.dmcbig.mediapicker.entity.Media;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.SelectIvAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.ToastUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@ContentView(R.layout.activity_public_actvi)
public class PublicActviActivity extends BaseActivity {
    @ViewInject(R.id.gv)
    private GridView gv;
    private String ROOT_PATH;
    @ViewInject(R.id.edt)
    private EditText et;
    Media nll;
    ArrayList<Media> selectList = new ArrayList<>();
    SelectIvAdapter selectIvAdapter;
    ArrayList<Media> select = new ArrayList<>();

    private void initPhotoError() {
        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MiceNetWorker Worker = new MiceNetWorker(mContext);
                Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
                    @Override
                    public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                        super.onSuccess(netWorker, netTask, baseBean);
                    }
                });
                if (select.size() < 1) {
                    ToastUtil.showLongToast(mContext, "请上传图片");
                    return;

                }
                Worker.dynamicUpload(et.getText().toString().trim(), select, "");
            }
        });
    }


    @Override
    protected void init() {
        getCommonTitle().setText("动态发布");
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
        findViewById(R.id.select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PickerActivity.class);
                intent.putExtra(PickerConfig.SELECT_MODE, PickerConfig.PICKER_IMAGE);//default image and video (Optional)
                long maxSize = 1024 * 1024 * 18;//long long long
                intent.putExtra(PickerConfig.MAX_SELECT_SIZE, maxSize); //default 180MB (Optional)
                intent.putExtra(PickerConfig.MAX_SELECT_COUNT, 3);  //default 40 (Optional)
                intent.putExtra(PickerConfig.DEFAULT_SELECTED_LIST, select); // (Optional)
                startActivityForResult(intent, 200);
            }
        });
        findViewById(R.id.video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri fileUri = Uri.fromFile(getOutputMediaFile());
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 10); //限制录制时长 以秒为单位
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1); //设置拍摄质量0~1（建议不要设置中间值，不同手机似乎效果不同）
                //intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, 1024 * 1024);//限制视频文件大小 以字节为单位
                startActivityForResult(intent, 1);
            }
        });
        findViewById(R.id.icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        initRootPath(mContext);
        initPhotoError();
    }

    private void initRootPath(Context context) {
        try {
            ROOT_PATH = context.getExternalFilesDir(null).getAbsolutePath();
        } catch (Exception e) {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                ROOT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + context.getPackageName();
            } else {
                ROOT_PATH = context.getFilesDir().getAbsolutePath();
            }
        } catch (Throwable e) {
            ROOT_PATH = context.getFilesDir().getAbsolutePath();
        }
    }

    private File getOutputMediaFile() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "SD卡不存在！", Toast.LENGTH_SHORT).show();
            return null;
        }
        File mediaStorageDir = new File(ROOT_PATH, "MyVideo");
        if (!mediaStorageDir.exists()) {
            mediaStorageDir.mkdirs();
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile = new File(mediaStorageDir.getPath() + File.separator + "VID_" + timeStamp + ".mp4");
        return mediaFile;
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
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // video captured and saved to fileUri specified in the Intent
                Toast.makeText(this, "Video saved to:\n" +
                                data.getData(),
                        Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the video capture
            }
        }
    }
}
