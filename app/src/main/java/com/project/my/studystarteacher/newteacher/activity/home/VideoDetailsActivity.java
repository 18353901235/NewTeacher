package com.project.my.studystarteacher.newteacher.activity.home;

import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.BaseVPFAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.project.my.studystarteacher.newteacher.base.BaseFragment;
import com.project.my.studystarteacher.newteacher.fragment.HudongFragment;
import com.project.my.studystarteacher.newteacher.fragment.VideoJsFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

@ContentView(R.layout.activity_video_details)
public class VideoDetailsActivity extends BaseActivity {
    @ViewInject(R.id.videoplayer)
    private JzvdStd payer;
    @ViewInject(R.id.js)
    private RadioButton js;
    @ViewInject(R.id.hd)
    private RadioButton hd;
    @ViewInject(R.id.js_v)
    private View js_v;
    @ViewInject(R.id.hd_v)
    private View hd_v;
    @ViewInject(R.id.viewPager)
    private ViewPager viewPager;
    private VideoJsFragment mkHomeFragment = new VideoJsFragment();
    private HudongFragment test1 = new HudongFragment();

    //http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4
    @Override
    protected void init() {
        getRight().setBackgroundResource(R.mipmap.btn_share);
        getCommonTitle().setText("视频名字");
        payer.setUp(""
                , "", Jzvd.SCREEN_WINDOW_NORMAL);
        payer.thumbImageView.setImageURI(Uri.parse("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640"));

        js.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                js_v.setVisibility(isChecked ? View.VISIBLE : View.INVISIBLE);
                hd.setChecked(!isChecked);
                if (isChecked) {
                    viewPager.setCurrentItem(0);
                }
            }
        });
        hd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                hd_v.setVisibility(isChecked ? View.VISIBLE : View.INVISIBLE);
                js.setChecked(!isChecked);
                if (isChecked) {
                    viewPager.setCurrentItem(1);
                }
            }
        });

        ArrayList<BaseFragment> frg = new ArrayList<BaseFragment>();
        frg.add(mkHomeFragment);
        frg.add(test1);
        viewPager.setAdapter(new BaseVPFAdapter(getSupportFragmentManager(), frg));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    js.setChecked(true);
                } else {
                    hd.setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


}
