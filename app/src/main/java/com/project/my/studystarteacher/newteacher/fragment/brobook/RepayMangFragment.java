package com.project.my.studystarteacher.newteacher.fragment.brobook;


import android.Manifest;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.activity.home.CaptureActivity;
import com.project.my.studystarteacher.newteacher.adapter.BaseVPFAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseFragment;
import com.project.my.studystarteacher.newteacher.common.UserSingleton;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.project.my.studystarteacher.newteacher.utils.EventBusUtil;
import com.project.my.studystarteacher.newteacher.utils.EventWhatId;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.ToastUtil;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

@ContentView(R.layout.fragment_repaybookmanger)
public class RepayMangFragment extends BaseFragment {
    @ViewInject(R.id.rg)
    private RadioGroup group;
    private BookMeRepayFragment test1 = new BookMeRepayFragment();
    private BookRepayRecordFragment test2 = new BookRepayRecordFragment();
    /**
     * 加载所有fragment
     */
    @ViewInject(R.id.viewPager)
    private ViewPager linAllFragment;

    @Override
    public void init() {
        EventBus.getDefault().register(this);
        getCommonTitle().setText("还书管理");
        ArrayList<BaseFragment> frg = new ArrayList<BaseFragment>();
        frg.add(test2);
        frg.add(test1);
        getRightTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventBusUtil(EventWhatId.SUBMITREPAY));
            }
        });
        linAllFragment.setAdapter(new BaseVPFAdapter(getChildFragmentManager(), frg));
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.one) {
                    linAllFragment.setCurrentItem(0);
                    getRightTextView().setVisibility(View.GONE);
                    getRight().setBackground(null);
                } else if (checkedId == R.id.two) {
                    if (UserSingleton.getInstance().getSysUser().getBookmanager() == 2) {
                        group.check(R.id.one);
                        takePhone();
                    } else {
                        linAllFragment.setCurrentItem(1);
                        getRightTextView().setText("提交");
                    }
                    //   getRight().setBackgroundResource(R.mipmap.musicbk_ic_search);
                } else if (checkedId == R.id.three) {
                    getRight().setBackground(null);
                    linAllFragment.setCurrentItem(2);
                }
            }
        });
        linAllFragment.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    group.check(R.id.one);
                } else if (position == 1) {
                    group.check(R.id.two);
                } else if (position == 2) {
                    group.check(R.id.three);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void scan(EventBusUtil y) {
        if (y.getMsgWhat() == EventWhatId.REPAYSCAN) {
            String s = (String) y.getMsgStr();
            MiceNetWorker Worker = new MiceNetWorker(mContext);
            Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
                @Override
                public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                    super.onSuccess(netWorker, netTask, baseBean);
                    ToastUtil.showLongToast(mContext, "还书成功");
                }
            });
            Worker.giveBarcodeBackBags(s);

        }
    }

    private void takePhone() {
        MPermissions.requestPermissions(this, 0, Manifest.permission
                .CAMERA);
    }

    @Override

    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
