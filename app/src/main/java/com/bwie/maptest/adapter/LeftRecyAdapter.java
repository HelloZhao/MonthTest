package com.bwie.maptest.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.maptest.R;
import com.bwie.maptest.utils.App;

import java.util.List;

/**
 * 1.类的用途：
 * 2.作者：${赵渊} on 2017/3/4 13:54
 */
public class LeftRecyAdapter extends RecyclerView.Adapter<LeftRecyAdapter.MyViewHolder> {


    private final List<String> mList;
    private final Context context;
    private LeftRecyOnItemClickListener recyOnItemClickListener;

    public void setRecyOnItemClickListener(LeftRecyOnItemClickListener recyOnItemClickListener) {
        this.recyOnItemClickListener = recyOnItemClickListener;
    }

    public LeftRecyAdapter(List<String> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.textView.setText(mList.get(position).toString());
        if(position == App.pos){
            holder.textView.setBackgroundColor(Color.WHITE);
        }else{
            holder.textView.setBackgroundColor(context.getResources().getColor(R.color.defaultColor));
        }
        if(recyOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();
                    recyOnItemClickListener.leftRecyItemClickListener(layoutPosition);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
    //定义item监听接口
    public interface LeftRecyOnItemClickListener{
        void leftRecyItemClickListener(int pos);
    }
}
