package com.project.my.studystarteacher.newteacher.activity.home;

import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.BaseVPFAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.project.my.studystarteacher.newteacher.base.BaseFragment;
import com.project.my.studystarteacher.newteacher.bean.ExpertLecture;
import com.project.my.studystarteacher.newteacher.bean.PinglunBean;
import com.project.my.studystarteacher.newteacher.fragment.HudongFragment;
import com.project.my.studystarteacher.newteacher.fragment.VideoJsFragment;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.project.my.studystarteacher.newteacher.utils.EventBusUtil;
import com.project.my.studystarteacher.newteacher.utils.EventWhatId;
import com.project.my.studystarteacher.newteacher.utils.ImageUtility;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.JsonUtil;

import org.greenrobot.eventbus.EventBus;
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
    @ViewInject(R.id.tv_id)
    private TextView tv_id;
    @ViewInject(R.id.visited_Count)
    private TextView visited_Count;

    @ViewInject(R.id.viewPager)
    private ViewPager viewPager;
    private VideoJsFragment mkHomeFragment = new VideoJsFragment();
    private HudongFragment test1 = new HudongFragment();
    int id;
    boolean palyFlag;

    //http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4
    @Override
    protected void init() {
        getRight().setBackgroundResource(R.mipmap.btn_share);
        id = getIntent().getIntExtra("data", -1);
        getData();
        getHDData();
        add();
        getCommonTitle().setText("视频名字");
        payer.setUp(""
                , "", Jzvd.SCREEN_WINDOW_NORMAL);
        payer.thumbImageView.setImageURI(Uri.parse("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640"));
//        payer.getla

        payer.startButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (!palyFlag) {
                            palyad();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                }
                return false;
            }
        });

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


    //   PinglunBean

    public void getHDData() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                ArrayList<PinglunBean> list = JsonUtil.fromList((String) baseBean.getData(), "list", PinglunBean.class);
                String amount = JsonUtil.fromString((String) baseBean.getData(), "amount");
                hd.setText("课堂互动（" + amount + ")");
                EventBus.getDefault().post(new EventBusUtil(EventWhatId.PinglunBeanList, list));

            }

            @Override
            public void onFailed(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean, int failedType) {
            }
        });
        Worker.interact(id + "");
    }

    public void palyad() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);


            }
        });
        Worker.times(id + "", "4396");
    }

    public void add() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);


            }
        });
        Worker.times(id + "", "4395");
    }

    public void getData() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                ArrayList<ExpertLecture> relevantVideo = JsonUtil.fromList((String) baseBean.getData(), "relevantVideo", ExpertLecture.class);
                getCommonTitle().setText(relevantVideo.get(0).getTitle());
                payer.setUp(relevantVideo.get(0).getVideo_Url()
                        , "", Jzvd.SCREEN_WINDOW_NORMAL);
                new ImageUtility(R.mipmap.moren3).showImage(relevantVideo.get(0).getCover(), payer.thumbImageView);
                visited_Count.setText(relevantVideo.get(0).getVisited_Count() + "");
                EventBus.getDefault().post(new EventBusUtil(EventWhatId.ExpertLectureDetais, relevantVideo.get(0)));
                ;
            }
        });
        Worker.getExpertLectureById(id + "");
    }

}
