package com.bwie.maptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bwie.maptest.adapter.LeftRecyAdapter;
import com.bwie.maptest.adapter.RightRecyAdapter;
import com.bwie.maptest.bean.Bean;
import com.bwie.maptest.utils.App;
import com.bwie.maptest.utils.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import static com.bwie.maptest.utils.App.pos;

public class MainActivity extends AppCompatActivity {

    private RecyclerView leftRecyclerView, rightRecyclerView;
    private String url = "http://mock.eoapi.cn/success/4q69ckcRaBdxhdHySqp2Mnxdju5Z8Yr4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        getData();
    }

    //请求网络
    private void getData() {
        OkHttpUtils httpUtils = OkHttpUtils.getHttpUtils();
        httpUtils.loadDataFromNet(url, Bean.class, new OkHttpUtils.CallBackListener<Bean>() {
            @Override
            public void onSuccess(Bean result) {
                List<String> titleList = new ArrayList<String>();
                List<Bean.RsBean> rs = result.getRs();
                if (rs != null && rs.size() > 0) {
                    for (int i = 0; i < rs.size(); i++) {
                        titleList.add(rs.get(i).getDirName());
                    }
                }
                //左边标题列表
                leftRecy(titleList,rs);
            }

            @Override
            public void onFail() {

            }
        });
    }
    //左边recycleView
    private void leftRecy(List<String> mList, final List<Bean.RsBean> rs) {
        leftRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        final LeftRecyAdapter leftRecy =  new LeftRecyAdapter(mList,MainActivity.this);
        leftRecyclerView.setAdapter(leftRecy);
        rightRecyclerView(rs.get(pos));
        leftRecy.setRecyOnItemClickListener(new LeftRecyAdapter.LeftRecyOnItemClickListener() {
            @Override
            public void leftRecyItemClickListener(int pos) {
                App.pos = pos;
                //右边RecyclerView
                rightRecyclerView(rs.get(pos));
                leftRecy.notifyDataSetChanged();
            }
        });
    }
    //右边RecyclerView
    private void rightRecyclerView(Bean.RsBean rs) {
        rightRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RightRecyAdapter rightRecyAdapter = new RightRecyAdapter(rs,MainActivity.this);
        rightRecyclerView.setAdapter(rightRecyAdapter);
    }

    //初始化控件
    private void initViews() {
        leftRecyclerView = (RecyclerView) findViewById(R.id.leftRecyclerView);
        rightRecyclerView = (RecyclerView) findViewById(R.id.rightRecyclerView);
    }
}
