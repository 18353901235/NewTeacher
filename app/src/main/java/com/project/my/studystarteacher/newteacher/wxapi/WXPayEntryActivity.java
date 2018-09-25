package com.project.my.studystarteacher.newteacher.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhouqiang.framework.BaseActivityManager;
import com.zhouqiang.framework.util.ToastUtil;


public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {

    //	private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
//
    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.handleIntent(getIntent(), this);

    }

    @Override
    protected void init() {

    }

//	@Override
//	protected void onNewIntent(Intent intent) {
//		super.onNewIntent(intent);
//		setIntent(intent);
//        api.handleIntent(intent, this);
//	}

    @Override
    public void onReq(BaseReq req) {

    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (resp.errCode == 0) {
                ToastUtil.showShortToast(this, " 支付成功！ ");
//				finishActivity(PayChoiceActivity.class);
//				finishActivity(AgentRegisterActivity.class);
                nextStepRecharge();

            } else {
                if (resp.errCode == -1) {
                    Toast.makeText(this, "支付失败", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "支付取消", Toast.LENGTH_LONG).show();
                }
                finish();
            }
        }
    }

    private void finishActivity(Class<?> cl) {
        Activity activity = BaseActivityManager.getActivityByClass(cl);
        if (activity != null) {
            activity.finish();
        }
    }

    private void nextStepRecharge() {

    }


}