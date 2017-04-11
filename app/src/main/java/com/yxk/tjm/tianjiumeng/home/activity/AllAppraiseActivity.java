package com.yxk.tjm.tianjiumeng.home.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.custom.CustomLoadMoreView;
import com.yxk.tjm.tianjiumeng.home.bean.AllAppraiseBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllAppraiseActivity extends BaseActivity {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    private List<AllAppraiseBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_appraise);
        ButterKnife.bind(this);
        setToolbarNavigationClick();

        list = new ArrayList<>();
        list.add(new AllAppraiseBean());
        list.add(new AllAppraiseBean());
        list.add(new AllAppraiseBean());
        list.add(new AllAppraiseBean());
        list.add(new AllAppraiseBean());
        list.add(new AllAppraiseBean());
        list.add(new AllAppraiseBean());

        AllAppraiseAdapter allAppraiseAdapter = new AllAppraiseAdapter();
        recycler.setAdapter(allAppraiseAdapter);


        allAppraiseAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

            }
        }, recycler);

        allAppraiseAdapter.setLoadMoreView(new CustomLoadMoreView());
        allAppraiseAdapter.loadMoreEnd(false);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefresh.setRefreshing(false);
                    }
                }, 1500);
            }
        });
    }

    private void setToolbarNavigationClick() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    public class AllAppraiseAdapter extends BaseQuickAdapter<AllAppraiseBean, BaseViewHolder> {
        public AllAppraiseAdapter() {
            super(R.layout.item_all_appraise, list);
        }

        @Override
        protected void convert(BaseViewHolder helper, AllAppraiseBean item) {

        }
    }


}
