package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.utils.ImageUtility;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * Created by hasee on 2018/5/6.
 */

public class HomeZhuboAdapter extends CommonAdapter<String> {
    ImageUtility imageUtility;
    private int ids[] = new int[]{
            R.mipmap.home_ic_music, R.mipmap.home_ic_activity, R.mipmap.home_ic_anchor, R.mipmap.home_ic_borrow

    };

    public HomeZhuboAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
        imageUtility = new ImageUtility(R.mipmap.moren);
    }

    @Override
    protected void convert(ViewHolder viewHolder, String item, int position) {
        ImageView iv = viewHolder.getView(R.id.iv);
        imageUtility.showcornerImage("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2292044633,1412475702&fm=27&gp=0.jpg", iv, R.mipmap.moren3);
    }
}
