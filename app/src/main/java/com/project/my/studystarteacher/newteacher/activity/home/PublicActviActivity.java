package com.project.my.studystarteacher.newteacher.activity.home;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dmcbig.mediapicker.PickerConfig;
import com.dmcbig.mediapicker.entity.Media;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.FaceAdapter;
import com.project.my.studystarteacher.newteacher.adapter.SelectIvAdapter;
import com.project.my.studystarteacher.newteacher.album.PicturePickActivity;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.project.my.studystarteacher.newteacher.bean.ChatEmoji;
import com.project.my.studystarteacher.newteacher.common.ProjectConstant;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.project.my.studystarteacher.newteacher.utils.EventBusUtil;
import com.project.my.studystarteacher.newteacher.utils.EventWhatId;
import com.project.my.studystarteacher.newteacher.utils.FaceConversionUtil;
import com.project.my.studystarteacher.newteacher.utils.ViewPagerAdapter;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.Logger;
import com.zhouqiang.framework.util.ToastUtil;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import org.greenrobot.eventbus.EventBus;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

@ContentView(R.layout.activity_public_actvi)
public class PublicActviActivity extends BaseActivity {
    @ViewInject(R.id.gv)
    private GridView gv;
    private String ROOT_PATH;
    @ViewInject(R.id.edt)
    private EditText et;
    @ViewInject(R.id.ll_facechoosee)
    private RelativeLayout ll_facechoosee;

    Media nll;
    ArrayList<Media> selectList = new ArrayList<>();
    SelectIvAdapter selectIvAdapter;
    ArrayList<Media> select = new ArrayList<>();
    ArrayList<Media> creamPic = new ArrayList<>();
    private String videoPath = "";
    /**
     * 游标显示布局
     */
    @ViewInject(R.id.iv_imagew)
    private LinearLayout layout_point;

    @PermissionGrant(0)
    public void requestSdcardSuccess() {
    }

    @PermissionDenied(0)
    public void requestSdcardFailed() {
        Toast.makeText(mContext, "权限授权失败，可能部分功能无法使用", Toast.LENGTH_SHORT).show();
    }

