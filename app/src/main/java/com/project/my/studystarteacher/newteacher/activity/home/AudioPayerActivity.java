package com.project.my.studystarteacher.newteacher.activity.home;

import android.content.ComponentName;
import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.project.my.studystarteacher.newteacher.bean.ZhuBoBean;
import com.project.my.studystarteacher.newteacher.common.Constant;
import com.project.my.studystarteacher.newteacher.service.MyService;
import com.project.my.studystarteacher.newteacher.utils.ImageUtility;
import com.zhouqiang.framework.util.ToastUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.BlurTransformation;

@ContentView(R.layout.activity_audio_payer)
public class AudioPayerActivity extends BaseActivity {
    @ViewInject(R.id.top)
    private RelativeLayout top;
    @ViewInject(R.id.img)
    private ImageView img;
    @ViewInject(R.id.bac_gry)
    private ImageView bac_gry;

    @ViewInject(R.id.start)
    private ImageView start;
    @ViewInject(R.id.pre)
    private ImageView pre;
    @ViewInject(R.id.next)
    private ImageView next;
    @ViewInject(R.id.list)
    private ImageView list;
    @ViewInject(R.id.bac_blur)
    private ImageView bac_blur;
    @ViewInject(R.id.cd_name)
    private TextView cd_name;
    @ViewInject(R.id.desc)
    private TextView desc;
    public static SeekBar skbProgress;
    public static ArrayList<String> song_list;
    public static int position = 0;

    public static TextView tv_currentTime;
    public static TextView tv_sumTime;
    @ViewInject(R.id.info)
    private LinearLayout info;
    @ViewInject(R.id.msg)
    private TextView msg;

    ComponentName componentName;
    Animation operatingAnim;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(Constant.Action.STOP);
        intent.setComponent(componentName);
        startService(intent);
    }

    ImageUtility imageUtility;
    int positon;

    @Override
    protected void init() {
        top.getBackground().mutate().setAlpha(0);
        final ArrayList<ZhuBoBean> data = (ArrayList<ZhuBoBean>) getIntent().getSerializableExtra("data");
        positon = getIntent().getIntExtra("data2", 0);
        imageUtility = new ImageUtility(R.mipmap.moren);

        getRight().setBackgroundResource(R.mipmap.btn_share);
        setData(data);
        operatingAnim = AnimationUtils.loadAnimation(this, R.anim.tip);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        skbProgress = (SeekBar) findViewById(R.id.skbProgress);
        tv_currentTime = (TextView) findViewById(R.id.tv_currentTime);
        tv_sumTime = (TextView) findViewById(R.id.tv_sumTime);
        componentName = new ComponentName(this, MyService.class);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.setVisibility(View.GONE);
                img.clearAnimation();
                img.setVisibility(View.GONE);
                bac_gry.setVisibility(View.GONE);
                msg.setVisibility(View.VISIBLE);
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setComponent(componentName);
                if (start.getBackground().getCurrent().getConstantState().equals(getResources().getDrawable(R.mipmap.paly).getConstantState())) {
                    if (msg.getVisibility() != View.VISIBLE) {
                        img.startAnimation(operatingAnim);

                    }
                    intent.setAction(Constant.Action.RESTART);
                    start.setBackgroundResource(R.mipmap.play_btn_play);
                } else {
                    intent.setAction(Constant.Action.PAUSE);
                    start.setBackgroundResource(R.mipmap.paly);
                    img.clearAnimation();
                }
                startService(intent);
            }
        });
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setComponent(componentName);
                if (positon - 1 > 0) {
                    positon--;
                    intent.putExtra("song", data.get(positon).getUrl());
                    intent.setAction(Constant.Action.FRONT);
                    startService(intent);
                    setData(data);
                } else {
                    ToastUtil.showLongToast(mContext, "前面没有了");
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setComponent(componentName);
                if (positon + 1 < data.size()) {
                    positon++;
                    intent.putExtra("song", data.get(positon).getUrl());
                    intent.setAction(Constant.Action.NEXT);
                    startService(intent);
                    setData(data);
                } else {
                    positon = 0;
                    ToastUtil.showLongToast(mContext, "已到最后一首了，即将播放第一首");
                    intent.putExtra("song", data.get(positon).getUrl());
                    intent.setAction(Constant.Action.NEXT);
                    setData(data);
                    startService(intent);
                }

            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //开始
        Intent intent2 = new Intent(Constant.Action.PLAY);
        intent2.setComponent(componentName);
        intent2.putExtra("song", data.get(positon).getUrl());
        startService(intent2);
        img.startAnimation(operatingAnim);
        Glide.with(this).load(data.get(positon).getHeadImg()).bitmapTransform(new BlurTransformation(mContext, 25)).into(bac_blur);
    }

    private void setData(ArrayList<ZhuBoBean> data) {
        imageUtility.showImage(data.get(positon).getHeadImg(), img);
        imageUtility.showImage(data.get(positon).getHeadImg(), bac_blur);
        msg.setText(data.get(positon).getBookdesc());

        cd_name.setText(data.get(positon).getBookname());
        desc.setText(data.get(positon).getAuthor());
    }

    @Override
    public void onBackPressed() {
        if (msg.getVisibility() == View.VISIBLE) {
            msg.setVisibility(View.GONE);
            info.setVisibility(View.VISIBLE);
            if (start.getBackground().getCurrent().getConstantState().equals(getResources().getDrawable(R.mipmap.paly).getConstantState())) {
                img.clearAnimation();
            } else {
                img.startAnimation(operatingAnim);
            }
            img.setVisibility(View.VISIBLE);
            bac_gry.setVisibility(View.VISIBLE);
            return;
        }
        super.onBackPressed();
    }
}
