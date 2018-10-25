package com.project.my.studystarteacher.newteacher.common;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.umeng.socialize.editorpage.ShareActivity;
import com.zhouqiang.framework.util.Logger;


/**
 * Created by zq on 2016/9/28.
 */
public class CommonWebViewActivity extends BaseActivity {
    private static final String TAG = "CommonWebViewActivity";
    private ProgressBar bar;
    private WebView wvPage;
    private String title;
    private String url;
    private boolean share;
    public static String isHtml = "isHtml";
    public static boolean html = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_webview);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
        getExras();
        findView();
    }


    protected void findView() {
        getCommonTitle().setText(title);
        share = getIntent().getBooleanExtra(ProjectConstant.SHARE, false);
        html = getIntent().getBooleanExtra(CommonWebViewActivity.isHtml, false);
        if (share) {
            getRight().setImageDrawable(getResources().getDrawable(R.mipmap.btn_share));
            getRight().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ShareActivity.class);
                    intent.putExtra("url", getIntent().getStringExtra(ProjectConstant.SHARE_URL));
                    intent.putExtra("title", getIntent().getStringExtra(ProjectConstant.SHARE_TITLE));
                    intent.putExtra("image", getIntent().getStringExtra(ProjectConstant.SHARE_IMAGE));
                    intent.putExtra("content", getIntent().getStringExtra("content"));
                    startActivity(intent);
                }
            });
        }

        bar = (ProgressBar) findViewById(R.id.myProgressBar);
        wvPage = (WebView) findViewById(R.id.wv_page);
        wvPage.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        wvPage.getSettings().setJavaScriptEnabled(true);
        wvPage.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wvPage.getSettings().setUseWideViewPort(true);
        wvPage.getSettings().setLoadWithOverviewMode(true);
        wvPage.setWebViewClient(new MyWebViewClient());
        wvPage.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    bar.setVisibility(View.INVISIBLE);
                } else {
                    if (View.INVISIBLE == bar.getVisibility()) {
                        bar.setVisibility(View.VISIBLE);
                    }
                    bar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        Logger.d("url:" + url);
        if (html) {
            wvPage.loadDataWithBaseURL(null, url, "text/html", "utf-8", null);
        } else {
            wvPage.loadUrl(url);
        }

    }

    //设置webview代理加载图片
    private class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            imgReset();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    //此方法获取里面的img，设置img的高度100%,固定图片不能左右滑动

    private void imgReset() {
        wvPage.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName('img'); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "var img = objs[i];   " +
                " img.style.width = '100%';img.style.height = '100%';" +
                "}" +
                "})()");
    }

    @Override
    // 设置回退
    // 覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wvPage.canGoBack()) {
            wvPage.goBack(); // goBack()表示返回WebView的上一页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void getExras() {
        title = getIntent().getStringExtra(ProjectConstant.WV_TITLE);
        url = getIntent().getStringExtra(ProjectConstant.WV_URL);
        share = getIntent().getBooleanExtra(ProjectConstant.SHARE, false);
    }


}
