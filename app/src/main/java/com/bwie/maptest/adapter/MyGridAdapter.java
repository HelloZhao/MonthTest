package com.bwie.maptest.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.maptest.R;
import com.bwie.maptest.bean.Bean;

import java.util.List;

/**
 * 1.类的用途：
 * 2.作者：${赵渊} on 2017/3/5 12:54
 */
public class MyGridAdapter extends BaseAdapter {

    private List<Bean.RsBean.ChildrenBeanX.ChildrenBean> children;
    private final Context context;

    public MyGridAdapter(List<Bean.RsBean.ChildrenBeanX.ChildrenBean> children, Context context) {
        this.children = children;
        this.context = context;
    }

    @Override
    public int getCount() {
        return children == null && children.size() > 0 ? 0 : children.size();
    }

    @Override
    public Object getItem(int position) {
        return children.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.grid_item_layout, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            holder.textView = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(children.get(position).getImgApp()).into(holder.imageView);
        holder.textView.setText(children.get(position).getDirName());
        notifyDataSetChanged();
        return convertView;
    }

    class ViewHolder {
        TextView textView;
        ImageView imageView;
    }
}
