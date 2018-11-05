package com.project.my.studystarteacher.newteacher.fragment;


import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.activity.home.CaptureActivity;
import com.project.my.studystarteacher.newteacher.activity.home.OpenMangerActivity;
import com.project.my.studystarteacher.newteacher.activity.my.ContactActivity;
import com.project.my.studystarteacher.newteacher.activity.my.LoveBossActivity;
import com.project.my.studystarteacher.newteacher.activity.my.MySettingActivity;
import com.project.my.studystarteacher.newteacher.base.BaseFragment;
import com.project.my.studystarteacher.newteacher.bean.BookCase;
import com.project.my.studystarteacher.newteacher.bean.sharebean;
import com.project.my.studystarteacher.newteacher.common.CommonWebViewActivity;
import com.project.my.studystarteacher.newteacher.common.ProjectConstant;
import com.project.my.studystarteacher.newteacher.common.UserSingleton;
import com.project.my.studystarteacher.newteacher.net.DemoHttpInformation;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.project.my.studystarteacher.newteacher.utils.ImageUtility;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.JsonUtil;
import com.zhouqiang.framework.util.ToastUtil;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.fragment_mine)
public class MyFragment extends BaseFragment {
    @ViewInject(R.id.tbar)
    private LinearLayout tbar;
    @ViewInject(R.id.icon)
    private ImageView icon;
    @ViewInject(R.id.name)
    private TextView name;
    @ViewInject(R.id.why_)
    private LinearLayout why;
    @ViewInject(R.id.activ_msg)
    private LinearLayout activMsg;
    @ViewInject(R.id.love)
    private LinearLayout love;
    @ViewInject(R.id.contact)
    private LinearLayout contact;
    @ViewInject(R.id.setting)
    private LinearLayout setting;
    @ViewInject(R.id.read_flow)
    private LinearLayout readFlow;
    @ViewInject(R.id.read_manager)
    private LinearLayout readManager;
    @ViewInject(R.id.open_tv)
    private TextView openTv;
    @ViewInject(R.id.add_book)
    private LinearLayout addBook;
    @ViewInject(R.id.open_door)
    private LinearLayout openDoor;
    @ViewInject(R.id.ly_main_weixin)
    private LinearLayout lyMainWeixin;
    ImageUtility imageUtility;

    @Override
    public void init() {
        getLeft().setVisibility(View.GONE);
        getCommonTitle().setText("我的");
        imageUtility = new ImageUtility(R.mipmap.img_headportrait);

        imageUtility.showImage(UserSingleton.getInstance().getSysUser().getHeadPic(), icon);
        name.setText(UserSingleton.getInstance().getSysUser().getTruename());
        getStatus();
        if (UserSingleton.getInstance().getSysUser().getBookmanager() == 2) {
            openDoor.setVisibility(View.VISIBLE);
            getRight().setVisibility(View.VISIBLE);
            getRight().setBackgroundResource(R.mipmap.me_ic_scan);
            getRight().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    ToActivity(mContext, CaptureActivity.class);
                    scanPic();
                }
            });
        }
    }

    private void scanPic() {
        MPermissions.requestPermissions(this, 0, Manifest.permission.CAMERA);
    }

    @PermissionGrant(0)
    public void requestSdcardSuccess() {
        mIntent = new Intent(getActivity(), CaptureActivity.class);
        startActivityForResult(mIntent, 0);
    }

    @PermissionDenied(0)
    public void requestSdcardFailed() {
        Toast.makeText(getActivity(), "权限授权失败，无法扫描二维码", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (imageUtility != null) {
            imageUtility.showImage(UserSingleton.getInstance().getSysUser().getHeadPic(), icon);
        }
    }

    public void swich() {

        RequestParams params = new RequestParams(DemoHttpInformation.SWITCHANNEL.getUrlPath());
        params.setHeader("token", UserSingleton.getInstance().getToken());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ToastUtil.showLongToast(mContext, "成功");
                getStatus();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {
                ToastUtil.showLongToast(mContext, "shib ");
            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void toAnswer() {


        RequestParams params = new RequestParams(DemoHttpInformation.TOANSWER.getUrlPath() + "?zybhao=" + UserSingleton.getInstance().getSysUser().getMain() + "&fybhao=" + UserSingleton.getInstance().getSysUser().getPart() + "&classID=" + UserSingleton.getInstance().getSysUser().getBji());

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    sharebean sharebean = JsonUtil.fromBean(result, sharebean.class);
                    Intent intent = new Intent(mContext, CommonWebViewActivity.class);
                    intent.putExtra(ProjectConstant.WV_TITLE, sharebean.getTitle());
                    intent.putExtra(ProjectConstant.WV_URL, "http://app.xuezhixing.net:8080/" + sharebean.getUrl());
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Event({R.id.icon, R.id.name, R.id.why_, R.id.activ_msg, R.id.love, R.id.contact, R.id.setting, R.id.read_flow, R.id.read_manager, R.id.open_tv, R.id.add_book, R.id.open_door, R.id.ly_main_weixin})
    private void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.icon:
                ToActivity(mContext, MySettingActivity.class);
                break;
            case R.id.name:
                break;
            case R.id.why_:
                toAnswer();
                break;
            case R.id.activ_msg:
                intent = new Intent(mContext, CommonWebViewActivity.class);
                intent.putExtra(ProjectConstant.WV_TITLE, "活动通知");
                intent.putExtra(ProjectConstant.WV_URL, "https://x.eqxiu.com/s/hq0HbnrI");
                startActivity(intent);
                break;
            case R.id.love:
                ToActivity(mContext, LoveBossActivity.class);
                break;
            case R.id.contact:
                ToActivity(mContext, ContactActivity.class);
                break;
            case R.id.setting:
                ToActivity(mContext, MySettingActivity.class);
                break;
            case R.id.read_flow:
                intent = new Intent(mContext, CommonWebViewActivity.class);
                intent.putExtra(ProjectConstant.WV_TITLE, "借阅流程");
                intent.putExtra(ProjectConstant.WV_URL, "http://m.eqxiu.com/s/v92mt87D");
                startActivity(intent);

                break;
            case R.id.read_manager:
                intent = new Intent(mContext, CommonWebViewActivity.class);
                intent.putExtra(ProjectConstant.WV_TITLE, "借阅管理");
                intent.putExtra(ProjectConstant.WV_URL, "http://m.eqxiu.com/s/twC481uk");
                startActivity(intent);
                //ToastUtil.showLongToast(mContext, "跳转web");
                break;
            case R.id.open_tv:

                break;
            case R.id.add_book:
                swich();
                // switchannel();
                //  ToastUtil.showLongToast(mContext, "未看到二级页面");
                break;
            case R.id.open_door:
                ToActivity(mContext, OpenMangerActivity.class);
                break;
            case R.id.ly_main_weixin:
                break;
        }
    }

    public void getStatus() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                BookCase bookcase = JsonUtil.fromBean((String) baseBean.getData(), "bookcase", BookCase.class);
                if (bookcase.getEnableChannel() == 1) {
                    openTv.setText("已开启");
                } else {
                    openTv.setText("已关闭");
                }

            }

            @Override
            public void onFailed(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean, int failedType) {
                //   super.onFailed(netWorker, netTask, baseBean, failedType);
            }
        });
        Worker.bookcaseStatus();
    }

    public void switchannel() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
            }
        });
        Worker.switchannel();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // EventBus.getDefault().unregister(this);
    }
}
