package com.project.my.studystarteacher.newteacher.fragment;


import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.VideoChartAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseFragment;
import com.project.my.studystarteacher.newteacher.common.TempSourceSupply;
import com.zhouqiang.framework.util.Logger;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import crossoverone.statuslib.AndroidBug5497Workaround;

@ContentView(R.layout.hudong_fragment)
public class HudongFragment extends BaseFragment {
    @ViewInject(R.id.list)
    private ListView list;
    @ViewInject(R.id.et_sendmessage)
    private EditText et_sendmessage;

    @Override
    public void init() {
        AndroidBug5497Workaround.assistActivity(mContext);//加上这一行,一定要在第一行F
        VideoChartAdapter homeClassAdapter3 = new VideoChartAdapter(getActivity(), R.layout.item_videochart, TempSourceSupply.getTemp());
        list.setAdapter(homeClassAdapter3);
        et_sendmessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Logger.d(";;" + s.toString());
            }
        });

    }

}