    public void requestPermiss() {
        MPermissions.requestPermissions(this, 0, Manifest.permission
                        .CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    /**
     * 游标点集合
     */
    private ArrayList<ImageView> pointViews;

    /**
     * 绘制游标背景
     */
    public void draw_Point(int index) {
        for (int i = 1; i < pointViews.size(); i++) {
            if (index == i) {
                pointViews.get(i).setBackgroundResource(R.mipmap.d2);
            } else {
                pointViews.get(i).setBackgroundResource(R.mipmap.d1);
            }
        }
    }

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
                        ToastUtil.showLongToast(mContext, "发布成功");
                        finish();
                        EventBus.getDefault().post(new EventBusUtil(EventWhatId.REFRSH));
                    }
                });
                if (isNull(videoPath))
                    if (select.size() < 1) {
                        ToastUtil.showLongToast(mContext, "请上传图片");
                        return;

                    }
                Worker.dynamicUpload(et.getText().toString().trim(), select, videoPath);
            }
        });
    }

    JzvdStd std;
    List<List<ChatEmoji>> emojis;

    @Override
    protected void init() {
        requestPermiss();
        getCommonTitle().setText("动态发布");
        emojis = FaceConversionUtil.getInstace().emojiLists;
        ;
        nll = new Media("drawable://" + R.mipmap.communication_content_camera02, "", 0, 0, 0, 0, "");
        selectList.add(nll);
        selectIvAdapter = new SelectIvAdapter(mContext, R.layout.iv_f_item, selectList);
        gv.setAdapter(selectIvAdapter);
        std = findViewById(R.id.videoplayer);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (select.size() > 3) {
                    ToastUtil.showLongToast(mContext, "最大只能上传3张图片");
                    return;
                }
                takePicture();
            }
        });


        findViewById(R.id.pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });
        findViewById(R.id.select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (select.size() > 3) {
                    ToastUtil.showLongToast(mContext, "最大只能上传3张图片");
                    return;
                }
                takePicture();
//                Intent intent = new Intent(mContext, PickerActivity.class);
//                intent.putExtra(PickerConfig.SELECT_MODE, PickerConfig.PICKER_IMAGE);//default image and video (Optional)
//                long maxSize = 1024 * 1024 * 18;//long long long
//                intent.putExtra(PickerConfig.MAX_SELECT_SIZE, maxSize); //default 180MB (Optional)
//                intent.putExtra(PickerConfig.MAX_SELECT_COUNT, 3);  //default 40 (Optional)
//                intent.putExtra(PickerConfig.DEFAULT_SELECTED_LIST, select); // (Optional)
//                startActivityForResult(intent, 200);
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
                ll_facechoosee.setVisibility(ll_facechoosee.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });
        initRootPath(mContext);
        initPhotoError();

        Init_viewPager();
        Init_Point();
        Init_Data();
    }

    private static String picFileFullName;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 101;
    Uri imageUri;
    File outputImage;

    // 拍照
    public void takePicture() {
        Intent intent = new Intent(mContext, PicturePickActivity.class);
        intent.putExtra("pictureNum", (3 - select.size()));
        startActivityForResult(intent, ProjectConstant.SEL_PHOTO_REQUESTCODE);
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
                if (creamPic.size() > 0) {
                    selectList.addAll(creamPic);
                }
                if ((select.size() + creamPic.size()) < 3) {
                    selectList.add(nll);
                }
                selectIvAdapter.notifyDataSetChanged();
            }
        }
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // video captured and saved to fileUri specified in the Intent
                std.setVisibility(View.VISIBLE);
                videoPath = data.getData().getPath();
                std.setUp(videoPath
                        , "", Jzvd.SCREEN_WINDOW_NORMAL);

                gv.setVisibility(View.GONE);
                //  new ImageUtility(R.mipmap.moren3).showImage("", std.thumbImageView);
            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the video capture
            }
        }
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
//            Intent intent = new Intent("com.android.camera.action.CROP");
//            intent.setDataAndType(imageUri, "image/*");
//            intent.putExtra("scale", true);
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//            startActivityForResult(intent, 111);
            Logger.d("......." + "获取图片成功，path=" + picFileFullName);
            if (outputImage.exists()) {
                creamPic.add(new Media(picFileFullName, "拍摄照片", 0, 0, 0, 0, ""));
                selectList.clear();
                selectList.addAll(select);
                if (creamPic.size() > 0) {
                    selectList.addAll(creamPic);
                }
                if ((select.size() + creamPic.size()) < 3) {
                    selectList.add(nll);
                }
                selectIvAdapter.notifyDataSetChanged();
            }

        }
        if (requestCode == ProjectConstant.SEL_PHOTO_REQUESTCODE) {
            if (data != null && data.getStringArrayListExtra("choicePicture") != null) {
                ArrayList<String> selImage = data.getStringArrayListExtra("choicePicture");
                if (selImage != null && selImage.size() > 0) {
                    Logger.d("selImage:" + selImage.get(0));
                    for (String s : selImage) {
                        select.add(new Media(s, "", 0, 0, 0, 0, ""));
                    }
                    selectList.clear();
                    selectList.addAll(select);
                    if ((select.size() + creamPic.size()) < 3) {
                        selectList.add(nll);
                    }
                    selectIvAdapter.notifyDataSetChanged();
                }
            }
        }

    }

    /**
     * 初始化游标
     */
    private void Init_Point() {

        pointViews = new ArrayList<ImageView>();
        ImageView imageView;
        for (int i = 0; i < pageViews.size(); i++) {
            imageView = new ImageView(mContext);
            imageView.setBackgroundResource(R.mipmap.d1);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    new ViewGroup.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 10;
            layoutParams.rightMargin = 10;
            layoutParams.width = 8;
            layoutParams.height = 8;
            layout_point.addView(imageView, layoutParams);
            if (i == 0 || i == pageViews.size() - 1) {
                imageView.setVisibility(View.GONE);
            }
            if (i == 1) {
                imageView.setBackgroundResource(R.mipmap.d2);
            }
            pointViews.add(imageView);

        }
    }

    /**
     * 初始化显示表情的viewpager
     */
    ArrayList<View> pageViews;

    private void Init_viewPager() {
        pageViews = new ArrayList<View>();
        // 左侧添加空页
        View nullView1 = new View(mContext);
        // 设置透明背景
        nullView1.setBackgroundColor(Color.TRANSPARENT);
        pageViews.add(nullView1);
        // 中间添加表情页
        final ArrayList<FaceAdapter> faceAdapters = new ArrayList<FaceAdapter>();
        for (int i = 0; i < emojis.size(); i++) {
            GridView view = new GridView(mContext);
            FaceAdapter adapter = new FaceAdapter(mContext, emojis.get(i));
            view.setAdapter(adapter);
            faceAdapters.add(adapter);
            view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int arg2, long id) {
                    ChatEmoji emoji = (ChatEmoji) faceAdapters.get(current).getItem(arg2);
                    if (emoji.getId() == R.drawable.face_del_icon) {
                        int selection = et.getSelectionStart();
                        String text = et.getText().toString();
                        if (selection > 0) {
                            String text2 = text.substring(selection - 1);
                            if ("]".equals(text2)) {
                                int start = text.lastIndexOf("[");
                                int end = selection;
                                et.getText().delete(start, end);
                                return;
                            }
                            et.getText().delete(selection - 1, selection);
                        }
                    }
                    if (!TextUtils.isEmpty(emoji.getCharacter())) {
                        SpannableString spannableString = FaceConversionUtil.getInstace()
                                .addFace(mContext, emoji.getId(), emoji.getCharacter());
                        et.append(spannableString);
                    }
                }
            });
            view.setNumColumns(7);
            view.setBackgroundColor(Color.TRANSPARENT);
            view.setHorizontalSpacing(1);
            view.setVerticalSpacing(1);
            view.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
            view.setCacheColorHint(0);
            view.setPadding(5, 0, 5, 0);
            view.setSelector(new ColorDrawable(Color.TRANSPARENT));
            view.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT));
            view.setGravity(Gravity.CENTER);
            pageViews.add(view);
        }

        // 右侧添加空页面
        View nullView2 = new View(mContext);
        // 设置透明背景
        nullView2.setBackgroundColor(Color.TRANSPARENT);
        pageViews.add(nullView2);
    }

    @ViewInject(R.id.vp_containss)
    private ViewPager vp_face;

    /**
     * 填充数据
     */
    /**
     * 当前表情页
     */
    private int current = 0;

    private void Init_Data() {
        vp_face.setAdapter(new ViewPagerAdapter(pageViews));

        vp_face.setCurrentItem(1);
        current = 0;
        vp_face.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                current = arg0 - 1;
                // 描绘分页点
                draw_Point(arg0);
                // 如果是第一屏或者是最后一屏禁止滑动，其实这里实现的是如果滑动的是第一屏则跳转至第二屏，如果是最后一屏则跳转到倒数第二屏.
                if (arg0 == pointViews.size() - 1 || arg0 == 0) {
                    if (arg0 == 0) {
                        vp_face.setCurrentItem(arg0 + 1);// 第二屏 会再次实现该回调方法实现跳转.
                        pointViews.get(1).setBackgroundResource(R.mipmap.d2);
                    } else {
                        vp_face.setCurrentItem(arg0 - 1);// 倒数第二屏
                        pointViews.get(arg0 - 1).setBackgroundResource(
                                R.mipmap.d2);
                    }
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

    }


}
