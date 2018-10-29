package com.project.my.studystarteacher.newteacher.activity.my;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.project.my.studystarteacher.newteacher.common.UserSingleton;
import com.project.my.studystarteacher.newteacher.net.DemoHttpInformation;
import com.zhouqiang.framework.util.Logger;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_love_boss)
public class LoveBossActivity extends BaseActivity {
    @ViewInject(R.id.iv)
    private ImageView iv;
    @ViewInject(R.id.go)
    private TextView go;

    @Override
    protected void init() {
        getCommonTitle().setText("分享主题");
        toAnswer();
        getRight().setBackgroundResource(R.mipmap.ic_statistics);


        getRight().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToActivity(mContext, LoveJoinDetailsActivity.class);
            }
        });
    }

    public void toAnswer() {
        RequestParams params = new RequestParams(DemoHttpInformation.SHARED.getUrlPath() + "?token=" + UserSingleton.getInstance().getToken());
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Logger.d(":::::" + result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Logger.d("::onError:::" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


//        MiceNetWorker Worker = new MiceNetWorker(mContext);
//        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
//            @Override
//            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
//                super.onSuccess(netWorker, netTask, baseBean);
//                try {
//                    final HomeShare homeShare = JsonUtil.fromBean((String) baseBean.getData(), HomeShare.class);
//                    new ImageUtility(R.mipmap.moren).showImage(homeShare.getSmallPic(), iv);
//                    go.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent intent = new Intent(mContext, CommonWebViewActivity.class);
//                            intent.putExtra(ProjectConstant.WV_TITLE, homeShare.getTitle());
//                            intent.putExtra(ProjectConstant.WV_URL, homeShare.getLink());
//                            startActivity(intent);
//                        }
//                    });
//                } catch (Exception e) {
//                }
//            }
//        });
//        Worker.Share();

    }

}
