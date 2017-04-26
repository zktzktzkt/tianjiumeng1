package com.yxk.tjm.tianjiumeng.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.custom.CustomLoadMoreView;
import com.yxk.tjm.tianjiumeng.home.bean.HotStrugBean;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.utils.DateUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

public class HotStrugActivity extends BaseActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecycler;
    private SwipeRefreshLayout swipe_refresh;
    private List<HotStrugBean> hotStrugBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_strug);
        App.getActivityManager().pushActivity(this);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        swipe_refresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);

        mRecycler.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(HotStrugActivity.this, HotStrugDetailActivity.class);
                intent.putExtra("productId", "1");
                startActivity(intent);
            }
        });

        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });

        setToolbarNavigationClick();
        initData();
    }

    private void initData() {

        OkHttpUtils.get()
                .url(ApiConstants.HOME_PINHUO)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        hotStrugBeanList = new Gson().fromJson(response, new TypeToken<List<HotStrugBean>>() {
                        }.getType());

                        HotStrugAdapter hotStrugAdapter = new HotStrugAdapter(hotStrugBeanList);
                        hotStrugAdapter.setLoadMoreView(new CustomLoadMoreView());
                        hotStrugAdapter.setEnableLoadMore(true);
                        mRecycler.setAdapter(hotStrugAdapter);

                        swipe_refresh.setRefreshing(false);

                        hotStrugAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                            @Override
                            public void onLoadMoreRequested() {

                            }
                        }, mRecycler);
                    }
                });

    }

    private void setToolbarNavigationClick() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    public class HotStrugAdapter extends BaseQuickAdapter<HotStrugBean, BaseViewHolder> {

        public HotStrugAdapter(List<HotStrugBean> hotStrugBeanList) {
            super(R.layout.item_hot_strug, hotStrugBeanList);
        }

        @Override
        protected void convert(BaseViewHolder helper, HotStrugBean item) {
            Glide.with(App.getAppContext()).load(item.getShowpic()).into((ImageView) helper.getView(R.id.img_pic));
            helper.setText(R.id.tv_title, item.getName());
            helper.setText(R.id.tv_amount, item.getGroupNo() + "个");
            helper.setText(R.id.tv_sendOutTime, DateUtil.longToString(item.getSendOutTime(), "yyyy年MM月dd日") + "发货");
            helper.setText(R.id.tv_price, getResources().getString(R.string.RMB) + item.getGroupPrice());
        }

    }
}
