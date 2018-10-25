package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.bean.ZhuBoBean;
import com.project.my.studystarteacher.newteacher.utils.ImageUtility;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;

/**
 * Created by hasee on 2018/5/6.
 */

public class HomeZhuboAdapter extends CommonAdapter<ZhuBoBean> {
    ImageUtility imageUtility;
    private int ids[] = new int[]{
            R.mipmap.home_ic_music, R.mipmap.home_ic_activity, R.mipmap.home_ic_anchor, R.mipmap.home_ic_borrow

    };

    public HomeZhuboAdapter(Context context, int layoutId, ArrayList<ZhuBoBean> datas) {
        super(context, layoutId, datas);
        imageUtility = new ImageUtility(R.mipmap.moren);
    }

    @Override
    protected void convert(ViewHolder viewHolder, ZhuBoBean item, int position) {
        ImageView iv = viewHolder.getView(R.id.iv);
        imageUtility.showcornerImage(item.getHeadImg(), iv, R.mipmap.moren3);
        viewHolder.setText(R.id.vName, item.getBookname());
    }
}
