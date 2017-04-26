package com.yxk.tjm.tianjiumeng.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.home.adapter.CrystalHouseAdapter;
import com.yxk.tjm.tianjiumeng.home.bean.CrystalHouseBean;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

public class CrystalHouseActivity extends BaseActivity {

    private RecyclerView mRecycler;
    private Toolbar mToolbar;
    private List<CrystalHouseBean> crystalHouseList;
    private SwipeRefreshLayout swipe_refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crystal_house);
        App.getActivityManager().pushActivity(this);

        swipe_refresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        mRecycler = (RecyclerView) findViewById(R.id.xrecycler);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolbarNavigationClick();

        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });

        initData();
    }

    private void initData() {
        OkHttpUtils.get()
                .url(ApiConstants.HOME_CRYSTAL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        crystalHouseList = new Gson().fromJson(response, new TypeToken<List<CrystalHouseBean>>() {
                        }.getType());

                        LinearLayoutManager layoutManager = new GridLayoutManager(CrystalHouseActivity.this, 3);
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        mRecycler.setLayoutManager(layoutManager);
                        CrystalHouseAdapter crystalHouseAdapter = new CrystalHouseAdapter(crystalHouseList);
                        mRecycler.setAdapter(crystalHouseAdapter);
                        crystalHouseAdapter.setOnItemClickListener(new CrystalHouseAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                Intent intent = new Intent(CrystalHouseActivity.this, ProductDetailActivity.class);
                                intent.putExtra("productId", "1");
                                startActivity(intent);
                            }
                        });

                        swipe_refresh.setRefreshing(false);
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
}
