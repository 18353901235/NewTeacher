package com.project.my.studystarteacher.newteacher.fragment.brobook;


import android.view.View;
import android.widget.AdapterView;

import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.BookBormangerAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseFragment;
import com.project.my.studystarteacher.newteacher.common.TempSourceSupply;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.fragment_bookbormanger)
public class BookBorMangerFragment extends BaseFragment {
    @ViewInject(R.id.list)
    private PullToRefreshGridView listView;

    @Override
    public void init() {
        BookBormangerAdapter demoAdapter = new BookBormangerAdapter(mContext, R.layout.bookbmanger_item, TempSourceSupply.getMyData());
        listView.setAdapter(demoAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToActivity(mContext, BorMangerActivity.class);
            }
        });
    }

}
