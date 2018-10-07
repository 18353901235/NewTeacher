package com.project.my.studystarteacher.newteacher.fragment;


import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.activity.home.AudioBookActivity;
import com.project.my.studystarteacher.newteacher.activity.home.AudioPayerActivity;
import com.project.my.studystarteacher.newteacher.activity.home.BorrowMangerActivity;
import com.project.my.studystarteacher.newteacher.activity.home.HomeZhuboActivity;
import com.project.my.studystarteacher.newteacher.activity.home.LoveDetailsActivity;
import com.project.my.studystarteacher.newteacher.activity.home.ReadActviActivity;
import com.project.my.studystarteacher.newteacher.activity.home.VideoDetailsActivity;
import com.project.my.studystarteacher.newteacher.activity.home.YduActiviActivity;
import com.project.my.studystarteacher.newteacher.activity.home.ZhuboActivity;
import com.project.my.studystarteacher.newteacher.activity.my.LoveBossActivity;
import com.project.my.studystarteacher.newteacher.adapter.HomeClassAdapter;
import com.project.my.studystarteacher.newteacher.adapter.HomeYueduAdapter;
import com.project.my.studystarteacher.newteacher.adapter.HomeZhuboAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseFragment;
import com.project.my.studystarteacher.newteacher.common.TempSourceSupply;
import com.project.my.studystarteacher.newteacher.view.AdvertHorizontalUtil;

import org.xutils.common.util.DensityUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

@ContentView(R.layout.fragment_home)
public class HomeFragment extends BaseFragment {
    @ViewInject(R.id.vp_home)
    private ViewPager vpHome;
    @ViewInject(R.id.ll_point)
    private LinearLayout llPoint;
    @ViewInject(R.id.rv_point)
    private RelativeLayout rv_point;

    @ViewInject(R.id.gv_class)
    private GridView gv_class;
    @ViewInject(R.id.yuedu_gv)
    private GridView yuedu_gv;
    @ViewInject(R.id.zhubo_gv)
    private GridView zhubo_gv;

    private AdvertHorizontalUtil adUtil;
    @ViewInject(R.id.love)
    private LinearLayout love;
    @ViewInject(R.id.yq)
    private LinearLayout yq;

    @Override
    public void init() {
        BannerAdModel(TempSourceSupply.getImgData());
        RelativeLayout.LayoutParams mp = (RelativeLayout.LayoutParams) rv_point.getLayoutParams();
        mp.setMargins(0, 0, 0, DensityUtil.dip2px(35));//分别是margin_top那四个属性
        rv_point.setLayoutParams(mp);
        HomeClassAdapter homeClassAdapter = new HomeClassAdapter(getActivity(), R.layout.item_class, TempSourceSupply.getTemp());
        gv_class.setAdapter(homeClassAdapter);
        HomeYueduAdapter homeClassAdapter2 = new HomeYueduAdapter(getActivity(), R.layout.item_yuedu, TempSourceSupply.getTemp());
        yuedu_gv.setAdapter(homeClassAdapter2);
        yuedu_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToActivity(mContext, VideoDetailsActivity.class);
            }
        });
        HomeZhuboAdapter homeClassAdapter3 = new HomeZhuboAdapter(getActivity(), R.layout.item_zhubo, TempSourceSupply.getTemp());
        zhubo_gv.setAdapter(homeClassAdapter3);
        zhubo_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToActivity(mContext, AudioPayerActivity.class);
            }
        });
        gv_class.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        ToActivity(mContext, AudioBookActivity.class);
                        break;
                    case 1:
                        ToActivity(mContext, BorrowMangerActivity.class);
                        break;
                    case 2:
                        ToActivity(mContext, ZhuboActivity.class);
                        break;
                    case 3:
                        ToActivity(mContext, ReadActviActivity.class);
                        break;
                }
            }
        });
        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToActivity(mContext, LoveDetailsActivity.class);
            }
        });
        yq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToActivity(mContext, LoveBossActivity.class);
            }
        });
        findViewById(R.id.moreyue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToActivity(mContext, YduActiviActivity.class);
            }
        });
        findViewById(R.id.more_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To2
                ToActivity(mContext, HomeZhuboActivity.class);

            }
        });
        findViewById(R.id.more_zhubo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To2


            }
        });
    }

    ;

    private void BannerAdModel(ArrayList imageUrls) {
        /*banner滑动广告也*/
        if (imageUrls != null && imageUrls.isEmpty()) {
            imageUrls.add("drawable://" + R.mipmap.moren3);
        }
        if (imageUrls != null && !imageUrls.isEmpty()) {
            // AdvertHorizontalVidoeUtil
            if (adUtil == null) {
                adUtil = new AdvertHorizontalUtil(getActivity(), vpHome,
                        llPoint, imageUrls,
                        3000, new AdvertHorizontalUtil.AdvertisCallBack() {
                    @Override
                    public void AdvertisClick(int position, Bitmap bit) {

                    }

                    @Override
                    public void AdvertisChage(int position) {

                    }
                });
                adUtil.startTrans();
            } else {
                adUtil.setList(imageUrls);
            }
        }
    }
}
