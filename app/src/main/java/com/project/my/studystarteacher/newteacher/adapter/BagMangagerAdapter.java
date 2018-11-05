package com.project.my.studystarteacher.newteacher.adapter;

import android.content.Context;
import android.view.View;

import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.bean.BagXzBean;
import com.project.my.studystarteacher.newteacher.common.UserSingleton;
import com.project.my.studystarteacher.newteacher.net.DemoHttpInformation;
import com.project.my.studystarteacher.newteacher.utils.ImageUtility;
import com.zhouqiang.framework.util.JsonUtil;
import com.zhouqiang.framework.util.Logger;
import com.zhouqiang.framework.util.ToastUtil;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hasee on 2018/5/9.
 */

public class BagMangagerAdapter extends CommonAdapter<BagXzBean> {
    ImageUtility imageUtility;

    public BagMangagerAdapter(Context context, int layoutId, ArrayList<BagXzBean> datas) {
        super(context, layoutId, datas);
        imageUtility = new ImageUtility(R.mipmap.moren);
    }

    @Override
    protected void convert(ViewHolder viewHolder, final BagXzBean item, int position) {
        viewHolder.setText(R.id.bagNum, item.getSchoolbagbhao());
        viewHolder.setText(R.id.time, item.getUpdatetime());
        List<BagXzBean.BookListBean> bookList = item.getBookList();
        viewHolder.getView(R.id.oneBook).setVisibility(View.GONE);
        viewHolder.getView(R.id.twoBook).setVisibility(View.GONE);
        viewHolder.getView(R.id.threeBook).setVisibility(View.GONE);
        viewHolder.getView(R.id.open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestParams params = new RequestParams(DemoHttpInformation.UNBOX.getUrlPath());
                params.addBodyParameter("token", UserSingleton.getInstance().getToken());
                params.addBodyParameter("openOrder", item.getOpenCaseOrder());
                params.addBodyParameter("serialNo", item.getSerial_no() + "");

                x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("zq:" + result);
                        String status = JsonUtil.fromString(result, "status");
                        if (status.equals("1")) {
                            ToastUtil.showLongToast(mContext, "开启成功");
                        } else {
                            ToastUtil.showLongToast(mContext, "开启失败");
                        }
                        try {
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        ToastUtil.showLongToast(mContext, "请求失败");
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });


            }
        });
        viewHolder.getView(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final RequestParams params = new RequestParams(DemoHttpInformation.SHIELD.getUrlPath());
                params.addBodyParameter("token", UserSingleton.getInstance().getToken());
                params.addBodyParameter("openCommand", item.getOpenCaseOrder());
                if (item.getBox_status() < 2) {
                    params.addBodyParameter("troubleCode", "1");
                } else {
                    params.addBodyParameter("troubleCode", "0");
                }
                Logger.d("params:" + params.getBodyContent());
                params.addBodyParameter("boxNo", item.getInner_box_no() + "");
                x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Logger.d(params.getBodyContent() + "/result:" + result);
                        String status = JsonUtil.fromString(result, "status");
                        if (status.equals("1")) {
                            ToastUtil.showLongToast(mContext, "屏蔽成功");
                        } else {
                            ToastUtil.showLongToast(mContext, "屏蔽失败");
                        }
                        try {
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        ToastUtil.showLongToast(mContext, "请求失败");
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });


            }
        });
        if (bookList != null) {
            if (bookList.size() > 0) {
                viewHolder.getView(R.id.oneBook).setVisibility(View.VISIBLE);
                viewHolder.setText(R.id.oneBook, bookList.get(0).getBookname());
            }
            if (bookList.size() > 1) {
                viewHolder.getView(R.id.twoBook).setVisibility(View.VISIBLE);
                viewHolder.setText(R.id.twoBook, bookList.get(1).getBookname());
            }
            if (bookList.size() > 2) {
                viewHolder.getView(R.id.threeBook).setVisibility(View.VISIBLE);
                viewHolder.setText(R.id.threeBook, bookList.get(2).getBookname());
            }
        }


    }
}
