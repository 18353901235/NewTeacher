package com.project.my.studystarteacher.newteacher.share;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.zhouqiang.framework.util.Logger;


/**
 * 分享界面
 * Created by ZhouQiang on 2015/12/1.
 */
public class ShareActivity extends BaseActivity implements View.OnClickListener {
    private Context mContext;
    private LinearLayout llPoplayout;

    private LinearLayout llQqkj;
    private LinearLayout llPyq;
    private LinearLayout llWx;
    private LinearLayout llQq;

    private TextView tvCancel;

    private String mImage;
    private String mTitle;
    private String mContent;
    private String mUrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_share);
        mContext = this;
        getWindow().setLayout(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        // Tencent类是SDK的主要实现类，开发者可通过Tencent类访问腾讯开放的OpenAPI。
        // 其中APP_ID是分配给第三方应用的appid，类型为String。
        super.onCreate(savedInstanceState);
    }

    @Override
    public void finish() {
        super.finish();
        //注释掉activity本身的过渡动画
        overridePendingTransition(0, 0);
    }

    @Override
    protected void init() {
        llPoplayout = (LinearLayout) findViewById(R.id.ll_pop_layout);
        llWx = (LinearLayout) findViewById(R.id.ll_wx);
        llPyq = (LinearLayout) findViewById(R.id.ll_wx_pyq);
        llQq = (LinearLayout) findViewById(R.id.ll_qq);
        llQqkj = (LinearLayout) findViewById(R.id.ll_qzone);

        tvCancel = (TextView) findViewById(R.id.tv_cancel);
        mImage = getIntent().getStringExtra("image");
        mTitle = getIntent().getStringExtra("title");
        mContent = getIntent().getStringExtra("content");
        if (TextUtils.isEmpty(mContent)) {
            mContent = getString(R.string.app_name);
        }
        mUrl = getIntent().getStringExtra("url");
        if (isNull(mImage)) {
            mImage = "http://120.24.168.227:8080/manager/applogo.png";
        }
        if (isNull(mUrl)) {
            mUrl = "";
        }
        Logger.d(mImage + "::" + mUrl);
        //添加选择窗口范围监听可以优先获取触点，即不再执行onTouchEvent()函数，点击其他地方时执行onTouchEvent()函数销毁Activity
        llPoplayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
        llPyq.setOnClickListener(this);
        llWx.setOnClickListener(this);
        llQq.setOnClickListener(this);
        llQqkj.setOnClickListener(this);
        tvCancel.setOnClickListener(this);

    }


    UMImage image = null;

    @Override
    public void onClick(View v) {
        if (TextUtils.isEmpty(mImage)) {
            image = new UMImage(mContext,
                    BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher));
        } else {
            image = new UMImage(mContext, mImage);
        }
        UMWeb web = new UMWeb(mUrl);
        web.setThumb(image);
        web.setTitle(mTitle);
        web.setDescription(mContent);
        if (v.getId() == R.id.ll_wx) {
            //  image.setTargetUrl(mUrl);
            new ShareAction(this).setPlatform(SHARE_MEDIA.WEIXIN).setCallback(umShareListener)
                    .withMedia(web)
                    .withText(mContent)
                    .share();
        } else if (v.getId() == R.id.ll_wx_pyq) {
            new ShareAction(this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE).setCallback(umShareListener)
                    .withMedia(web)
                    .withText(mContent)
                    .share();
        } else if (v.getId() == R.id.ll_qq) {
            Logger.d("mUrl:" + mUrl);
            new ShareAction(this).setPlatform(SHARE_MEDIA.QQ).setCallback(umShareListener)
                    .withMedia(web)
                    .withText(mContent)
                    .share();

        } else if (v.getId() == R.id.ll_qzone) {
            Logger.d("mImage:" + mImage);
            UMImage umImage = new UMImage(mContext, mImage);
            new ShareAction(this).setPlatform(SHARE_MEDIA.QZONE).setCallback(umShareListener)
                    .withMedia(web)
                    .withText(mContent)
                    .share();

        } else if (v.getId() == R.id.tv_cancel) {
            finish();
        }
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            finish();
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            //   ToastUtil.showShortToast(mContext, getChinaName(platform) + " 分享成功啦");
            finish();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            //  ToastUtil.showShortToast(mContext, getChinaName(platform) + " 分享失败啦");
            finish();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            //  ToastUtil.showShortToast(mContext, getChinaName(platform) + " 分享取消了");
            finish();
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

    private String getChinaName(SHARE_MEDIA platform) {
        if (platform.equals(SHARE_MEDIA.WEIXIN)) {
            return "微信";
        } else if (platform.equals(SHARE_MEDIA.WEIXIN_CIRCLE)) {
            return "朋友圈";
        } else if (platform.equals(SHARE_MEDIA.QQ)) {
            return "QQ";
        } else if (platform.equals(SHARE_MEDIA.QZONE)) {
            return "QQ空间";
        } else {
            return "";
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        finish();
        /** attention to this below ,must add this**/
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
