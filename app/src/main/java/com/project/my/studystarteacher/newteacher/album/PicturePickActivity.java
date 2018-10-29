package com.project.my.studystarteacher.newteacher.album;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.zhouqiang.framework.util.ToastUtil;

import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Created by zq on 2016/2/15.
 */
public class PicturePickActivity extends BaseActivity {
    private GridView photoGrid;// 图片列表
    private TextView btnNext;// 底部下一步按钮
    private TextView titleName;// 头部的标题
    // private ProgressDialog mProgressDialog;
    private HashSet<String> mDirPaths = new HashSet<String>();// 临时的辅助类，用于防止同一个文件夹的多次扫描
    private List<ImageFloder> mImageFloders = new ArrayList<ImageFloder>();// 扫描拿到所有的图片文件夹
    int totalCount = 0;
    private File mImgDir = new File("");// 图片数量最多的文件夹
    private int mPicsSize;// 存储文件夹中的图片数量
    private List<String> mImgs = new ArrayList<String>();// 所有的图片
    private List<PicturePickBean> lisImages = new ArrayList<PicturePickBean>();// 所有图片倒叙
    private int mScreenHeight;
    private LinearLayout dirLayout;
    private ListView dirListView;
    private RelativeLayout headLayout;
    private int mScreenWidth;
    private ImageFloderAdapter floderAdapter;
    private GridItemAdapter gridItemAdapter;
    private PopupWindow popupWindow;
    private View dirview;
    private static final int TAKE_CAMERA_PICTURE = 1000;// 拍照
    private String path;
    private File dir;
    private String imagename;
    private int pickNum;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gridItemAdapter.mSelectedImage.clear();
        setContentView(R.layout.activity_picture_pick);
        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        mScreenHeight = outMetrics.heightPixels;
        mScreenWidth = outMetrics.widthPixels;
        initView();
        initInstance();
        initData();
        initListener();
    }

    @Override
    protected void init() {

    }

    private void initView() {
        photoGrid = (GridView) findViewById(R.id.gird_photo_list);
        titleName = getCommonTitle();
        headLayout = (RelativeLayout) findViewById(R.id.flAll);
        btnNext = getRightTextView();
    }

    private void initInstance() {
        titleName.setVisibility(View.VISIBLE);
        titleName.setText("相册图片↓");
        btnNext.setText("确认");
        pickNum = getIntent().getIntExtra("pictureNum", 1);
        getImages();
    }

    private void initData() {
        // 一定要修改文件路径名称
        path = Environment.getExternalStorageDirectory() + "/"
                + "sanmidemo/photo/";
        dir = new File(path);
        if (!dir.exists())
            dir.mkdirs();
        if (mImgDir == null) {
            Toast.makeText(getApplicationContext(), "一张图片没扫描到",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (mImgDir.exists()) {
            mImgs = Arrays.asList(mImgDir.list());
        }
        int imgSize = mImgs.size();
        for (int i = 0; i < imgSize; i++) {
            PicturePickBean pickBean = new PicturePickBean();
            pickBean.setPickPath(mImgs.get(i));
            lisImages.add(0, pickBean);
        }
        gridItemAdapter = new GridItemAdapter(this, lisImages,
                mImgDir.getAbsolutePath(), pickNum);
        photoGrid.setAdapter(gridItemAdapter);
        gridItemAdapter.setOnPhotoSelectedListener(new GridItemAdapter.OnPhotoSelectedListener() {
            @Override
            public void photoClick(List<String> number) {
//                btnNext.setText("下一步(" + number.size() + "张)");
                titleName.setText("已选择(" + number.size() + "张)");
            }

            @Override
            public void takePhoto() {
                imagename = getTimeName(System.currentTimeMillis())
                        + ".jpg";
                String state = Environment.getExternalStorageState();
                if (state.equals(Environment.MEDIA_MOUNTED)) {
                    Intent intent = new Intent(
                            MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(dir, imagename);// localTempImgDir和localTempImageFileName是自己定义的名字
                    Uri u = Uri.fromFile(f);
                    intent.putExtra(
                            MediaStore.Images.Media.ORIENTATION, 0);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, u);
                    startActivityForResult(intent, TAKE_CAMERA_PICTURE);
                } else {
                    ToastUtil.showShortToast(mContext, "请插入SD卡");
                }

            }
        });
    }

    private void initListener() {
        titleName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 初始化展示文件夹的popupWindw
                initListDirPopupWindw();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < gridItemAdapter.mSelectedImage.size(); i++) {
                }
                ArrayList<String> listPath = new ArrayList<String>();
                int size = gridItemAdapter.mSelectedImage.size();
                for (int i = 0; i < size; i++) {
                    listPath.add(gridItemAdapter.mSelectedImage.get(i));
                }
                Intent intent = new Intent();
                intent.putExtra("choicePicture", listPath);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        photoGrid.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                for (int i = 0; i < lisImages.size(); i++) {
                    if (i >= (firstVisibleItem - 6)
                            && i < (firstVisibleItem + visibleItemCount + 6)) {

                    } else {
                        try {
                            Bitmap bit = lisImages.get(i).getBit();
                            if (bit != null && !bit.isRecycled()) {
                                lisImages.get(i).setBit(null);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    /**
     * 利用ContentProvider扫描手机中的图片，此方法在运行在子线程中 完成图片的扫描，最终获得jpg最多的那个文件夹
     */
    private void getImages() {
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "暂无外部存储", Toast.LENGTH_SHORT).show();
            return;
        }
        // 显示进度条
        // mProgressDialog = ProgressDialog.show(this, null, "正在加载...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                String firstImage = null;
                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver mContentResolver = PicturePickActivity.this
                        .getContentResolver();
                // 只查询jpeg和png的图片
                Cursor mCursor = mContentResolver.query(mImageUri, null,
                        MediaStore.Images.Media.MIME_TYPE + "=? or "
                                + MediaStore.Images.Media.MIME_TYPE + "=?",
                        new String[]{"image/jpeg", "image/png"},
                        MediaStore.Images.Media.DATE_MODIFIED);
                while (mCursor.moveToNext()) {
                    // 获取图片的路径
                    String path = mCursor.getString(mCursor
                            .getColumnIndex(MediaStore.Images.Media.DATA));
                    // 拿到第一张图片的路径
                    if (firstImage == null)
                        firstImage = path;
                    // 获取该图片的父路径名
                    File parentFile = new File(path).getParentFile();
                    if (parentFile == null)
                        continue;
                    String dirPath = parentFile.getAbsolutePath();
                    ImageFloder imageFloder = null;
                    if (mDirPaths.contains(dirPath)) {
                        continue;
                    } else {
                        mDirPaths.add(dirPath);
                        // 初始化imageFloder
                        imageFloder = new ImageFloder();
                        imageFloder.setDir(dirPath);
                        imageFloder.setFirstImagePath(path);
                    }
                    if (parentFile.list() == null)
                        continue;
                    int picSize = parentFile.list(new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String filename) {
                            return filename.endsWith(".jpg")
                                    || filename.endsWith(".png")
                                    || filename.endsWith(".jpeg");
                        }
                    }).length;
                    totalCount += picSize;
                    imageFloder.setCount(picSize);
                    mImageFloders.add(imageFloder);

                    if (picSize > mPicsSize) {
                        mPicsSize = picSize;
                        mImgDir = parentFile;
                    }
                }
                mCursor.close();
                // 扫描完成，辅助的HashSet也就可以释放内存了
                mDirPaths = null;
                // 通知Handler扫描图片完成
                mHandler.sendEmptyMessage(0x110);

            }
        }).start();

    }

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            // mProgressDialog.dismiss();
            // 异步线程获取数据为了扫描图片完成后能够进行初次页面加载
            initData();
        }
    };

    public static String getTimeName(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date(time);
        return formatter.format(date);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_CAMERA_PICTURE:
                ArrayList<String> listPath = null;
                if (resultCode == -1) {
                    listPath = new ArrayList<String>();
                    String photoPath = new StringBuffer(path).append(imagename)
                            .toString();
                    listPath.add(photoPath);
                }
                Intent intent = new Intent();
                intent.putExtra("choicePicture", listPath);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }

    /**
     * 初始化展示文件夹的popupWindw
     */
    private void initListDirPopupWindw() {
        if (popupWindow == null) {
            dirview = LayoutInflater.from(PicturePickActivity.this).inflate(
                    R.layout.pop_picture_list, null);
            dirListView = (ListView) dirview.findViewById(R.id.id_list_dirs);
            popupWindow = new PopupWindow(dirview, ViewGroup.LayoutParams.MATCH_PARENT,
                    mScreenHeight * 3 / 5);
            floderAdapter = new ImageFloderAdapter(PicturePickActivity.this,
                    mImageFloders);
            dirListView.setAdapter(floderAdapter);
        }
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 显示的位置为:屏幕的宽度的一半-PopupWindow的高度的一半
        popupWindow.showAsDropDown(headLayout, 0, 0);
        dirListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                titleName.setText(mImageFloders.get(position).getName());
                mImgDir = new File(mImageFloders.get(position).getDir());
                mImgs = Arrays.asList(mImgDir.list(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String filename) {
                        return filename.endsWith(".jpg")
                                || filename.endsWith(".png")
                                || filename.endsWith(".jpeg");
                    }
                }));
                for (int i = 0; i < mImageFloders.size(); i++) {
                    mImageFloders.get(i).setSelected(false);
                }
                mImageFloders.get(position).setSelected(true);
                floderAdapter.changeData(mImageFloders);
                int imgSize = mImgs.size();
                lisImages.clear();
                for (int i = 0; i < imgSize; i++) {
                    PicturePickBean pickBean = new PicturePickBean();
                    pickBean.setPickPath(mImgs.get(i));
                    lisImages.add(0, pickBean);
                    ;
                }
                gridItemAdapter.changeData(lisImages,
                        mImageFloders.get(position).getDir());
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        for (int i = 0; i < lisImages.size(); i++) {
            if (lisImages != null && lisImages.get(i) != null && lisImages.get(i).getBit() != null && lisImages.get(i).getBit().isRecycled()) {
                lisImages.get(i).getBit().recycle();
            }
            lisImages.get(i).setBit(null);
        }
        System.gc();
        super.onDestroy();
    }
}
