package com.project.my.studystarteacher.newteacher.fragment;


import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.project.my.studystarteacher.newteacher.R;
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
        HomeZhuboAdapter homeClassAdapter3 = new HomeZhuboAdapter(getActivity(), R.layout.item_zhubo, TempSourceSupply.getTemp());
        zhubo_gv.setAdapter(homeClassAdapter3);
    }

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
