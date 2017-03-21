package com.yxk.tjm.tianjiumeng.home.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.custom.CustomLoadMoreView;
import com.yxk.tjm.tianjiumeng.home.bean.HotStrugBean;

import java.util.ArrayList;
import java.util.List;

public class HotStrugActivity extends BaseActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecycler;
    private List<HotStrugBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_strug);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mRecycler = (RecyclerView) findViewById(R.id.recycler);

        setToolbarNavigationClick();
        initData();
    }

    private void initData() {
        list = new ArrayList<>();
        list.add(new HotStrugBean());
        list.add(new HotStrugBean());
        list.add(new HotStrugBean());
        list.add(new HotStrugBean());
        list.add(new HotStrugBean());
        list.add(new HotStrugBean());
        list.add(new HotStrugBean());
        list.add(new HotStrugBean());
        list.add(new HotStrugBean());

        HotStrugAdapter hotStrugAdapter = new HotStrugAdapter();
        hotStrugAdapter.setLoadMoreView(new CustomLoadMoreView());
        hotStrugAdapter.setEnableLoadMore(true);
        mRecycler.setAdapter(hotStrugAdapter);

        hotStrugAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

            }
        }, mRecycler);
        //setAdapterItemClickListener(hotStrugAdapter);
    }

 /*   private void setAdapterItemClickListener(HotStrugAdapter hotStrugAdapter) {
        hotStrugAdapter.setOnItemClickListener(new HotStrugAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(new Intent(HotStrugActivity.this, HotStrugDetailActivity.class));
            }

            @Override
            public void onItemButtonClick(int position) {

            }
        });*/


    private void setToolbarNavigationClick() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    public class HotStrugAdapter extends BaseQuickAdapter<HotStrugBean, BaseViewHolder> {

        public HotStrugAdapter() {
            super(R.layout.item_hot_strug, list);
        }

        @Override
        protected void convert(BaseViewHolder helper, HotStrugBean item) {

        }

    }
}
