package com.project.my.studystarteacher.newteacher.album;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.my.studystarteacher.newteacher.R;

import java.util.List;

/**
 * 相册列表类
 *
 * @author Administrator
 */
public class ImageFloderAdapter extends BaseAdapter {
    private Context context;
    private List<ImageFloder> list;

    public ImageFloderAdapter(Context context, List<ImageFloder> list) {
        super();
        this.context = context;
        this.list = list;
    }

    public void changeData(List<ImageFloder> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ImageFloder getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        /**
         * 图片
         */
        ImageView igPicture;
        /**
         * 名称
         */
        TextView vName;
        /**
         * 数量
         */
        TextView vCount;
        /**
         * 选择
         */
        ImageView igChoice;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_pop_picture_list, null);
            vh = new ViewHolder();
            vh.igChoice = (ImageView) convertView.findViewById(R.id.igChoice);
            vh.igPicture = (ImageView) convertView.findViewById(R.id.igPicture);
            vh.vName = (TextView) convertView.findViewById(R.id.vName);
            vh.vCount = (TextView) convertView.findViewById(R.id.vCount);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        ImageLoader.getInstance(3, ImageLoader.Type.LIFO).loadImage(
                list.get(position).getFirstImagePath(), vh.igPicture, null);
        vh.vName.setText(list.get(position).getName());
        vh.vCount.setText(list.get(position).getCount() + "张");
        if (list.get(position).isSelected())
            vh.igChoice.setVisibility(View.VISIBLE);
        else
            vh.igChoice.setVisibility(View.INVISIBLE);
        return convertView;
    }

}
