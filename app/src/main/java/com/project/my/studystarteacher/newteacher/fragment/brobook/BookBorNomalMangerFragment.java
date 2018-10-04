package com.project.my.studystarteacher.newteacher.fragment.brobook;


import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.BookBormangerNoamlAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseFragment;
import com.project.my.studystarteacher.newteacher.common.TempSourceSupply;
import com.zhouqiang.framework.util.ToastUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.fragment_bookbormanger_normal)
public class BookBorNomalMangerFragment extends BaseFragment {
    @ViewInject(R.id.list)
    private PullToRefreshGridView listView;

    @Override
    public void init() {
        BookBormangerNoamlAdapter demoAdapter = new BookBormangerNoamlAdapter(mContext, R.layout.bokbor_mangernomal_item, TempSourceSupply.getMyData());
        listView.setAdapter(demoAdapter);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                ToastUtil.showLongToast(mContext, "加载下一页");
            }
        });
    }

}
