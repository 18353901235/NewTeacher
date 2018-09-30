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
import com.project.my.studystarteacher.newteacher.common.Constant;
import com.project.my.studystarteacher.newteacher.service.MyService;

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
    @ViewInject(R.id.bac_blur)
    private ImageView bac_blur;
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

    @Override
    protected void init() {
        top.getBackground().mutate().setAlpha(0);
        getRight().setBackgroundResource(R.mipmap.btn_share);
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
        //开始
        Intent intent2 = new Intent(Constant.Action.PLAY);
        intent2.setComponent(componentName);
        intent2.putExtra("song", "http://www.ytmp3.cn/down/53393.mp3");
        startService(intent2);
        img.startAnimation(operatingAnim);
        Glide.with(this).load(R.mipmap.pic).bitmapTransform(new BlurTransformation(mContext, 25)).into(bac_blur);


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
