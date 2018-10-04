package com.project.my.studystarteacher.newteacher.fragment.brobook;


import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.BookBoMangPeoAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseFragment;
import com.project.my.studystarteacher.newteacher.common.TempSourceSupply;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.fragment_book_bor_record_list)
public class BookBorMangerPeopleFragment extends BaseFragment {
    @ViewInject(R.id.list)
    private PullToRefreshListView listView;

    @Override
    public void init() {
        BookBoMangPeoAdapter demoAdapter = new BookBoMangPeoAdapter(mContext, R.layout.bokbor_mangerpeople_item, TempSourceSupply.getCZSData());
        listView.setAdapter(demoAdapter);

    }

}
