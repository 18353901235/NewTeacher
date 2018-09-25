package com.project.my.studystarteacher.newteacher.wxapi;


import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.umeng.socialize.weixin.view.WXCallbackActivity;

public class WXEntryActivity extends WXCallbackActivity {
    @Override
    public void onResp(BaseResp resp) {
        if (resp.errCode == 0) {
            Toast.makeText(this, "分享成功", Toast.LENGTH_SHORT).show();
        } else {
            if(resp.errCode == -1){
                Toast.makeText(this, "分享失败", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "分享取消",Toast.LENGTH_SHORT).show();
            }
            finish();
        }
        super.onResp(resp);
    }
}
