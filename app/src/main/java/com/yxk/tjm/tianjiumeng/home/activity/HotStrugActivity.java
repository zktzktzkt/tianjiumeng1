package com.yxk.tjm.tianjiumeng.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.home.adapter.HotStrugAdapter;

public class HotStrugActivity extends BaseActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecycler;

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
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(layoutManager);
        HotStrugAdapter hotStrugAdapter = new HotStrugAdapter();
        mRecycler.setAdapter(hotStrugAdapter);
        setAdapterItemClickListener(hotStrugAdapter);
    }

    private void setAdapterItemClickListener(HotStrugAdapter hotStrugAdapter) {
        hotStrugAdapter.setOnItemClickListener(new HotStrugAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(new Intent(HotStrugActivity.this, HotStrugDetailActivity.class));
            }

            @Override
            public void onItemButtonClick(int position) {

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
