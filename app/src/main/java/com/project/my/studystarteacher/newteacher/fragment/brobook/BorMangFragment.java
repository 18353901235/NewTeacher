package com.project.my.studystarteacher.newteacher.fragment.brobook;


import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.base.BaseFragment;

import org.xutils.view.annotation.ContentView;

@ContentView(R.layout.fragment_brobookmanger)
public class BorMangFragment extends BaseFragment {
    @Override
    public void init() {
        getCommonTitle().setText("借阅管理");
        getRight().setBackgroundResource(R.mipmap.musicbk_ic_search);
    }

}
