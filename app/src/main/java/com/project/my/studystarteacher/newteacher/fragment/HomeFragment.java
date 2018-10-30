package com.project.my.studystarteacher.newteacher.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.activity.home.AudioBookActivity;
import com.project.my.studystarteacher.newteacher.activity.home.AudioPayerActivity;
import com.project.my.studystarteacher.newteacher.activity.home.BorrowMangerActivity;
import com.project.my.studystarteacher.newteacher.activity.home.HomeZhuboActivity;
import com.project.my.studystarteacher.newteacher.activity.home.LoveDetailsActivity;
import com.project.my.studystarteacher.newteacher.activity.home.ReadActviActivity;
import com.project.my.studystarteacher.newteacher.activity.home.VideoDetailsActivity;
import com.project.my.studystarteacher.newteacher.activity.home.YduActiviActivity;
import com.project.my.studystarteacher.newteacher.activity.home.YueDuBangActivity;
import com.project.my.studystarteacher.newteacher.activity.home.ZhuboActivity;
import com.project.my.studystarteacher.newteacher.activity.my.LoveBossActivity;
import com.project.my.studystarteacher.newteacher.adapter.HomeClassAdapter;
import com.project.my.studystarteacher.newteacher.adapter.HomeYueduAdapter;
import com.project.my.studystarteacher.newteacher.adapter.HomeZhuboAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseFragment;
import com.project.my.studystarteacher.newteacher.bean.Activi_Ranking;
import com.project.my.studystarteacher.newteacher.bean.BannerBean;
import com.project.my.studystarteacher.newteacher.bean.ExpertLecture;
import com.project.my.studystarteacher.newteacher.bean.RankingBean;
import com.project.my.studystarteacher.newteacher.bean.ReadDynamics;
import com.project.my.studystarteacher.newteacher.bean.RedCard;
import com.project.my.studystarteacher.newteacher.bean.YaoSign;
import com.project.my.studystarteacher.newteacher.bean.ZhuBoBean;
import com.project.my.studystarteacher.newteacher.common.CommonWebViewActivity;
import com.project.my.studystarteacher.newteacher.common.ProjectConstant;
import com.project.my.studystarteacher.newteacher.common.TempSourceSupply;
import com.project.my.studystarteacher.newteacher.common.UserSingleton;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.project.my.studystarteacher.newteacher.utils.EventBusUtil;
import com.project.my.studystarteacher.newteacher.utils.EventWhatId;
import com.project.my.studystarteacher.newteacher.utils.ImageUtility;
import com.project.my.studystarteacher.newteacher.view.AdvertHorizontalUtil;
import com.project.my.studystarteacher.newteacher.view.CircleImageView;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.JsonUtil;

import org.greenrobot.eventbus.EventBus;
import org.xutils.common.util.DensityUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

@ContentView(R.layout.fragment_home)
public class HomeFragment extends BaseFragment {
    @ViewInject(R.id.yuedu_gv)
    private GridView yueduGv;
    @ViewInject(R.id.second_icon)
    private CircleImageView secondIcon;
    @ViewInject(R.id.second_name)
    private TextView secondName;
    @ViewInject(R.id.first_icon)
    private CircleImageView firstIcon;
    @ViewInject(R.id.first_name)
    private TextView firstName;
    @ViewInject(R.id.iv_f3)
    private ImageView ivF3;
    @ViewInject(R.id.one_tv)
    private ImageView onetv;
    @ViewInject(R.id.two_tv)
    private ImageView two_tv;
    @ViewInject(R.id.third_icon)
    private CircleImageView thirdIcon;
    @ViewInject(R.id.third_name)
    private TextView thirdName;
    @ViewInject(R.id.two_icon)
    private CircleImageView twoIcon;
    @ViewInject(R.id.two_name)
    private TextView twoName;
    @ViewInject(R.id.one_icon)
    private CircleImageView oneIcon;
    @ViewInject(R.id.one_name)
    private TextView oneName;
    @ViewInject(R.id.red)
    private TextView red;
    @ViewInject(R.id.yqNum)
    private TextView yqNum;
    @ViewInject(R.id.three_icon)
    private CircleImageView threeIcon;
    @ViewInject(R.id.three_name)
    private TextView threeName;
    @ViewInject(R.id.my_adress)
    private TextView my_adress;
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
    ImageUtility imageUtility;

