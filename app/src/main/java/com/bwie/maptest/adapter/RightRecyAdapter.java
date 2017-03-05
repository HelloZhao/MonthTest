package com.bwie.maptest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.maptest.R;
import com.bwie.maptest.bean.Bean;
import com.bwie.maptest.utils.MyGridView;

/**
 * 1.类的用途：
 * 2.作者：${赵渊} on 2017/3/5 12:30
 */
public class RightRecyAdapter extends RecyclerView.Adapter<RightRecyAdapter.MyViewHolder> {


    private final Bean.RsBean rs;
    private final Context context;

    public RightRecyAdapter(Bean.RsBean rs, Context context) {
        this.rs = rs;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.right_recy_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.shopBrand.setText(rs.getChildren().get(position).getDirName());
        MyGridAdapter myGridAdapter = new MyGridAdapter(rs.getChildren().get(position).getChildren(),context);
        holder.right_grid.setAdapter(myGridAdapter);
    }

    @Override
    public int getItemCount() {
        return rs.getChildren() == null && rs.getChildren().size() > 0 ? 0 : rs.getChildren().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView shopBrand;
        private final MyGridView right_grid;

        public MyViewHolder(View itemView) {
            super(itemView);
            shopBrand = (TextView) itemView.findViewById(R.id.shopBrand);
            right_grid = (MyGridView) itemView.findViewById(R.id.right_grid);
        }
    }
}
