package com.project.my.studystarteacher.newteacher.view;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.project.my.studystarteacher.newteacher.R;
import com.zhouqiang.framework.util.WindowSize;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


/***
 * 广告页工具类
 *
 * @author ZhouQiang
 */
public class AdvertHorizontalUtil {
    /**
     * 广告页图片
     */
    private ViewPager vpAdver;
    /**
     * 存放图片
     */
    private ArrayList<View> advPics;
    /**
     * 关联
     */
    private Activity activity;
    /**
     * 是否继续
     */
    private boolean isContinue;
    /**
     * 图片链接地址
     */
    private ArrayList<String> lisPath;
    /**
     * 计时
     */
    private AtomicInteger what;
    /**
     * 每个图片的停留时间
     */
    private int time;
    /**
     * 下画圈
     */
    private LinearLayout llPoints;
    /**
     * 回调
     */
    private AdvertisCallBack adverCallBack;
    /**
     * 适配器
     */
    public AdvAdapter advAdapter;
    public boolean canContinue;
    /**
     * 前一页
     */
    private int preRedPointIndex;

    /***
     * 实例化 time为-1时，页面不滑动
     *
     * @param activity 关联
     * @param vpAdver  切换页面
     * @param llPoints 下画圈
     * @param lisPath  图片地址
     * @param time     图片停留时间
     */
    public AdvertHorizontalUtil(Activity activity, ViewPager vpAdver,
                                LinearLayout llPoints, ArrayList<String> lisPath, int time,
                                AdvertisCallBack adverCallBack) {
        this.activity = activity;
        this.vpAdver = vpAdver;
        this.lisPath = lisPath;
        this.time = time;
        this.adverCallBack = adverCallBack;
        this.llPoints = llPoints;
        advPics = new ArrayList<View>();
        if (time == -1 && lisPath == null) {
            lisPath = new ArrayList<String>();
            initListenr();
        } else if (time == -1 && lisPath != null) {
            initListenr();
        }
        vpAdver.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN://用户点击
                        stopTrans();
                        break;
                    case MotionEvent.ACTION_MOVE://用户点击
                        stopTrans();
                        break;
                    case MotionEvent.ACTION_UP://用户抬起
                        continueTrans();
                        break;
                }
                return false;
            }
        });
    }

    private final class AdvAdapter extends PagerAdapter {
        private List<View> views = null;

        public AdvAdapter(List<View> views) {
            this.views = views;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public void finishUpdate(View arg0) {

        }

        @Override
        public int getCount() {
            if (lisPath.size() > 1) {
                return Integer.MAX_VALUE;
            } else {
                return 1;
            }
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            final ImageView imageView = new ImageView(activity);
            imageView.setScaleType(ScaleType.FIT_XY);
            container.addView(imageView);
            final Bitmap[] bit = new Bitmap[1];
            if (lisPath != null && lisPath.size() > 0
                    ) {
                //  ImageLoader.getInstance().displayImage(lisPath.get(position % lisPath.size()),imageView);
                try {
                    Glide.with(activity).load(lisPath.get(position % lisPath.size())).error(R.mipmap.moren3).into(imageView);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN://用户点击
                            stopTrans();
                            break;
                        case MotionEvent.ACTION_MOVE://用户点击
                            stopTrans();
                            break;
                        case MotionEvent.ACTION_UP://用户抬起
                            continueTrans();
                            break;
                        case MotionEvent.ACTION_CANCEL://用户抬起
                            continueTrans();
                            break;

                    }
                    return false;
                }
            });
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    adverCallBack.AdvertisClick(position % lisPath.size(),
                            null);
                }
            });
            return imageView;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

    }

    private void initListenr() {
        advAdapter = new AdvAdapter(advPics);
        // 给顶部轮播图设置数据
        vpAdver.setAdapter(advAdapter);

        vpAdver.setCurrentItem(lisPath.size() * 1000);
        // 监听顶部轮播图
        vpAdver.setOnPageChangeListener(new TopOnPageChangeListener());
        // 先清空以前的点
        llPoints.removeAllViews();
        // 重新给前一个点赋值
        preRedPointIndex = 0;
        // 添加顶部轮播图里面的点
        for (int i = 0; i < lisPath.size(); i++) {
            ImageView imageView = new ImageView(activity.getBaseContext());
            if (i == 0) {
                imageView.setBackgroundResource(R.mipmap.home_guanggao_bule);
            } else {
                imageView.setBackgroundResource(R.mipmap.home_guanggao_gray);
            }
            int size = WindowSize.getWidth(activity) / 30;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    size, size);
            params.leftMargin = 10;
            imageView.setLayoutParams(params);
            // 默认所有点是白色
            imageView.setEnabled(false);
            // 把点添加到容器中
            if (lisPath.size() > 1) {
                llPoints.addView(imageView);
            }

        }
        // 初始化第一个红点
        if (lisPath != null && lisPath.size() > 0) {
            if (llPoints.getChildCount() > 0)
                llPoints.getChildAt(0).setEnabled(true);
        }
    }

    /*
     * 自定义Handler开启轮播
     */
    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            if (lisPath.size() > 1) {
                int nextIndex = (vpAdver.getCurrentItem() + 1);
                vpAdver.setCurrentItem(nextIndex);
                switch (msg.what) {
                    case 0:
                        break;
                    case 1:
                        // 再发一个3s的延迟消息，实现无限轮播
                        Message message = Message.obtain(handler);
                        message.what = 1;
                        handler.sendMessageDelayed(message, time);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    class TopOnPageChangeListener implements OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == 2) {
                int page = vpAdver.getCurrentItem();
                // 把前一个红点变为白色
                llPoints.getChildAt(preRedPointIndex).setBackgroundResource(R.mipmap.home_guanggao_gray);
                // 根据选中的位置更新图片描述信息
                // 根据选中的位置把点变红色
                llPoints.getChildAt(page % lisPath.size()).setBackgroundResource(R.mipmap.home_guanggao_bule);
                // 给前一个点赋值
                preRedPointIndex = page % lisPath.size();
                if (time != -1 && lisPath.size() > 1) {
                    handler.removeCallbacksAndMessages(null);
                    Message message = Message.obtain(handler);
                    message.what = 0;
                    handler.sendMessageDelayed(message, time);
                }
                adverCallBack.AdvertisChage(page);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {

        }

    }

    private MyHandler handler;

    public void setList(ArrayList<String> lisPath) {
        if (lisPath != null) {
            this.lisPath = lisPath;
            if (advAdapter != null) {
                advAdapter.notifyDataSetChanged();
            }
            initListenr();
        }
    }

    public void setAdvClear() {
        if (lisPath != null) {
            lisPath.clear();
            lisPath.add("");
            if (handler == null) {
                handler = new MyHandler();
            }
            vpAdver.setOnPageChangeListener(null);
            if (advAdapter != null) {
                advAdapter.notifyDataSetChanged();
            }
            initListenr();
        }
    }

    /***
     * 开始转换
     */
    public void startTrans() {
        if (lisPath.size() > 0) {
            if (handler == null) {
                handler = new MyHandler();
            }
            // 清除之前的消息
            handler.removeCallbacksAndMessages(null);
            Message message = Message.obtain(handler);
            message.what = 1;
            handler.sendMessageDelayed(message, time);
            initListenr();
        } else {
            return;
        }
        // 开启无限轮播
    }

    /***
     * 继续轮播
     */
    public void continueTrans() {
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            Message message = Message.obtain(handler);
            message.what = 1;
            handler.sendMessageDelayed(message, time);
        }
    }


    /***
     * 继续轮播
     */
    public void stopTrans() {

        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    public interface AdvertisCallBack {
        /***
         * 点击
         *
         * @param position 位置
         * @param bit      图片
         */
        void AdvertisClick(int position, Bitmap bit);

        /***
         * 改变
         *
         * @param position 位置
         */
        void AdvertisChage(int position);
    }
}