    @Override
    public void init() {
        BannerAdModel(null);
        imageUtility = new ImageUtility(R.mipmap.moren);
        RelativeLayout.LayoutParams mp = (RelativeLayout.LayoutParams) rv_point.getLayoutParams();
        mp.setMargins(0, 0, 0, DensityUtil.dip2px(35));//分别是margin_top那四个属性
        rv_point.setLayoutParams(mp);
        HomeClassAdapter homeClassAdapter = new HomeClassAdapter(getActivity(), R.layout.item_class, TempSourceSupply.getTemp());
        gv_class.setAdapter(homeClassAdapter);

        yuedu_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ExpertLecture bean = (ExpertLecture) parent.getItemAtPosition(position);
                ToActivity(mContext, VideoDetailsActivity.class, bean.getId());
            }
        });
        my_adress.setText(UserSingleton.getInstance().getSysUser().getFymcheng());
        zhubo_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToActivity(mContext, AudioPayerActivity.class, zhuboList, position);
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
                EventBus.getDefault().post(new EventBusUtil(EventWhatId.TOVIDEO));
            }
        });
        findViewById(R.id.more_zhubo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToActivity(mContext, HomeZhuboActivity.class);
            }
        });
        findViewById(R.id.more_ranking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToActivity(mContext, YueDuBangActivity.class);
            }
        });
        getData();
        getYNum();
        getYy();
    }

    ArrayList<BannerBean> banner;

    public void getYNum() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                ArrayList<YaoSign> yaoSigns = JsonUtil.fromList((String) baseBean.getData(), "detail", YaoSign.class);
                yqNum.setText(yaoSigns.size() + "");
            }
        });
        Worker.statistics();
    }

    public void getYy() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                ArrayList<RedCard> detail = JsonUtil.fromList((String) baseBean.getData(), "detail", RedCard.class);
                red.setText(detail.size() + "");
            }
        });
        Worker.count();
    }

    private void getData() {
        getActivityRanking();
        getBanner();
        getExpertLecture();
        getReadDynamics();
        getActivityData();
        popularity();
    }

    ArrayList<ZhuBoBean> zhuboList;

    private void popularity() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                zhuboList = JsonUtil.fromList((String) baseBean.getData(), "myAudio", ZhuBoBean.class);
                HomeZhuboAdapter homeClassAdapter3 = new HomeZhuboAdapter(getActivity(), R.layout.item_zhubo, zhuboList);
                zhubo_gv.setAdapter(homeClassAdapter3);

            }
        });
        Worker.popularity("6666");
    }

    private void getActivityData() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                ArrayList<Activi_Ranking> rankingData = JsonUtil.fromList((String) baseBean.getData(), "dataInfo", Activi_Ranking.class);
                if (rankingData.size() > 2) {
                    oneName.setText(rankingData.get(0).getStudentName());

                    imageUtility.showImage(rankingData.get(0).getHeadPic(), oneIcon);
                    twoName.setText(rankingData.get(1).getStudentName());
                    imageUtility.showImage(rankingData.get(1).getHeadPic(), twoIcon);
                    thirdName.setText(rankingData.get(2).getStudentName());
                    imageUtility.showImage(rankingData.get(2).getHeadPic(), threeIcon);
                }

            }
        });
        Worker.getActivityData();

    }

    private void getActivityRanking() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                ArrayList<RankingBean> rankingData = JsonUtil.fromList((String) baseBean.getData(), "rankingData", RankingBean.class);
                if (rankingData.size() > 2) {
                    firstName.setText(rankingData.get(0).getXs_xming());
                    imageUtility.showImage(rankingData.get(0).getXs_pic(), firstIcon);
                    secondName.setText(rankingData.get(1).getXs_xming());
                    imageUtility.showImage(rankingData.get(1).getXs_pic(), secondIcon);
                    thirdName.setText(rankingData.get(2).getXs_xming());
                    imageUtility.showImage(rankingData.get(2).getXs_pic(), thirdIcon);
                }

            }
        });
        Worker.getActivityRanking();

    }

    private void getExpertLecture() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                ArrayList<ExpertLecture> expertLecture = JsonUtil.fromList((String) baseBean.getData(), "expertLecture", ExpertLecture.class);
                HomeYueduAdapter homeClassAdapter2 = new HomeYueduAdapter(getActivity(), R.layout.item_yuedu, expertLecture);
                yuedu_gv.setAdapter(homeClassAdapter2);
            }
        });
        Worker.getExpertLecture(0, 2);
    }

    private void getReadDynamics() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                final ArrayList<ReadDynamics> readDynamics = JsonUtil.fromList((String) baseBean.getData(), "readDynamics", ReadDynamics.class);
                if (readDynamics.size() > 1) {
                    imageUtility.showImage(readDynamics.get(0).getShow_Pic(), onetv);
                    onetv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, CommonWebViewActivity.class);
                            intent.putExtra(ProjectConstant.WV_TITLE, readDynamics.get(0).getTitle());
                            intent.putExtra(ProjectConstant.WV_URL, readDynamics.get(0).getUrl());
                            startActivity(intent);
                        }
                    });

                    imageUtility.showImage(readDynamics.get(1).getShow_Pic(), two_tv);
                    two_tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, CommonWebViewActivity.class);
                            intent.putExtra(ProjectConstant.WV_TITLE, readDynamics.get(1).getTitle());
                            intent.putExtra(ProjectConstant.WV_URL, readDynamics.get(1).getUrl());
                            startActivity(intent);
                        }
                    });
                }

            }
        });
        Worker.getReadDynamics(0, 2);
    }

    private void getBanner() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                banner = JsonUtil.fromList((String) baseBean.getData(), "banner", BannerBean.class);
                ArrayList<String> strings = new ArrayList<>();
                for (BannerBean b : banner) {
                    strings.add(b.getPic());
                }
                BannerAdModel(strings);
            }
        });
        Worker.getBanner();

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
                        if (banner != null) {
                            BannerBean bannerBean = banner.get(position);
                            Intent intent = new Intent(mContext, CommonWebViewActivity.class);
                            intent.putExtra(ProjectConstant.WV_TITLE, bannerBean.getName());
                            intent.putExtra(ProjectConstant.WV_URL, bannerBean.getUrl());
                            startActivity(intent);
                        }

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
