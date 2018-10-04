package com.project.my.studystarteacher.newteacher.fragment.brobook;


import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.RecommendAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseFragment;
import com.project.my.studystarteacher.newteacher.common.TempSourceSupply;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.fragment_book_bor_record_list)
public class RecommendBookFragment extends BaseFragment {
    @ViewInject(R.id.list)
    private PullToRefreshListView listView;

    @Override
    public void init() {
        getRight().setBackgroundResource(R.mipmap.musicbk_ic_search);
        RecommendAdapter demoAdapter = new RecommendAdapter(mContext, R.layout.commendbook_item, TempSourceSupply.getCZSData());
        listView.setAdapter(demoAdapter);

    }

}
