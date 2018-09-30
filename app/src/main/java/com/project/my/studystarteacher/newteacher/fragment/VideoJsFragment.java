package com.project.my.studystarteacher.newteacher.fragment;


import android.widget.GridView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.HomeZhuboAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseFragment;
import com.project.my.studystarteacher.newteacher.common.TempSourceSupply;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.videojs_fragment)
public class VideoJsFragment extends BaseFragment {
    @ViewInject(R.id.gv)
    private GridView gv;


    @Override
    public void init() {

        HomeZhuboAdapter homeClassAdapter3 = new HomeZhuboAdapter(getActivity(), R.layout.item_zhubosmall, TempSourceSupply.getTemp());
        gv.setAdapter(homeClassAdapter3);


    }

}
