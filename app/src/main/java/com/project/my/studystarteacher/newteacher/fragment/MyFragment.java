package com.project.my.studystarteacher.newteacher.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.activity.my.ContactActivity;
import com.project.my.studystarteacher.newteacher.activity.my.LoveBossActivity;
import com.project.my.studystarteacher.newteacher.activity.my.MySettingActivity;
import com.project.my.studystarteacher.newteacher.base.BaseFragment;
import com.project.my.studystarteacher.newteacher.common.CommonWebViewActivity;
import com.project.my.studystarteacher.newteacher.common.ProjectConstant;
import com.zhouqiang.framework.util.ToastUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

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

    @Override
    public void init() {
        getLeft().setVisibility(View.GONE);
        getCommonTitle().setText("我的");
        getRight().setBackgroundResource(R.mipmap.me_ic_scan);
    }

    @Event({R.id.icon, R.id.name, R.id.why_, R.id.activ_msg, R.id.love, R.id.contact, R.id.setting, R.id.read_flow, R.id.read_manager, R.id.open_tv, R.id.add_book, R.id.open_door, R.id.ly_main_weixin})
    private void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.icon:
                break;
            case R.id.name:
                break;
            case R.id.why_:
                ToActivity(mContext, LoveBossActivity.class);
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
                ToastUtil.showLongToast(mContext, "未看到二级页面");
                break;
            case R.id.open_door:
                ToastUtil.showLongToast(mContext, "开箱管理");
                break;
            case R.id.ly_main_weixin:
                break;
        }
    }
}
