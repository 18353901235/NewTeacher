package com.project.my.studystarteacher.newteacher.base;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.project.my.studystarteacher.newteacher.R;
import com.zhouqiang.framework.SanmiFragment;

import org.xutils.x;

import java.io.Serializable;

public abstract class BaseFragment extends SanmiFragment {

    private View view;
    @Nullable
    private Bundle savedInstanceState;
    public Activity mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();

        return x.view().inject(this, inflater, container);
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
    //  public void moban() {
//        MiceNetWorker Worker = new MiceNetWorker(mContext);
//        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
//            @Override
//            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
    //   super.onSuccess(netWorker, netTask, baseBean);
//            }
//        });
//        Worker.updateFacade(path);
//    }

//    @PermissionGrant(10)
//    public void requestAudioSuccess() {
//        Intent intent = new Intent("com.baidu.action.RECOGNIZE_SPEECH");
//        intent.putExtra("grammar", "asset:///baidu_speech_grammardemo.bsg"); // 设置离线的授权文件(离线模块需要授权), 该语法可以用自定义语义工具生成, 链接http://yuyin.baidu.com/asr#m5
//        //intent.putExtra("slot-data", your slots); // 设置grammar中需要覆盖的词条,如联系人名
//        startActivityForResult(intent, 1);
//    }
//
//    @PermissionDenied(10)
//    public void requestAudioFailed() {
//        Toast.makeText(getActivity(), "权限授权失败，无法使用语言识别", Toast.LENGTH_SHORT).show();
//    }
//
//    public void speak() {
//        MPermissions.requestPermissions(this, 10, Manifest.permission
//                .RECORD_AUDIO);
//    }

    protected ImageButton getLeft() {
        ImageButton ibLeft = (ImageButton) view.findViewById(R.id.ib_left);
        ibLeft.setVisibility(View.VISIBLE);
        return ibLeft;
    }

    protected void hideLeftButton() {
        ((ImageButton) view.findViewById(R.id.ib_left)).setVisibility(View.INVISIBLE);
    }

    protected ImageButton getRight() {
        ImageButton ibRight = (ImageButton) view.findViewById(R.id.ib_right);
        ibRight.setVisibility(View.VISIBLE);
        return ibRight;
    }

    protected void hideRightButton() {
        ((ImageButton) view.findViewById(R.id.ib_right)).setVisibility(View.INVISIBLE);
    }

    protected TextView getRightTextView() {
        TextView tvRight = (TextView) view.findViewById(R.id.tv_right);
        tvRight.setVisibility(View.VISIBLE);
        return tvRight;
    }

    protected void hideRightView() {
        ((TextView) view.findViewById(R.id.tv_right)).setVisibility(View.INVISIBLE);
    }

    protected TextView getCommonTitle() {
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvTitle.setVisibility(View.VISIBLE);
        return tvTitle;
    }

    protected void hideCommonTitle() {
        ((TextView) view.findViewById(R.id.tv_title)).setVisibility(View.INVISIBLE);
    }

    /***
     * 设置返回按钮
     *
     * @param v
     */
    public void ButtonBackClick(View v) {

    }

    public static void ToActivity(Activity context, Class clazz) {
        context.startActivity(new Intent(context, clazz));
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

    public static void ToActivity(Activity context, Class clazz, String i, String type) {
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

    protected void setTopBackBg(int id) {
        RelativeLayout tvTitle = (RelativeLayout) findViewById(R.id.flAll);
        tvTitle.setBackgroundResource(id);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.view = view;
        this.savedInstanceState = savedInstanceState;
        super.onViewCreated(view, savedInstanceState);
        setContentView(view);
        //   TypefaceUtil.replaceFont(getActivity(), "fonts/center_xi.ttf");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }
}
