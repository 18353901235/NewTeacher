package com.project.my.studystarteacher.newteacher.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.project.my.studystarteacher.newteacher.R;
import com.zhouqiang.framework.SanmiActivity;

import org.xutils.x;

import java.io.Serializable;

import crossoverone.statuslib.StatusUtil;

/**
 * Created by zq on 2016/9/28.
 */
public abstract class BaseActivity extends SanmiActivity {
    public Handler handOk = new Handler();

    @Override
    protected boolean onKeyBack() {
        return false;
    }

    @Override
    protected boolean onKeyMenu() {
        return false;
    }

    public void moban() {
//        MiceNetWorker Worker = new MiceNetWorker(mContext);
//        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
//            @Override
//            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
        //   super.onSuccess(netWorker, netTask, baseBean);
//            }
//        });
//        Worker.updateFacade(path);
    }

    //  AndroidBug5497Workaround.assistActivity(this);//加上这一行,一定要在第一行
//    setStatusColor();
//    setSystemInvadeBlack();


    public static void ToActivity(Activity context, Class clazz) {
        context.startActivity(new Intent(context, clazz));
    }

    public static void ToActivity(Activity context, Class clazz, boolean isFinsh) {
        Intent intent = new Intent(context, clazz);
        intent.putExtra("isFinsh", isFinsh);
        context.startActivity(intent);
    }

    public static void ToActivity(Activity context, Class clazz, int i) {
        Intent intent = new Intent(context, clazz);
        intent.putExtra("data", i);
        context.startActivity(intent);
    }

    public static void ToActivity(Activity context, Class clazz, String i) {
        Intent intent = new Intent(context, clazz);
        intent.putExtra("data", i);
        context.startActivity(intent);
    }

    public static void ToActivity(Activity context, Class clazz, Serializable i) {
        Intent intent = new Intent(context, clazz);
        intent.putExtra("data", i);
        context.startActivity(intent);
    }

    public static void ToActivity(Activity context, Class clazz, Serializable i, String type) {
        Intent intent = new Intent(context, clazz);
        intent.putExtra("data", i);
        intent.putExtra("data2", type);
        context.startActivity(intent);
    }

    public static void ToActivity(Activity context, Class clazz, Serializable i, boolean type) {
        Intent intent = new Intent(context, clazz);
        intent.putExtra("data", i);
        intent.putExtra("data2", type);
        context.startActivity(intent);
    }

    public static void ToActivity(Activity context, Class clazz, Serializable i, int type) {
        Intent intent = new Intent(context, clazz);
        intent.putExtra("data", i);
        intent.putExtra("data2", type);
        context.startActivity(intent);
    }

    /**
     * @param savedInstanceState
     * @PermissionGrant(0) public void requestSdcardSuccess() {
     * Intent intent = new Intent(mContext, PicturePickActivity.class);
     * intent.putExtra("pictureNum", 1);
     * startActivityForResult(intent, ProjectConstant.SEL_PHOTO_REQUESTCODE);
     * }
     * @PermissionDenied(0) public void requestSdcardFailed() {
     * Toast.makeText(this, "权限授权失败，无法进入相册", Toast.LENGTH_SHORT).show();
     * }
     * <p>
     * private void selectImage() {
     * MPermissions.requestPermissions(this, 0, Manifest.permission
     * .READ_PHONE_STATE, Manifest.permission
     * .READ_EXTERNAL_STORAGE);
     * }
     * @Override public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
     * MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
     * super.onRequestPermissionsResult(requestCode, permissions, grantResults);
     * }
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 状态栏透明
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            setStatusColor();
            setSystemInvadeBlack();
        }
        x.view().inject(this);
        // TypefaceUtil.replaceFont(this, "fonts/center_xi.ttf");
        init();
    }

    protected void setStatusColor() {
        StatusUtil.setUseStatusBarColor(this, Color.TRANSPARENT, R.color.colorTitleBg);
    }

    protected void setSystemInvadeBlack() {
        // 第二个参数是是否沉浸,第三个参数是状态栏字体是否为黑色
        StatusUtil.setSystemStatus(this, true, true);
    }

    protected abstract void init();

    protected ImageButton getLeft() {
        ImageButton ibLeft = (ImageButton) findViewById(R.id.ib_left);
        ibLeft.setVisibility(View.VISIBLE);
        return ibLeft;
    }

    protected void hideLeftButton() {
        ((ImageButton) findViewById(R.id.ib_left)).setVisibility(View.INVISIBLE);
    }

    protected ImageButton getRight2() {
        ImageButton ibRight = (ImageButton) findViewById(R.id.ib_right2);
        ibRight.setVisibility(View.VISIBLE);
        return ibRight;
    }

    protected ImageButton getRight() {
        ImageButton ibRight = (ImageButton) findViewById(R.id.ib_right);
        ibRight.setVisibility(View.VISIBLE);
        return ibRight;
    }

    protected void hideRightButton() {
        ((ImageButton) findViewById(R.id.ib_right)).setVisibility(View.INVISIBLE);
    }

    protected TextView getRightTextView() {
        TextView tvRight = (TextView) findViewById(R.id.tv_right);
        tvRight.setVisibility(View.VISIBLE);
        return tvRight;
    }

    protected void hideRightView() {
        ((TextView) findViewById(R.id.tv_right)).setVisibility(View.INVISIBLE);
    }

    protected TextView getCommonTitle() {
        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setVisibility(View.VISIBLE);
        return tvTitle;
    }

    protected void setTopBackBg(int id) {
        RelativeLayout tvTitle = (RelativeLayout) findViewById(R.id.flAll);
        tvTitle.setBackgroundResource(id);
    }


    protected void hideCommonTitle() {
        ((TextView) findViewById(R.id.tv_title)).setVisibility(View.INVISIBLE);
    }

    /***
     * 设置返回按钮
     *
     * @param v
     */
    public void ButtonBackClick(View v) {
        // 隐藏键盘
        ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(v.getWindowToken(), 0);
        onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //  MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //  MobclickAgent.onPause(this);
    }
}
